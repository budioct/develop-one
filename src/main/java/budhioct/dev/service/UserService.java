package budhioct.dev.service;

import budhioct.dev.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    UserDTO.RegisterResponse register(UserDTO.RegisterRequest request);
    UserDTO.LoginResponse login(UserDTO.LoginRequest request);
    UserDTO.LoginResponse refreshToken(HttpServletRequest request, HttpServletResponse response);
}
