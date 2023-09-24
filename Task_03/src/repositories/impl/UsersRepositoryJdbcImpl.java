package repositories.impl;

import models.User;
import repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (ExecutorService service = Executors.newFixedThreadPool(10)) {

            service.submit(() -> {
                try {

                    Connection connection = dataSource.getConnection();

                    Statement statement = connection.createStatement();

                    ResultSet resultSet = statement.executeQuery("select * from account;");
                    while (resultSet.next()) {
                        User user = new User(resultSet.getString("email"), resultSet.getString("password"));
                        users.add(user);
                    }
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return users;
        }
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public User findOneByEmail(String email) {
        return null;
    }

}

