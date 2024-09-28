package com.rishi.electronic.store.dtos;

import com.rishi.electronic.store.validate.ImageNameValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @Size(min = 3, max = 30, message = "Invalid Name !!")
    private String name;

    //    @Email(message = "Invalid User Email !!")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "Please provide a valid email address")
    @NotBlank(message = "Email should not Blank")
    private String email;

    //    @NotBlank(message = "Password is required !!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @Size(min = 4, max = 6, message = "Invalid gender")
    private String gender;

    @NotBlank(message = "Write something about yourself !!")
    private String about;

    private List<RoleDto> roles;
    // @Pattern
    //Custom Validator
    @ImageNameValid
    private String imageName;
}
