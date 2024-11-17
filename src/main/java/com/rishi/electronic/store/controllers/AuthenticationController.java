package com.rishi.electronic.store.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.apache.v2.ApacheHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import com.rishi.electronic.store.dtos.GoogleLoginRequest;
import com.rishi.electronic.store.dtos.JwtRequest;
import com.rishi.electronic.store.dtos.JwtResponse;
import com.rishi.electronic.store.dtos.UserDto;
import com.rishi.electronic.store.entites.Providers;
import com.rishi.electronic.store.entites.User;
import com.rishi.electronic.store.exceptions.BadApiRequest;
import com.rishi.electronic.store.exceptions.ResourceNotFoundException;
import com.rishi.electronic.store.security.JwtHelper;
import com.rishi.electronic.store.services.UserServices;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    // Logger initialization
    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserServices userService;

    @Autowired
    private UserDetailsService userDetailsService;

    // Loading google client details from properties
    @Value("${app.google.client_id}")
    private String googleClientId;

    @Value("${app.google.default_password}")
    private String googleProviderDefaultPassword;

    // Method to generate token for login
    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        logger.info("Username: {}, Password: {}", request.getEmail(), request.getPassword());
        this.doAuthentication(request.getEmail(), request.getPassword());

        User user = (User) userDetailsService.loadUserByUsername(request.getEmail());

        // Generate JWT token
        String token = jwtHelper.generateToken(user);

        // Return response with token
        JwtResponse jwtResponse = JwtResponse.builder().token(token).user(modelMapper.map(user, UserDto.class)).build();
        return ResponseEntity.ok(jwtResponse);
    }

    private void doAuthentication(String email, String password) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid Username and Password..");
        }
    }

    // Method to handle Google login
    @PostMapping("/login-with-google")
    public ResponseEntity<JwtResponse> handleGoogleLogin(@RequestBody GoogleLoginRequest loginRequest) throws Exception, BadApiRequest {
        logger.info("IdToken: {}", loginRequest.getIdToken());

        // Token verification process
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new ApacheHttpTransport(), new GsonFactory())
                .setAudience(List.of(googleClientId))
                .build();

        GoogleIdToken googleIdToken = verifier.verify(loginRequest.getIdToken());

        if (googleIdToken != null) {
            // Token is valid, process payload
            GoogleIdToken.Payload payload = googleIdToken.getPayload();

            String email = payload.getEmail();
            String userName = payload.getSubject();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            logger.info("Name: {}", name);
            logger.info("Email: {}", email);
            logger.info("Picture: {}", pictureUrl);
            logger.info("User: {}", userName);

            UserDto userDto = new UserDto();
            userDto.setName(name);
            userDto.setEmail(email);
            userDto.setImageName(pictureUrl);
            userDto.setPassword(googleProviderDefaultPassword);
            userDto.setAbout("User created using Google");
            userDto.setProviders(Providers.GOOGLE);

            UserDto user = null;
            try {



                logger.info("User is being loaded from the database...");
                user = userService.getUserByEmail(userDto.getEmail());


                //provider
                logger.info(user.getPassword().toString());
                if(user.getProviders().equals(userDto.getProviders())){
                    //continue
                }else {
                    throw  new BadCredentialsException("Your Email is already registered !! Try to login user name password");
                }

            } catch (ResourceNotFoundException ex) {
                logger.info("User does not exist, creating a new user...");
                user = userService.createUser(userDto);
            }

            // Authenticate the user
            this.doAuthentication(user.getEmail(), userDto.getPassword());

            User user1=modelMapper.map(user,User.class);
            String token=jwtHelper.generateToken(user1);

            JwtResponse jwtResponse=JwtResponse.builder().token(token).user(user).build();

            return ResponseEntity.ok(jwtResponse);
        } else {
            logger.info("Token is invalid!!");
            throw new BadApiRequest("Invalid Google User");
        }
    }
}
