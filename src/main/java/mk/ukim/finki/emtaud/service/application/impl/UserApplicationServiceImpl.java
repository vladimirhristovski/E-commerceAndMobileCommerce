package mk.ukim.finki.emtaud.service.application.impl;

import mk.ukim.finki.emtaud.dto.CreateUserDto;
import mk.ukim.finki.emtaud.dto.DisplayUserDto;
import mk.ukim.finki.emtaud.dto.LoginResponseDto;
import mk.ukim.finki.emtaud.dto.LoginUserDto;
import mk.ukim.finki.emtaud.model.domain.User;
import mk.ukim.finki.emtaud.security.JwtHelper;
import mk.ukim.finki.emtaud.service.application.UserApplicationService;
import mk.ukim.finki.emtaud.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = this.userService.register(createUserDto.username(), createUserDto.password(), createUserDto.repeatPassword(), createUserDto.name(), createUserDto.surname(), createUserDto.role());

        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = this.userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = this.jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(this.userService.findByUsername(username)));
    }
}
