package repositories.impl;

import models.User;
import repositories.UsersRepository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    private Long generatedId = 1L;

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("#"))
                    .map(parsed -> new User(Long.parseLong(parsed[0]), parsed[1], parsed[2]))
                    .collect(Collectors.toList());

        } catch (IOException e)  {
            throw new IllegalStateException("Проблемы с чтением из файла: " + e.getMessage());
        }
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            user.setId(generatedId);

            writer.write(user.getId() + "#" + user.getEmail() + "#" + user.getPassword());
            writer.newLine();

        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с записью в файл: " + e.getMessage());
        }
        generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User model) {
        List<User> users = findAll();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(model.getId())) {
                users.remove(i);
                break;
            }
        }

        users.add(model);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){

            for (User user : users) {
                writer.write(user.getId() + "#" + user.getEmail() + "#" + user.getPassword()); // записываем его в файл
                writer.newLine();
            }

        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с записью в файл: " + e.getMessage());
        }
    }

    @Override
    public User findOneByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("#"))
                    .filter(parsed -> parsed[1].equals(email))
                    .findFirst()
                    .map(parsed -> new User(Long.parseLong(parsed[0]), parsed[1], parsed[2]))
                    .orElse(null);

        } catch (IOException e)  {
            throw new IllegalStateException("Проблемы с чтением из файла: " + e.getMessage());
        }
    }
}
