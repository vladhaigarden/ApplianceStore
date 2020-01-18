package com.epam.preprod.tereshkevych.shop.error.validator;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.exception.DbException;

import javax.servlet.http.HttpServletRequest;

/**
 * Search errors in request
 *
 * @author Vladyslav Tereshkevych
 */
public abstract class ChainValidator {

    private ChainValidator nextSearcher;

    public ChainValidator() {
    }

    public ChainValidator(ChainValidator nextSearcher) {
        this.nextSearcher = nextSearcher;
    }

    public abstract FormErrorContainer searchError(HttpServletRequest request) throws DbException;

    public ChainValidator getSearcherError() {
        return nextSearcher;
    }
}