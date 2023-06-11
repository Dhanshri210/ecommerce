package com.bikkadit.ecommerce.exception;

public class BadApiRequest extends RuntimeException{

    public BadApiRequest(String message){
        super(message);
    }
   public BadApiRequest(){
        super("Bad Request !!");
   }
}
