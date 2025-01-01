package budhioct.dev.service;

import budhioct.dev.dto.UserDTO;

public interface UserService {
    UserDTO.RegisterResponse register(UserDTO.RegisterRequest request);
    UserDTO.LoginResponse login(UserDTO.LoginRequest request);
}
