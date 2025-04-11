package com.petr.postcode_api.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.petr.postcode_api.config.JwtUtils;
import com.petr.postcode_api.user.Role;


@RestController
public class AuthController {

    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    public AuthController(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;

        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //////////////////////////////////////////////////////////
        String jwtToken = jwtUtils.generateToken(userDetails);
        //////////////////////////////////////////////////////////
        //System.out.println("Converted authorities: " + userDetails.getAuthorities());

        List<Role> roles = userDetails.getAuthorities().stream()
                .map(item -> Role.fromAuthority(item.getAuthority()))
                .collect(Collectors.toList());

        // List<Role> roles = userDetails.getAuthorities().stream()
        // .map(item -> Role.valueOf(item.getAuthority()))
        // .collect(Collectors.toList());

        LoginResponse response = new LoginResponse(jwtToken, roles, userDetails.getUsername());

        return ResponseEntity.ok(response);
    }
}
