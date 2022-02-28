package com.iqvia.myapplication.validations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NotBlank(message = "Role can not be blank.")
@Pattern(regexp = "^(ROLE_[A-Z]+$)", message = "Role should be in ROLE_ADMIN format")
@Target({ TYPE, METHOD, FIELD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { })
@Documented
public @interface ValidRole {
	String message() default "Role can't be blank and format should be ROLE_ADMIN";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { }; 
}
