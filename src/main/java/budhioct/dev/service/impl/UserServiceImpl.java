package budhioct.dev.service.impl;

import budhioct.dev.dto.UserDTO;
import budhioct.dev.entity.User;
import budhioct.dev.repository.UserRepository;
import budhioct.dev.security.role.Role;
import budhioct.dev.service.UserService;
import budhioct.dev.utilities.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ValidationService validation;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO.RegisterResponse register(UserDTO.RegisterRequest request) {
        validation.validate(request);

        if (userRepository.findFirstByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));
        userRepository.save(user);

        return UserDTO.toRegisterResponse(user);
    }
}
