package com.example.skvortsoff.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public  class PhoneValidator implements ConstraintValidator<FieldPhone,String> {

    @Override
    public void initialize(FieldPhone param) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext ctx) {
        if (phone == null) {
            return false;
        }

        // dd-ddd-dd-dd (Belarus)
        if (phone.matches("\\d{2}[-]\\d{3}[-]\\d{2}[-]\\d{2}")) return true;
            // ddd-ddd-dd-dd (Russia)
        else return phone.matches("\\d{3}[-]\\d{3}[-]\\d{2}[-]\\d{2}");
    }
}

