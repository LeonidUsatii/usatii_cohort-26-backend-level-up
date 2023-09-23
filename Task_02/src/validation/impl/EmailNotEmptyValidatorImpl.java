package validation.impl;

import validation.EmailValidator;


public class EmailNotEmptyValidatorImpl implements EmailValidator {
    @Override
    public void validate(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
    }
}
