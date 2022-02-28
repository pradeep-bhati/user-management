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
import javax.validation.constraints.Size;

@NotBlank(message = "Last name should not be blank.")
@Size(min= 3, max = 20, message = "Last name should be minimum 3 and maximum 15 in length.")
@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should have alpahbets only.")
@Target({ TYPE, METHOD, FIELD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { })
@Documented
public @interface ValidLastName {
	String message() default "Last name should be alphabets, minimum 3 and maximum 15 in length.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { }; 
}
