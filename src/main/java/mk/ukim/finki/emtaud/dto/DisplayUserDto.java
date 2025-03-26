package mk.ukim.finki.emtaud.dto;

import mk.ukim.finki.emtaud.model.domain.User;
import mk.ukim.finki.emtaud.model.enumerations.Role;

public record DisplayUserDto(String username, String name, String surname, Role role) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(user.getUsername(), user.getName(), user.getSurname(), user.getRole());
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }

}
