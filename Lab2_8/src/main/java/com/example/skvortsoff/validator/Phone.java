package com.example.skvortsoff.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "{Invalid phone, example: 29-123-45-67 (Belarus) 719-123-45-67 (Russia)}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

