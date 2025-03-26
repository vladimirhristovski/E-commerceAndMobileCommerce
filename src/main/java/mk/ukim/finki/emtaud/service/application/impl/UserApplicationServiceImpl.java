package mk.ukim.finki.emtaud.service.application.impl;

import mk.ukim.finki.emtaud.dto.CreateUserDto;
import mk.ukim.finki.emtaud.dto.DisplayUserDto;
import mk.ukim.finki.emtaud.dto.LoginUserDto;
import mk.ukim.finki.emtaud.model.domain.User;
import mk.ukim.finki.emtaud.service.application.UserApplicationService;
import mk.ukim.finki.emtaud.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = this.userService.register(createUserDto.username(), createUserDto.password(), createUserDto.repeatPassword(), createUserDto.name(), createUserDto.surname(), createUserDto.role());

        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(DisplayUserDto.from(this.userService.login(loginUserDto.username(), loginUserDto.password())));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(this.userService.findByUsername(username)));
    }
}
