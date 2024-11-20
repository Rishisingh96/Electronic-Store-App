package com.rishi.electronic.store.services.Impl;

import com.rishi.electronic.store.dto.PageableResponse;
import com.rishi.electronic.store.dto.UserDto;
import com.rishi.electronic.store.entity.Role;
import com.rishi.electronic.store.entity.User;
import com.rishi.electronic.store.exceptions.ResourceNotFoundException;
import com.rishi.electronic.store.helper.Helper;
import com.rishi.electronic.store.repositories.RoleRepository;
import com.rishi.electronic.store.repositories.UserRepository;
import com.rishi.electronic.store.services.UserServices;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Value("${user.profile.image.path}")
    private String imagePath;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {
        //generate unique id in string format
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
        //dto -> entity
        User user = dtoToEntity(userDto);
        //password encode
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //get the normal role
        Role role = new Role();
        role.setRoleId(UUID.randomUUID().toString());
        role.setName("ROLE_NORMAL");
        Role roleNormal = roleRepository.findByName("ROLE_NORMAL").orElse(role);
        user.setRoles(List.of(roleNormal));

        User savedUser = userRepository.save(user);

        //entity -> dto
        UserDto newDto = entityToDto(savedUser);
        return newDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Your userId not found"));
        user.setName(userDto.getName());

        //email update
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setImageName(userDto.getImageName());

        //save data
        User updateUser = userRepository.save(user);
        UserDto updateDto = entityToDto(updateUser);
        return updateDto;
    }

    @Override
    public void deleteUser(String userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found with given id !!"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id !!")); // We use custom Exception

        //delete user profile image
        // images/user/abc.png
        String fullPath = imagePath + user.getImageName();
        try{
            Path path = Paths.get(fullPath);
            Files.delete(path);
        }catch (IOException ex){
            logger.info("User image not found in folder");
            ex.printStackTrace();
        }
        //delete user
        userRepository.delete(user);

    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {

       //Sort sort  =(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy)).ascending();
      //  Sort sort = Sort.by(sortBy);
    //   Sort sort = () ? () : ();
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending()) ;

        //pageNumber default start from 0
//        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.Direction.valueOf(sortBy),sortDir); sort
//        Pageable pageable = PageRequest.of(pageNumber-1,pageSize, sort);
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
//        List<User> users = userRepository.findAll(pageable);
        Page<User> page=  userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);

//        List<User> users = page.getContent();
//        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

//        PageableResponse<UserDto> response = new PageableResponse<>();
//        response.setContent(dtoList);
//        response.setPageNumber(page.getNumber());
//        response.setPageSize(page.getSize());
//        response.setTotalElements(page.getTotalElements());
//        response.setTotalPages(page.getTotalPages());
//     //   response.setLastPage(page.isLast());
        return response;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user id not found"));

        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found Exception Email and Password !!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    private UserDto entityToDto(User savedUser) {
//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getEmail())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName())
//                .build();
//        return userDto;
        return mapper.map(savedUser,UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getEmail())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();
//        return user;
        return mapper.map(userDto,User.class);
    }
}
