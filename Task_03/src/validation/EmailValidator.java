package validation;

public interface EmailValidator {

    void validate(String email) throws IllegalArgumentException;
}
