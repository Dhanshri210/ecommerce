package com.bikkadit.ecommerce.exception;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
public class ResourceNotFoundException extends RuntimeException{

    String resourceName;

    public ResourceNotFoundException(String resourceName) {
        super(String.format("%s Not Found With %s : %s", resourceName));
        this.resourceName = resourceName;

    }

}
