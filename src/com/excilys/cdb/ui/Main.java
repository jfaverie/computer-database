package com.excilys.cdb.ui;

import java.util.Scanner;

public class Main {
	
	 protected static final int ELEM_PER_PAGE = 20;

	private final Scanner scanner;

	private Main() {
		scanner = new Scanner(System.in);
		scanner.useDelimiter("\\n");
	}

	private void master() {
		System.out.println("What do you want to CRUD? [1] for Company ; [2] for Computer ; [3] to exit");
		int choice = scanner.nextInt();
		
		if (choice == 1) {
			
			
		} else if (choice == 2) {

		} else {
			System.exit(0);
		}
	}
	
	
	
	
}