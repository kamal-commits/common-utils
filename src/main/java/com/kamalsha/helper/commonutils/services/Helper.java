// package com.kamalsha.helper.commonutils.services;

// import java.math.BigDecimal;
// import java.text.SimpleDateFormat;
// import java.util.HashMap;
// import java.util.Map;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Calendar;
// import java.util.Date;

// import org.springframework.stereotype.Service;
// import lombok.extern.log4j.Log4j2;

// @Service
// @Log4j2
// public class Helper {

//   // Function to convert string to double
//   /**
//    * Converts a string to a double value.
//    * 
//    * @param str the string to be converted
//    * @return the double value of the string, or 0 if the string cannot be
//    *         converted
//    */
//   public double getDoubleValue(String str) {
//     double result = 0;
//     try {
//       result = Double.parseDouble(str);
//     } catch (Exception e) {
//       log.error("Error in convertStringToDouble: " + e.getMessage());
//     }
//     return result;
//   }

//   // Function to convert string to integer
//   public Integer getInteger(String value) {
//     // Constant for special case
//     final String NA = "NA";

//     if (value != null && !value.isEmpty() && !value.equals(NA)) {
//       // Remove non-numeric characters
//       value = value.replaceAll("[^\\d.]", "");

//       // Check for decimal point
//       if (value.contains(".")) {
//         value = value.substring(0, value.indexOf("."));
//       }

//       // Check for negative number
//       int multiplier = 1;
//       if (value.contains("-")) {
//         value = value.substring(1);
//         multiplier = -1;
//       }

//       // Parse and return the result
//       return Integer.parseInt(value) * multiplier;
//     } else {
//       return 0;
//     }
//   }

//   /**
//    * Converts an object to a HashMap.
//    * 
//    * @param obj the object to be converted
//    * @return a HashMap containing the object's fields and their values
//    */
//   public Map<String, Object> convertObjectToHashMap(Object obj) {
//     HashMap<String, Object> map = new HashMap<>();
//     for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
//       field.setAccessible(true);
//       try {
//         map.put(field.getName(), field.get(obj));
//       } catch (IllegalArgumentException | IllegalAccessException e) {
//         log.error("Error in convertObjectToHashMap: " + e.getMessage());
//       }
//     }
//     return map;
//   }

//   public String formatDate(String date) {
//     final String NA = "NA";
//     String formattedDate = "";

//     if (date != null && !date.isEmpty() && !date.equals(NA)) {
//       try {
//         // Parse the input date
//         SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
//         Date parsedDate = inputFormat.parse(date);

//         // Format the date as yyyy-MM-dd
//         SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//         formattedDate = outputFormat.format(parsedDate);

//       } catch (ParseException e) {
//         // Handle parsing exception
//         e.printStackTrace();
//       }
//     } else {
//       formattedDate = date;
//     }

//     return formattedDate;
//   }

//   public int getMonthNumber(String monthName) {
//     Map<String, Integer> monthMap = new HashMap<>();
//     monthMap.put("JANUARY", 0);
//     monthMap.put("FEBRUARY", 1);
//     monthMap.put("MARCH", 2);
//     monthMap.put("APRIL", 3);
//     monthMap.put("MAY", 4);
//     monthMap.put("JUNE", 5);
//     monthMap.put("JULY", 6);
//     monthMap.put("AUGUST", 7);
//     monthMap.put("SEPTEMBER", 8);
//     monthMap.put("OCTOBER", 9);
//     monthMap.put("NOVEMBER", 10);
//     monthMap.put("DECEMBER", 11);

//     // Default to -1 if the month name is not found
//     return monthMap.getOrDefault(monthName, -1);
//   }

//   public Date getStartDateOfMonth(String month, String year) {

//     int monthNumber = getMonthNumber(month.toUpperCase());
//     Calendar calendar = Calendar.getInstance();
//     calendar.set(Calendar.YEAR, Integer.parseInt(year));
//     calendar.set(Calendar.MONTH, monthNumber);

//     // Get Start Date of the month
//     calendar.set(Calendar.DAY_OF_MONTH, 1);
//     Date startDate = calendar.getTime();

//     return startDate;
//   }

//   // Convert Date to Strin

//   // Get End Date of the month
//   public Date getEndDateOfMonth(String month, String year) {

//     int monthNumber = getMonthNumber(month.toUpperCase());

//     Calendar calendar = Calendar.getInstance();
//     calendar.set(Calendar.YEAR, Integer.parseInt(year));
//     calendar.set(Calendar.MONTH, monthNumber);

//     // Get End Date of the month
//     calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//     Date endDate = calendar.getTime();
//     // //logger.info("End Date : " + endDate);

//     return endDate;
//   }

//   public String getCurrentMonth() {
//     SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

//     simpleformat = new SimpleDateFormat("MMMM");
//     String strMonth = simpleformat.format(new Date());
//     // logger.info("Current Month = " + strMonth.toUpperCase());

//     return strMonth.toUpperCase();
//   }

//   public HashMap<String, Object> getStartAndEndDateFromFinancialYear(String financialYear) {
//     HashMap<String, Object> response = new HashMap<>();
//     String[] financialYearArray = financialYear.split("-");
//     String startYear = financialYearArray[0];
//     String endYear = financialYearArray[1];
//     String startDate = startYear + "-04-01";
//     String endDate = endYear + "-03-31";
//     response.put("startDate", startDate);
//     response.put("endDate", endDate);
//     return response;
//   }

//   // Check if coming value is string or not
//   public boolean isString(Object value) {
//     try {
//       String str = (String) value;
//       return true;
//     } catch (Exception e) {
//       return false;
//     }
//   }

//   // Check if coming value is numeric or not
//   public boolean isNumeric(Object value) {
//     try {
//       BigDecimal bigDecimal = (BigDecimal) value;
//       return true;
//     } catch (Exception e) {
//       return false;
//     }
//   }

//   // Check if coming value is date or not
//   public boolean isDate(Object value) {
//     try {
//       Date date = (Date) value;
//       return true;
//     } catch (Exception e) {
//       return false;
//     }
//   }

// }

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
      result = Double.parseDouble(str);
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
  public int getInteger(String value) {
    if (value != null && !value.isEmpty() && !value.equals(NA)) {
      value = value.replaceAll("[^\\d.]", "");
      if (value.contains(".")) {
        value = value.substring(0, value.indexOf("."));
      }
      int multiplier = 1;
      if (value.contains("-")) {
        value = value.substring(1);
        multiplier = -1;
      }
      try {
        return Integer.parseInt(value) * multiplier;
      } catch (NumberFormatException e) {
        log.error("Error in getInteger: Unable to convert string to integer. Input: " + value, e);
      }
    }
    return 0;
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
    return value instanceof BigDecimal;
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
