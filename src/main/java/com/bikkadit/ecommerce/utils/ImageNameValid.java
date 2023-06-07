package com.bikkadit.ecommerce.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public  @interface ImageNameValid {

    //Error Message
        String message() default "Invalid Image Name/Details";

        //represent group of contraint
        Class<?>[] groups() default { };

//additional information about annotation
        Class<? extends Payload>[] payload() default { };





}
