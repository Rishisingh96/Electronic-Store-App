package com.rishi.electronic.store.exceptions;

public class BadApiRequest extends Throwable {
    public BadApiRequest(String message) {
        super(message);
    }
    public BadApiRequest(){
        super("Bad Request !!");
    }
}
