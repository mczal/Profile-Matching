package com.spk.dto.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Gl552 on 12/24/2016.
 */
@Documented
@Constraint(validatedBy = StringOfNumbersValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface StringOfNumbers {

  /**
   * Groups and payload parameters are unused parameter.
   */
  Class<?>[] groups() default {};

  String message() default "Must filled with only numbers";

  Class<? extends Payload>[] payload() default {};

  StringOfNumbersType value() default StringOfNumbersType.ALL;
}
