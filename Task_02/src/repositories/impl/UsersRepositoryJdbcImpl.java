package repositories.impl;

import models.User;
import repositories.UsersRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {

        this.connection = connection;
    }
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from account;");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("email"), resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return users;
    }

    @Override
    public User findById(Long id) {
        return null;
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
