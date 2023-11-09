package com.kamalsha.helper.commonutils.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

/**
 * A utility service providing various helper methods for common tasks.
 */
@Service
@Log4j2
public class Helper {

  private static final String NA = "NA";
  private static final String DATE_FORMAT_INPUT = "dd/MM/yyyy";
  private static final String DATE_FORMAT_OUTPUT = "yyyy-MM-dd";

  /**
   * Converts a string to a double value.
   *
   * @param str the string to be converted
   * @return the double value of the string, or 0 if the string cannot be
   *         converted
   */
  public double getDoubleValue(String str) {
    double result = 0;
    try {
      if (str != null) {
        String in = str.trim();
        if (!in.isEmpty()) {
          result = Double.parseDouble(in);
        }
      }
    } catch (NumberFormatException e) {
      log.error("Error in getDoubleValue: Unable to convert string to double. Input: " + str, e);
    }
    return result;
  }

  /**
   * Converts a string to an integer.
   *
   * @param value the string to be converted
   * @return the integer value of the string, or 0 if the string cannot be
   *         converted
   */
  public Integer getInteger(String value) {
    if (value == null || value.isEmpty() || value.equals("NA") || value.equalsIgnoreCase("")) {
      return 0;
    }

    String newValue = value.replaceAll("[^\\d.]", "");
    if (newValue.isEmpty()) {
      return 0;
    }
    if (newValue.contains(".")) {
      newValue = newValue.substring(0, newValue.indexOf("."));
    }

    int number = Integer.parseInt(newValue);
    if (value.contains("-")) {
      number *= -1;
    }

    return number;
  }

  /**
   * Converts an object to a HashMap containing its fields and their values.
   *
   * @param obj the object to be converted
   * @return a HashMap containing the object's fields and their values
   */
  public Map<String, Object> convertObjectToHashMap(Object obj) {
    HashMap<String, Object> map = new HashMap<>();
    for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      try {
        map.put(field.getName(), field.get(obj));
      } catch (IllegalArgumentException | IllegalAccessException e) {
        log.error("Error in convertObjectToHashMap: " + e.getMessage());
      }
    }
    return map;
  }

  /**
   * Formats a date string from the "dd/MM/yyyy" format to "yyyy-MM-dd".
   *
   * @param date the date string to be formatted
   * @return the formatted date string, or the original string if parsing fails
   */
  public String formatDate(String date) {
    String formattedDate = "";
    if (isValidDateFormat(date)) {
      try {
        SimpleDateFormat inputFormat = new SimpleDateFormat(DATE_FORMAT_INPUT);
        inputFormat.setLenient(false); // Add this line to ensure strict parsing
        Date parsedDate = inputFormat.parse(date);

        // Choose the output format based on the length of elements in the input
        formattedDate = getFormattedDateBasedOnElements(parsedDate);

      } catch (ParseException e) {
        log.error("Error in formatDate: Unable to parse date. Input: " + date, e);
      }
    }

    return formattedDate;
  }

  private boolean isValidDateFormat(String date) {
    return date != null && !date.isEmpty() && !NA.equals(date) && !"".equals(date);
  }

  private String getFormattedDateBasedOnElements(Date parsedDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(parsedDate);

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    if (String.valueOf(year).length() == 4) {
      return String.format("%04d-%02d-%02d", year, month, day);
    } else if (String.valueOf(day).length() == 4) {
      return String.format("%04d-%02d-%02d", day, month, year);
    }

    return null; // Return null for invalid date formats
  }

  /**
   * Checks if the given value is a string.
   *
   * @param value the value to check
   * @return true if the value is a string, false otherwise
   */
  public boolean isString(Object value) {
    return value instanceof String;
  }

  /**
   * Checks if the given value is numeric.
   *
   * @param value the value to check
   * @return true if the value is numeric, false otherwise
   */
  public boolean isNumeric(Object value) {
    if (value == null) {
      return false;
    }

    Class<?> valueType = value.getClass();

    return valueType.equals(BigDecimal.class) ||
        valueType.equals(Integer.class) ||
        valueType.equals(Double.class) ||
        valueType.equals(Float.class) ||
        valueType.equals(Long.class) ||
        valueType.equals(Short.class);
  }

  /**
   * Checks if the given value is a date.
   *
   * @param value the value to check
   * @return true if the value is a date, false otherwise
   */
  public boolean isDate(Object value) {
    return value instanceof Date;
  }

}
