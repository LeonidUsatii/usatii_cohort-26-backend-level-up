package services.impl;

import models.User;
import repositories.UsersRepository;
import services.UsersService;
import validation.EmailValidator;
import validation.PasswordValidator;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final EmailValidator emailValidator;

    private final PasswordValidator passwordValidator;

    public UsersServiceImpl(UsersRepository usersRepository,
                            EmailValidator emailValidator,
                            PasswordValidator passwordValidator) {
        this.usersRepository = usersRepository;
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
    }

    public User addUser(String email, String password) {
        emailValidator.validate(email);

        passwordValidator.validate(password);

        User existedUser = usersRepository.findOneByEmail(email);

        if (existedUser != null) {
            throw new IllegalArgumentException("Пользователь с таким email уже есть");
        }

        User user = new User(email, password);

        usersRepository.save(user);

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        return usersRepository.findAll();
    }

    @Override
    public void updateUser(Long id, String newEmail, String newPassword) {

        List<User> users = usersRepository.findAll();

        User userForUpdate = null;


        for (User user : users) {

            if (user.getId().equals(id)) {

                userForUpdate = user;

                break;
            }
        }

        if (userForUpdate == null) {
            throw new IllegalArgumentException("User with id <" + id + "> not found");
        }


        if (!newEmail.isBlank()) {

            userForUpdate.setEmail(newEmail);
        }

        if (!newPassword.isBlank()) {
            userForUpdate.setPassword(newPassword);
        }


        usersRepository.update(userForUpdate);

    }
}
