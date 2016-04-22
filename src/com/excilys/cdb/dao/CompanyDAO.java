package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.excilys.cdb.dao.DAO;
import com.excilys.cdb.entities.*;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.jdbc.*;

public class CompanyDAO extends DAO<Company> {

	private static final String FIND_ID = "SELECT id, name from company WHERE id = ?;";
	private static final String FIND_NAME = "SELECT id, name from company WHERE name = ?;";
	private static final String CREATE = "INSERT INTO company (name) VALUES (?);";
	private static final String UPDATE = "UPDATE company SET name= ? WHERE id = ?;";
	private static final String DELETE = "DELETE FROM company WHERE id = ?;";
	private static final String LISTALL = "SELECT id,name from company;";

	@Override
	public void create(Company comp) {
		Connection connection = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(CREATE);
			stmt.setString(1, comp.getName());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}

		}

	}

	@Override
	public void update(Company comp) {
		Connection connection = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(UPDATE);
			stmt.setString(1, comp.getName());
			stmt.setLong(2, comp.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}

		}
	}

	@Override
	public void delete(Company comp) {
		Connection connection = null;
		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(DELETE);
			stmt.setLong(1, comp.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}

	}

	@Override
	public Company findById(long id) {
		Company company = new Company();
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(FIND_ID);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			rs.next();
			company.setId(rs.getLong("id"));
			company.setName(rs.getString("name"));
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return company;
	}

	@Override
	public Company findByName(String name) {
		Company company = new Company();
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(FIND_NAME);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			rs.next();
			company.setId(rs.getLong("id"));
			company.setName(rs.getString("name"));
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return company;
	}

	@Override
	public List<Company> index() {
		List<Company> companies = new ArrayList<>();
		ResultSet rs = null;
		Connection connection = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			rs = connection.prepareStatement(LISTALL).executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company.setId(rs.getLong("id"));
				company.setName(rs.getString("name"));
				companies.add(company);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				rs.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}

		}
		return companies;
	}

}
