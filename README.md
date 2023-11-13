# HelperService Readme

`HelperService` is a utility service providing various helper methods for common tasks. It includes functionalities for converting data types, formatting dates, checking data types, and more. The service is implemented in Java and utilizes popular libraries like Spring and Jackson.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Methods](#methods)
- [Example](#example)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

- Java Development Kit (JDK)
- Spring Framework (for `@Service` annotation)
- Jackson Library (for JSON processing)
- Log4j2 (for logging)

## Installation

1. Clone the repository to your local machine:

```bash
git clone https://github.com/kamal-commits/common-utils.git
```

2. Open the project in your favorite Java IDE.

3. Ensure that the required dependencies are properly configured.

## Usage

The `HelperService` class provides various utility methods that can be used in your Java project. To use the service, instantiate an object of `HelperService` and call the desired methods.

```java
// Example usage
HelperService helperService = new HelperService();

// Convert a string to a double
double convertedValue = helperService.toDouble("123.45");

// Format a date string
String formattedDate = helperService.toDate("01/23/2023");

// Check if a value is numeric
boolean isNumeric = helperService.isNumeric(42);

```

## Methods

### `toDouble(String str)`

Converts a string to a double value. If the string cannot be converted, it returns 0.

### `toDouble(String str, Integer decimalPlaces)`

Converts a string to a double value with a specified number of decimal places.

### `toInt(String value)`

Converts a string to an integer. If the string cannot be converted, it returns 0.

### `toHashMap(Object obj)`

Converts an object to a HashMap containing its fields and their values.

### `toDate(String date)`

Formats a date string from the "dd/MM/yyyy" format to "yyyy-MM-dd".

### `isString(Object value)`

Checks if the given value is a string.

### `isNumeric(Object value)`

Checks if the given value is numeric.

### `isDate(Object value)`

Checks if the given value is a date.

### `capitalize(String str)`

Capitalizes the first letter of a string.

### `getJsonObject(String jsonString)`

Parses a JSON string and returns a Map object representing the JSON object.

### `getNestedValue(Map<String, Object> map, String... keys)`

Returns the value of the nested key in the given map.

## Example

```java
// Example usage
HelperService helperService = new HelperService();

// Convert a string to a double
double convertedValue = helperService.toDouble("123.45");

// Format a date string
String formattedDate = helperService.toDate("01/23/2023");

// Check if a value is numeric
boolean isNumeric = helperService.isNumeric(42);

```

## Contributing

Contributions are welcome! Feel free to submit issues, suggest improvements, or open pull requests.

## License

This project is licensed under the [MIT License](LICENSE).

---
