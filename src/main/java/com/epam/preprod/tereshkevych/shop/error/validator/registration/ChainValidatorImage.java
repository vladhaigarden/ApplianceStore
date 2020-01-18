package com.epam.preprod.tereshkevych.shop.error.validator.registration;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.error.validator.ChainValidator;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.util.validator.ImageValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Search errors which can appear because of uploaded wrong file.
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainValidatorImage extends ChainValidator {

    private static final String FILE_PARAMETER = "file";

    @Override
    public FormErrorContainer searchError(HttpServletRequest request) throws DbException {
        FormErrorContainer formErrorContainer = new ImageValidator().validate(getImagePart(request));
        if (!formErrorContainer.isEmpty()) {
            return formErrorContainer;
        }
        ChainValidator chainValidator = getSearcherError();
        return chainValidator == null ? formErrorContainer : chainValidator.searchError(request);
    }

    private Part getImagePart(HttpServletRequest request) throws DbException {
        try {
            return request.getPart(FILE_PARAMETER);
        } catch (IOException | ServletException e) {
            throw new DbException(e.getMessage());
        }
    }
}