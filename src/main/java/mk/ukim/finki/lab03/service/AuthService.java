package mk.ukim.finki.lab03.service;

import mk.ukim.finki.lab03.model.User;

import java.time.LocalDate;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, LocalDate birth);
}
