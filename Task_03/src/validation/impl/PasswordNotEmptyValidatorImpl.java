package validation.impl;

import validation.PasswordValidator;


public class PasswordNotEmptyValidatorImpl implements PasswordValidator {

    @Override
    public void validate(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password не может быть пустым");
        }
    }
}
