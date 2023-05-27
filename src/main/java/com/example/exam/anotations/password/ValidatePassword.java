package com.example.exam.anotations.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidatePassword {

    String message() default "Password and Confirm password not matches";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
