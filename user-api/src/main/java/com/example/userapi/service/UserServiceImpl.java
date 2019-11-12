package com.example.userapi.service;

import com.example.userapi.config.JwtUtil;
import com.example.userapi.model.JwtResponse;
import com.example.userapi.model.User;
import com.example.userapi.model.UserRole;
import com.example.userapi.repository.UserRepository;
import com.example.userapi.repository.UserRoleRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRoleService userRoleService;

    //comment this out

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;


    //add the below
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String userLogin(User user){
        User newUser = userRepository.findByUsername(user.getUsername());

        if(newUser != null && bCryptPasswordEncoder.matches(user.getPassword(), newUser.getPassword())){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user==null)
            throw new UsernameNotFoundException("User null");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, new ArrayList<>());
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        //authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

        return authorities;
    }

    @Override
    public String userSignup(User newUser) {
        UserRole userRole = userRoleService.getRole(newUser.getRoles().get(0).getName());
        newUser.addRole(userRole);


        // comment below line out
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        if(userRepository.save(newUser) != null){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        // add in below code
//        newUser.setPassword(encoder().encode(newUser.getPassword()));
//        User savedUser = userRepository.save(newUser);
//        if(userRepository.save(newUser) != null){
//            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
//            return jwtUtil.generateToken(userDetails);
//        }
        return null;
    }

    @Override
    public User addRole(String username, int roleId) {
        UserRole userRole = userRoleRepository.findById(roleId).get();
        User user = userRepository.findByUsername(username);
        user.addRole(userRole);

        return userRepository.save(user);
    }
}



