package com.epam.preprod.tereshkevych.shop.util.validator;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;

import javax.servlet.http.Part;

/**
 * Validation of the uploaded file from part
 *
 * @author Vladyslav Tereshkevych
 */
public class ImageValidator {

    private static final String ERROR_USER_AVATAR_KEY = "error_user_avatar";

    private static final String ERROR_VALIDATE_FILE = "File is not selected!";

    private static final String ERROR_VALIDATE_AVATAR = "Uploaded file is not a valid image!";

    private static final String ERROR_VALIDATE_SIZE_FILE = "The file size exceeds the limit allowed (10MB)";

    private static final String BINARY_STREAM = "application/octet-stream";

    private static final String SLASH = "/";

    private static final String IMAGE_EXPANSION_JPG = "jpeg";

    private static final String IMAGE_EXPANSION_PNG = "png";

    private static final int MAXIMUM_SIZE_FILE = 10485760;

    public FormErrorContainer validate(Part part) {
        FormErrorContainer formErrorContainer = new FormErrorContainer();
        if (part == null || part.getContentType().equals(BINARY_STREAM)) {
            formErrorContainer.add(ERROR_USER_AVATAR_KEY, ERROR_VALIDATE_FILE);
        } else {
            validateImage(formErrorContainer, part);
        }
        return formErrorContainer;
    }

    private void validateImage(FormErrorContainer formErrorContainer, Part part) {
        String contentType = part.getContentType();
        String expansion = contentType.substring(contentType.lastIndexOf(SLASH) + 1);
        if (!expansion.equals(IMAGE_EXPANSION_JPG) && !expansion.equals(IMAGE_EXPANSION_PNG)) {
            formErrorContainer.add(ERROR_USER_AVATAR_KEY, ERROR_VALIDATE_AVATAR);
        } else {
            validateLength(formErrorContainer, part);
        }
    }

    private void validateLength(FormErrorContainer formErrorContainer, Part part) {
        if (part.getSize() > MAXIMUM_SIZE_FILE) {
            formErrorContainer.add(ERROR_USER_AVATAR_KEY, ERROR_VALIDATE_SIZE_FILE);
        }
    }
}