package mk.ukim.finki.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emtlab.dto.DisplayUserDto;
import mk.ukim.finki.emtlab.dto.LoginUserDto;
import mk.ukim.finki.emtlab.dto.RegisterUserDto;
import mk.ukim.finki.emtlab.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration")
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or passwords do not match."
            )
    })
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody RegisterUserDto registerUserDto) {
        try {
            return this.userApplicationService.register(registerUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Invalid username or password."
            )
    })
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDto> login(HttpServletRequest request) {
        try {
            DisplayUserDto displayUserDto = this.userApplicationService.login(
                    new LoginUserDto(request.getParameter("username"), request.getParameter("password"))
            ).orElseThrow(RuntimeException::new);

            request.getSession().setAttribute("user", displayUserDto.toUser());
            return ResponseEntity.ok(displayUserDto);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "User logout", description = "Ends the user's session.")
    @ApiResponse(responseCode = "200", description = "User logged out successfully.")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
