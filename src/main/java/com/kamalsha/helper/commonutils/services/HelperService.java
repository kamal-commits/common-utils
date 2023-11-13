package com.kamalsha.helper.commonutils.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.log4j.Log4j2;
// IOException
import java.io.IOException;

/**
 * A utility service providing various helper methods for common tasks.
 */
@Service
@Log4j2
public class HelperService {

  private static final String NA = "NA";
  private static final String DATE_FORMAT_INPUT = "dd/MM/yyyy";

  /**
   * Converts a string to a double value.
   *
   * @param str the string to be converted
   * @return the double value of the string, or 0 if the string cannot be
   *         converted
   */
  public double toDouble(String str) {
    return toDouble(str, 2); // Default value for decimalPlaces is 2
  }

  public double toDouble(String str, Integer decimalPlaces) {

    if (str == null || str.trim().isEmpty() || str.equals("NA") || str.equalsIgnoreCase("")) {
      return 0;
    }

    String decimalPlaceValue = "#.##";

    if (decimalPlaces != null && decimalPlaces > 0) {
      decimalPlaceValue = "#.";
      for (int i = 0; i < decimalPlaces; i++) {
        decimalPlaceValue += "#";
      }
    }

    String newValue = str.replaceAll("[^\\d.]", "");
    if (newValue.isEmpty()) {
      return 0;
    }
    if (newValue.contains(".")) {
      newValue = newValue.substring(0, newValue.indexOf("."));
    }

    double number = Double.parseDouble(newValue);
    DecimalFormat df = new DecimalFormat(decimalPlaceValue);
    number = Double.parseDouble(df.format(number));

    if (str.contains("-")) {
      number *= -1;
    }

    return number;

  }

  /**
   * Converts a string to an integer.
   *
   * @param value the string to be converted
   * @return the integer value of the string, or 0 if the string cannot be
   *         converted
   */
  public Integer toInt(String value) {
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
  public Map<String, Object> toHashMap(Object obj) {
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
  public String toDate(String date) {
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

  // To Capitalize first letter of string
  public String capitalize(String str) {
    if (str == null || str.isEmpty()) {
      return str;
    }

    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }

  /**
   * Parses a JSON string and returns a Map object representing the JSON object.
   *
   * @param jsonString the JSON string to be parsed
   * @return a Map object representing the JSON object
   */
  public Map<String, Object> getJsonObject(String jsonString) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    Map<String, Object> jsonObj = null;
    try {
      jsonObj = mapper.readValue(jsonString, Map.class);
    } catch (JsonMappingException e) {
      log.error("Error in getJsonObject: " + e.getMessage());
    } catch (JsonProcessingException e) {
      log.error("Error in getJsonObject: " + e.getMessage());
    } catch (IOException e) {
      log.error("Error in getJsonObject: " + e.getMessage());
    }
    return jsonObj;
  }

  /**
   * Returns the value of the nested key in the given map.
   *
   * @param <T>  the type of the value to be returned
   * @param map  the map to search for the nested key
   * @param keys the keys to search for the nested value
   * @return the value of the nested key in the given map
   */
  public static <T> T getNestedValue(Map<String, Object> map, String... keys) {
    Object value = null;

    if (map == null) {
      return null;
    }

    for (String key : keys) {
      if (map == null) {
        break;
      }

      // If key is not found, return null
      if (!map.containsKey(key)) {
        value = null;
        break;
      }

      value = map.get(key);

      if (value instanceof Map) {
        // noinspection unchecked
        map = (Map<String, Object>) value;
      } else {
        break; // Stop if the value is not a Map
      }
    }

    // noinspection unchecked
    return (T) value;
  }

}
