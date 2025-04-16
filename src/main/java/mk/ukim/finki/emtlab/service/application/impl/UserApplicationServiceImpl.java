package mk.ukim.finki.emtlab.service.application.impl;

import mk.ukim.finki.emtlab.dto.DisplayUserDto;
import mk.ukim.finki.emtlab.dto.LoginUserDto;
import mk.ukim.finki.emtlab.dto.RegisterUserDto;
import mk.ukim.finki.emtlab.model.domain.User;
import mk.ukim.finki.emtlab.service.application.UserApplicationService;
import mk.ukim.finki.emtlab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(RegisterUserDto registerUserDto) {
        User user = this.userService.register(registerUserDto.username(), registerUserDto.password(), registerUserDto.repeatPassword(), registerUserDto.name(), registerUserDto.surname(), registerUserDto.role());

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
