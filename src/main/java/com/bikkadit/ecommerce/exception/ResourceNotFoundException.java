package com.bikkadit.ecommerce.exception;

import lombok.*;

@Setter
@Getter
@Builder
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("Resource Not Found");
    }
    public ResourceNotFoundException(String message) {
        super(message);

    }

}
