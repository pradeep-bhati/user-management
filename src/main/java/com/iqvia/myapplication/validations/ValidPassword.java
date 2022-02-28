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

@NotBlank(message = "Password can not be blank.")
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password should be of at  least 8 characters containing "
		+ "one lower case alphabet, one upper case alphabet,one numeric and one special character.")
@Target({ TYPE, METHOD, FIELD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { })
@Documented
public @interface ValidPassword {
	String message() default "Password should be of at  least 8 characters containing one lower case alphabet,"
			+ " one upper case alphabet,one numeric and one special character.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { }; 
}
