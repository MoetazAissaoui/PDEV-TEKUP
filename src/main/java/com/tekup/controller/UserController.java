package com.tekup.controller;

import com.tekup.config.TokenProvider;
import com.tekup.constants.RoleConst;
import com.tekup.model.AuthToken;
import com.tekup.model.LoginUser;
import com.tekup.model.User;
import com.tekup.model.UserDto;
import com.tekup.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
@Log4j2(topic = "UserController")
@RequestMapping("/users")
public class UserController extends BaseController {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider jwtTokenUtil;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PostMapping("/register")
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/employee")
    public User createEmployee(@RequestBody UserDto user){
        return userService.createEmployee(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/manager")
    public User createManager(@RequestBody UserDto user){
        return userService.createEmployee(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/all")
    public List<User> getAllList(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/by/username")
    public User getAllList(@RequestParam String username){
        return userService.findOne(username);
    }
}