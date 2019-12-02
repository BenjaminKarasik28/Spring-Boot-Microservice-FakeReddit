package com.example.userapi.service;

import com.example.userapi.config.JwtUtil;
import com.example.userapi.exceptionhandling.EmailSignupException;
import com.example.userapi.exceptionhandling.ExistingUserSignupException;
import com.example.userapi.exceptionhandling.IncorrectLoginException;
import com.example.userapi.model.User;
import com.example.userapi.model.UserRole;
import com.example.userapi.repository.UserRepository;
import com.example.userapi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRoleService userRoleService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    private RestTemplate restTemplate = new RestTemplate();

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public List<String> userLogin(User user) {
        User newUser = userRepository.findByEmail(user.getEmail());
        if( newUser != null && encoder().matches(user.getPassword(), newUser.getPassword())) {
            return Arrays.asList( jwtUtil.generateToken(newUser.getUsername()), newUser.getUsername());
        }
        else {
            logger.error("User tried to sign up with incorrect username or password");
            throw new IncorrectLoginException("Incorrect username or password");
        }
    }

    @Override
    public List<String> userSignup(User newUser) throws EmailSignupException, ExistingUserSignupException {
        //validate email input
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        try {
            if(!newUser.getEmail().matches(regex)) {
                logger.error("Attempted to sign up with the following email " + newUser.getEmail());
                throw new EmailSignupException("Please enter a valid email");
            }
            else if((userRepository.findByEmail(newUser.getEmail()) != null) || (userRepository.findByUsername(newUser.getUsername()) != null)) {
                logger.error("User tried to sign up with existing email " + newUser.getEmail());
                throw new ExistingUserSignupException("User already exists - please login");
            }
            UserRole userRole = userRoleRepository.findByName("ROLE_USER");
            if (userRole == null) {
                userRole = new UserRole();
                userRole.setName("ROLE_USER");
                userRole = userRoleService.createRole(userRole);
            }
            newUser.addRole(userRole);

            newUser.setPassword(encoder().encode(newUser.getPassword()));
            if (userRepository.save(newUser) != null) {
                logger.info("User signed up" + " " + newUser.getEmail());
                return Arrays.asList(jwtUtil.generateToken(newUser.getUsername()), newUser.getUsername());
            }
        } finally {
            System.out.println("test");
        }
        return null;
    }

    @Override
    public User addRole(String email, int roleId) {
        UserRole userRole = userRoleRepository.findById(roleId).get();
        User user = userRepository.findByEmail(email);
        user.addRole(userRole);
        return userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Long deleteUserByUsername(String username) {
        restTemplate.delete("http://localhost:8082/post/" + username);
        restTemplate.delete("http://localhost:8083/post/name/" + username);
        User savedUser = userRepository.findByUsername(username);
        userRepository.delete(savedUser);
        return savedUser.getId();
    }

    @Override
    public User updateUser(String username, User user) {
        User savedUser = userRepository.findByUsername(username);
        if(user.getUsername() != null) savedUser.setUsername(user.getUsername());
        if(user.getEmail() != null) savedUser.setEmail(user.getEmail());
        if(user.getPassword() != null) savedUser.setPassword(encoder().encode(user.getPassword()));
        if(user.getRoles() != null) savedUser.setRoles(user.getRoles());
        return userRepository.save(savedUser);
    }

    @Override
    public String getEmailByUsername(String username) {
        User user = userRepository.findByUsername(username);
        String email = user.getEmail();
        return email;
    }
}


