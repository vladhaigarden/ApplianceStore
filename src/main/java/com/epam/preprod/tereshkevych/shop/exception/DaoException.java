package com.epam.preprod.tereshkevych.shop.exception;

/**
 * An exception that provides information on a dao access error.
 *
 * @author Vladyslav Tereshkevych
 */
public class DaoException extends Exception{

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

}