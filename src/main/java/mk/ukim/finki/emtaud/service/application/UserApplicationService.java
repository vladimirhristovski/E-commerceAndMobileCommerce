package mk.ukim.finki.emtaud.service.application;

import mk.ukim.finki.emtaud.dto.CreateUserDto;
import mk.ukim.finki.emtaud.dto.DisplayUserDto;
import mk.ukim.finki.emtaud.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
