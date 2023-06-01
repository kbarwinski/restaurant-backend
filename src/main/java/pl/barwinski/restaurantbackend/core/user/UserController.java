package pl.barwinski.restaurantbackend.core.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.barwinski.restaurantbackend.core.user.request.LoginRequest;
import pl.barwinski.restaurantbackend.core.user.request.RegistrationRequest;
import pl.barwinski.restaurantbackend.core.user.exception.UserNotFoundException;
import pl.barwinski.restaurantbackend.core.user.exception.WrongPasswordException;
import pl.barwinski.restaurantbackend.utils.EndpointPaths;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(EndpointPaths.BASE)
@Tag(name = "User")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping(path = EndpointPaths.USER + "/login")
    @Operation(summary = "Login user", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in"),
            @ApiResponse(responseCode = "401", description = "User not found or wrong password")
    })
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginModel ret = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(ret);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        } catch (WrongPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
        }
    }
    @PostMapping(path = EndpointPaths.USER + "/register")
    @Operation(summary = "Register user", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully registered")
    })
    public ResponseEntity<UserEntity> register(@RequestBody RegistrationRequest registrationRequest) {
        UserEntity user = userMapper.mapToEntity(registrationRequest);

        user.setUserRole(UserEntity.UserRole.ROLE_CLIENT);

        userService.register(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = EndpointPaths.ADMIN + EndpointPaths.USER)
    @Operation(summary = "Get all users", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully got all users")
    })
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        List<Sort.Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(Sort.Direction.fromString(sort[1]), sort[0]));
        }

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<UserEntity> users = userService.getAll(pagingSort);
        List<UserDto> userDtos = userMapper.mapToDto(users.toList());

        return ResponseEntity.ok(userDtos);
    }
}

