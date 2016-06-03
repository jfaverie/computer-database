package com.excilys.cdb.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class KeyboardScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyboardScanner.class);

    private static KeyboardScanner instance = new KeyboardScanner();

    private final Scanner scanner;
    private final DateTimeFormatter formatter;

    public static KeyboardScanner getInstance() {
        return instance;
    }

    /**
     * Default constructor for init the scanner and the formatter for the date.
     */
    private KeyboardScanner() {
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");

        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    /**
     * Get an int from System.in.
     * @return an Int, or -1 during an invalid input
     */
    public int nextInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            return -1;
        }
    }

    /**
     * Get a long from System.in.
     * @return a Long value, or -1 during an invalid input
     */
    public long nextLong() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            scanner.next();
            return -1;
        }
    }

    /**
     * Get a String from System.in.
     * @return a String value, or an empty String during an invalid input
     */
    public String nextString() {
        try {
            return scanner.next();
        } catch (InputMismatchException e) {
            return "";
        }
    }

    /**
     * Get a date from System.in.
     * @return a LocalDate value, or null during an invalid input
     */
    public LocalDate nextDate() {
        try {
            return LocalDate.parse(scanner.next(), formatter);
        } catch (Exception e) {
            LOGGER.info("Wrong date format");
        }

        return null;
    }

}
