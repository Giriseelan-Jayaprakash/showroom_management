package com.showroommanagement.service;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Users;
import com.showroommanagement.exception.BadRequestServiceAlertException;
import com.showroommanagement.repository.UserRepository;
import com.showroommanagement.util.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserValidation userValidation;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public ResponseDTO createUser(final Users user) {
        if (!userValidation.isValidEmail(user.getEmailId())) {
            throw new BadRequestServiceAlertException("Invalid Email format");
        }
        if (!userValidation.isValidPassword(user.getPassword())) {
            throw new BadRequestServiceAlertException("Invalid Password format");
        }
        if (userRepository.existsByEmailId(user.getEmailId())) {
            throw new BadRequestServiceAlertException("User Already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", user.getId());
        responseData.put("userName", user.getUserName());
        responseData.put("emailId", user.getEmailId());
        responseData.put("authority", user.getAuthority());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setMessage("Created Successfully");
        responseDTO.setData(responseData);
        return responseDTO;
    }

    public ResponseDTO verifyUser(final Users user) {
        if (!userValidation.isValidEmail(user.getEmailId())) throw new BadRequestServiceAlertException("Incorrect EmailId");
        if (!userValidation.isValidPassword(user.getPassword())) throw new BadRequestServiceAlertException("Incorrect Password");
        Optional<Users> storedUser = userRepository.findByEmailId(user.getEmailId());
        ResponseDTO responseDTO = new ResponseDTO();
        if (storedUser.isEmpty()) {
            responseDTO.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseDTO.setMessage("User not found");
            responseDTO.setData(null);
            return responseDTO;
        }
        if (bCryptPasswordEncoder.matches(user.getPassword(), storedUser.get().getPassword())) {
            if (user == null && user.getId() == null) {
                throw new BadRequestServiceAlertException("User or User ID is null. Cannot generate token.");
            }
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("id", storedUser.get().getId());
            responseData.put("userName", storedUser.get().getUserName());
            responseData.put("emailId", storedUser.get().getEmailId());
            responseData.put("authority", storedUser.get().getAuthority());
            responseData.put("token", jwtService.generateToken(storedUser.get()));
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setMessage("Login Successful");
            responseDTO.setData(responseData);
            return responseDTO;
        } else {
            responseDTO.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            responseDTO.setMessage("Invalid login credentials");
            responseDTO.setData(null);
            return responseDTO;
        }
    }
}
