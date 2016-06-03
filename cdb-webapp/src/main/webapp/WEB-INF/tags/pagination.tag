<%@ tag body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="currentPage" type="Integer" required="true"%>
<%@ attribute name="nbPage" type="Integer" required="true"%>
<%@ attribute name="limit" type="Integer" required="true"%>
<%@ attribute name="sortCol" required="true" type="Integer"%>
<%@ attribute name="sortType" required="true" type="Integer"%>
<%@ attribute name="search" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<ul class="pagination">

	<c:if test="${currentPage > 0}">
		<li><a href="?page=${currentPage - 1}&nbel=${limit}&colId=${sortCol}&colType=${sortType}&search=${search}"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
		</a></li>
	</c:if>

	<c:choose>

		<c:when test="${currentPage > 2 && currentPage < nbPage - 2}">
			<c:forEach var="cursor" begin="${currentPage - 2}"
				end="${currentPage + 2}">
				<li ${(cursor == currentPage) ? 'class="active"' : ""}><mylib:link
						classRef="" context="" msg="${cursor}" limit="${limit}"
						cursor="${cursor}" search="${search}" sortCol="${sortCol}" sortType="${sortType}"/></li>
			</c:forEach>
		</c:when>

		<c:otherwise>
			<c:choose>
				<c:when test="${currentPage < 3}">
					<c:forEach var="cursor" begin="0" end="4">
						<c:if test="${cursor < nbPage + 1}">
							<li ${(cursor == currentPage) ? 'class="active"' : ""}><mylib:link
						classRef="" context="" msg="${cursor}" limit="${limit}"
						cursor="${cursor}" search="${search}" sortCol="${sortCol}" sortType="${sortType}" /></li>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="cursor" begin="${nbPage - 4}" end="${nbPage + 1}">
						<c:if test="${cursor < nbPage + 1}">
							<li ${(cursor == currentPage) ? 'class="active"' : ""}><mylib:link
						classRef="" context="" msg="${cursor}" limit="${limit}"
						cursor="${cursor}" search="${search}" sortCol="${sortCol}" sortType="${sortType}" /></li>
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</c:otherwise>

	</c:choose>
	<c:if test="${currentPage < nbPage}">
		<li><a href="?page=${currentPage + 1}&nbel=${limit}&colId=${sortCol}&colType=${sortType}&search=${search}"
			aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</c:if>
</ul>