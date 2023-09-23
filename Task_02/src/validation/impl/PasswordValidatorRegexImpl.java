package validation.impl;

import validation.PasswordValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidatorRegexImpl implements PasswordValidator {

    private String regex;

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public void validate(String password) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Пароль слабый");
        }
    }
}
