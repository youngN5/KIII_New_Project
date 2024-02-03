package mk.ukim.finki.lab03.service.impl;

import mk.ukim.finki.lab03.model.User;
import mk.ukim.finki.lab03.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.lab03.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.lab03.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.lab03.repository.jpa.UserRepository;
import mk.ukim.finki.lab03.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }
    private boolean credentialsInvalid(String username, String password) {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate birth) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        User user = new User(username,password,name,surname,birth);
        return userRepository.save(user);

    }

}

