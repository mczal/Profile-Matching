package com.spk.dto.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Gl552 on 12/24/2016.
 */
public class StringOfNumbersValidator implements ConstraintValidator<StringOfNumbers, String> {

  private StringOfNumbersType stringOfNumbersType;

  @Override
  public void initialize(StringOfNumbers stringOfNumbers) {
    this.stringOfNumbersType = stringOfNumbers.value();
  }

  private boolean isAllNumbers(String value) {
    boolean check = true;
    for (int i = 0; i < value.length(); i++) {
      if (value.charAt(i) < 48 || value.charAt(i) > 57) {
        check = false;
        break;
      }
    }
    return check;
  }

  private boolean isContainAnyNumber(String value) {
    boolean check = false;
    for (int i = 0; i < value.length(); i++) {
      if (value.charAt(i) >= 48 && value.charAt(i) <= 57) {
        check = true;
        break;
      }
    }
    return check;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    switch (this.stringOfNumbersType) {
      case ALL:
        return isAllNumbers(value);
      case CONTAINS:
        return isContainAnyNumber(value);
    }
    return false;
  }
}
