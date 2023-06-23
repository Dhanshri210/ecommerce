package com.bikkadit.ecommerce.utils;

import com.bikkadit.ecommerce.controller.UserController;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {

    private static Logger logger = LoggerFactory.getLogger(ImageNameValidator.class);


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        logger.info("Message From is Valid annotaton : {}",value);
        //logic
        if (value.isEmpty()){
            return false;
    }
            else{
                return true;
        }
        }
    }

