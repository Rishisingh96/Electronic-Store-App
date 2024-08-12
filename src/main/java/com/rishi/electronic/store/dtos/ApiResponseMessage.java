package com.rishi.electronic.store.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessage {  // create class because I want to send Json format
    private String message;  // send message Delete method UserController
    private boolean success;
    private HttpStatus status;

}
