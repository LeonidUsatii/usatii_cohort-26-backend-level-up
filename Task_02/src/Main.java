import repositories.UsersRepository;
import repositories.impl.UsersRepositoryJdbcImpl;
import services.UsersService;
import services.impl.UsersServiceImpl;
import validation.EmailValidator;
import validation.PasswordValidator;
import validation.impl.EmailNotEmptyValidatorImpl;
import validation.impl.PasswordNotEmptyValidatorImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection;
        EmailValidator emailValidator = new EmailNotEmptyValidatorImpl();
        PasswordValidator passwordValidator = new PasswordNotEmptyValidatorImpl();
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:~/databases/shop_db;AUTO_SERVER=TRUE",
                    "admin", "qwerty007");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(connection);
        UsersService usersService = new UsersServiceImpl(usersRepository, emailValidator, passwordValidator);
        System.out.println(usersService.getAllUsers());
   }
}
