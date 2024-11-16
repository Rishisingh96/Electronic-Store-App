package com.rishi.electronic.store.controllers;

import com.rishi.electronic.store.dtos.GoogleLoginRequest;
import com.rishi.electronic.store.dtos.JwtRequest;
import com.rishi.electronic.store.dtos.JwtResponse;
import com.rishi.electronic.store.dtos.UserDto;
import com.rishi.electronic.store.entites.User;
import com.rishi.electronic.store.security.JwtHelper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    //method to generate token:
    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;


    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
        logger.info("Username {} , Password {}", request.getEmail(), request.getPassword());
        this.doAuthentication(request.getEmail(), request.getPassword());

        User user = (User) userDetailsService.loadUserByUsername(request.getEmail());

        //... generate token ....
        String token = jwtHelper.generateToken(user);
        //send karna hai response
        JwtResponse jwtResponse = JwtResponse.builder().token(token).user(modelMapper.map(user, UserDto.class)).build();

        return ResponseEntity.ok(jwtResponse);
    }

    private void doAuthentication(String email, String password) {

        try{
            Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
            authenticationManager.authenticate(authentication);

        }catch (BadCredentialsException ex){
            throw  new BadCredentialsException("Invalid Username and Password ..");
        }
    }

    
    public ResponseEntity<JwtResponse> handleGoogleLogin(@RequestBody GoogleLoginRequest loginRequest){
        logger.info("Id Token : {}", loginRequest.getIdToken());
        return null;
    }
}

//handle login with google
//{idToken}

