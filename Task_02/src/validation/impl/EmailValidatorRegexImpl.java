package validation.impl;

import validation.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorRegexImpl implements EmailValidator {

    private String regex;

    public EmailValidatorRegexImpl(String regex) {
        this.regex = regex;
    }

    @Override
    public void validate(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email не соответствует формату");
        }
    }
}
