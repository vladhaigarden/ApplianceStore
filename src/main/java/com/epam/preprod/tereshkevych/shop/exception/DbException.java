package com.epam.preprod.tereshkevych.shop.exception;

/**
 * An exception that provides information on a database access error.
 *
 * @author Vladyslav Tereshkevych
 */
public class DbException extends Exception {

    public DbException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbException(String message) {
        super(message);
    }
}