package com.rishi.electronic.store.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {
//    @Override
//    public void initialize(ImageNameValid constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //Logic
        if(s.isBlank()){
            return false;
        }else {
            return true;
        }

    }
}
