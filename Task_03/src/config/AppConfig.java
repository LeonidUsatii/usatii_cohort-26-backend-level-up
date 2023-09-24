package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositories.UsersRepository;
import repositories.impl.UsersRepositoryJdbcImpl;
import services.UsersService;
import services.impl.UsersServiceImpl;
import validation.EmailValidator;
import validation.PasswordValidator;
import validation.impl.EmailNotEmptyValidatorImpl;
import validation.impl.PasswordNotEmptyValidatorImpl;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class AppConfig {
    DataSource dataSource;
    @Bean
    public PasswordValidator passwordValidatorNotEmpty() {

         return new PasswordNotEmptyValidatorImpl();
    }

    @Bean
    public EmailValidator emailValidatorNotEmpty() {
        return new EmailNotEmptyValidatorImpl();
    }

    @Bean
    public UsersRepository usersRepositoryJdbc() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:file:~/databases/shop_db_1;AUTO_SERVER=TRUE");
        config.setUsername("admin");
        config.setPassword("qwerty007");
        config.setMaximumPoolSize(50);
        return new UsersRepositoryJdbcImpl
        (new HikariDataSource(config));
    }

    @Bean
    public UsersService usersService(UsersRepository usersRepositoryJdbc,
                                     EmailValidator emailValidatorNotEmpty,
                                     PasswordValidator passwordValidatorNotEmpty) {
        return new UsersServiceImpl(usersRepositoryJdbc, emailValidatorNotEmpty, passwordValidatorNotEmpty);
    }

}
