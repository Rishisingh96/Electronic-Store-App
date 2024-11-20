package com.rishi.electronic.store.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

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
