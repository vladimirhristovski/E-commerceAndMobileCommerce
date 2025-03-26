package mk.ukim.finki.emtaud.dto;

import mk.ukim.finki.emtaud.model.domain.User;
import mk.ukim.finki.emtaud.model.enumerations.Role;

public record CreateUserDto(String username, String password, String repeatPassword, String name, String surname,
                            Role role) {

    //TODO: add repeat password logic

    public User toUser() {
        return new User(name, password, name, surname, role);
    }

}
