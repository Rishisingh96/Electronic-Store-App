package com.rishi.electronic.store.controllers;

import ch.qos.logback.core.boolex.EvaluationException;
import com.rishi.electronic.store.dtos.ApiResponseMessage;
import com.rishi.electronic.store.dtos.ImageResponse;
import com.rishi.electronic.store.dtos.PageableResponse;
import com.rishi.electronic.store.dtos.UserDto;
import com.rishi.electronic.store.entites.Providers;
import com.rishi.electronic.store.exceptions.BadApiRequest;
import com.rishi.electronic.store.services.FileService;
import com.rishi.electronic.store.services.UserServices;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userServices;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath ;

    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
       userDto.setProviders(Providers.SELF);
        UserDto userDto1 = userServices.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") String userId,
            @Valid @RequestBody UserDto userDto
    ) {
        UserDto updateUserDto = userServices.updateUser(userDto, userId);
        return new ResponseEntity<>(updateUserDto, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) {
        userServices.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage.builder()
                .message("\"User is deleted Successfully !!").
                success(true).
                status(HttpStatus.OK).
                build();
        return new ResponseEntity<>(message, HttpStatus.OK);
//        return new ResponseEntity<>("User is deleted Successfully !!",HttpStatus.OK);

    }

    //get all
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "20",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    ) {
        return new ResponseEntity<>(userServices.getAllUser(pageNumber,pageSize,sortBy,sortDir), HttpStatus.OK);
    }

    //get single
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(userServices.getUserById(userId), HttpStatus.OK);
    }

    //get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userServices.getUserByEmail(email), HttpStatus.OK);
    }

    //search user
    @GetMapping("/Search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords) {
        return new ResponseEntity<>(userServices.searchUser(keywords), HttpStatus.OK);
    }

    //upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage")MultipartFile image,@PathVariable String userId) throws IOException, BadApiRequest {
        String imageName = fileService.uploadFile(image, imageUploadPath);

        UserDto user = userServices.getUserById(userId);
        user.setImageName(imageName);

        UserDto userDto = userServices.updateUser(user,userId);

        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }

    //serve user image
    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto user = userServices.getUserById(userId);
        logger.info("User image name : {}",user.getImageName());
        InputStream resources = fileService.getResources(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resources,response.getOutputStream());
    }
}
