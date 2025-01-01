package budhioct.dev.rest.contoller;

import budhioct.dev.dto.UserDTO;
import budhioct.dev.rest.config.RestResponse;
import budhioct.dev.service.UserService;
import budhioct.dev.utilities.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class UserRestController {

    private final UserService userService;

    @PostMapping(
            path = "/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RestResponse.object<UserDTO.RegisterResponse>> register(@RequestBody UserDTO.RegisterRequest request){
        UserDTO.RegisterResponse userResponse = userService.register(request);
        RestResponse.object<UserDTO.RegisterResponse> res = RestResponse.object.<UserDTO.RegisterResponse>builder()
                .data(userResponse)
                .status_code(Constants.CREATED)
                .message(Constants.REGISTER_MESSAGE)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping(
            path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<UserDTO.LoginResponse> login(@RequestBody UserDTO.LoginRequest request) {
        UserDTO.LoginResponse loginResponse = userService.login(request);
        return RestResponse.object.<UserDTO.LoginResponse>builder()
                .data(loginResponse)
                .status_code(Constants.OK)
                .message(Constants.AUTH_LOGIN_MESSAGE)
                .build();
    }

}
