package com.example.register.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = NameConstraintValidator.class)
@Retention(RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
public @interface ValidName {
	
	String message() default "Invalid input";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
