package com.rishi.electronic.store.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {
    private String imageName;
    private String coverImage;
    private String message;  // send message Delete method UserController
    private boolean success;
    private HttpStatus status;
}
