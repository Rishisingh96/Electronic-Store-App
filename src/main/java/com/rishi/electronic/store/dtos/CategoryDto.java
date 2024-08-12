package com.rishi.electronic.store.dtos;

import com.rishi.electronic.store.validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto {

    private String categoryId;

    @NotBlank(message = "title required !!")
   // @Min(value = 4,message = "title must be of minimum 4 characters !!")
    @Size(min = 4,message = "title must be of minimum 4 characters")
    private String title;

    @NotBlank(message = "Description required !!")
    private String description;

    @ImageNameValid
    private String coverImage;
}
