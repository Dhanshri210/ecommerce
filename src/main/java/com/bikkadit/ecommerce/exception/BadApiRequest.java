package com.bikkadit.ecommerce.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BadApiRequest extends RuntimeException{

    public BadApiRequest(String message){
        super(message);
    }
   public BadApiRequest(){
        super("Bad Request !!");
   }
}
