package com.kamalsha.helper.commonutils.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.Date;

class HelperTest {

  private final HelperService helper = new HelperService();

  @Test
  void testtoDouble() {
    assertEquals(5.0, helper.toDouble("5.0"));
    assertEquals(0.0, helper.toDouble("invalid"));
    assertEquals(0.0, helper.toDouble(""));
    assertEquals(0.0, helper.toDouble(null));
    assertEquals(5.0, helper.toDouble("5.0.0"));
  }

  @Test
  void testtoInt() {
    assertEquals(10, helper.toInt("10"));
    assertEquals(-5, helper.toInt("-5"));
    assertEquals(0, helper.toInt("invalid"));
    assertEquals(0, helper.toInt(""));
    assertEquals(0, helper.toInt(null));
    assertEquals(123456789, helper.toInt("123,456,789"));
    assertEquals(-123456789, helper.toInt("-123,456,789"));
  }

  @Test
  void testConvertObjectToHashMap() {
    // Assuming you have a sample object to test with
    SampleObject sampleObject = new SampleObject("field1", 42, true);
    assertEquals(3, helper.toHashMap(sampleObject).size());
    assertEquals("field1", helper.toHashMap(sampleObject).get("field1"));
    assertEquals(42, helper.toHashMap(sampleObject).get("field2"));
    assertEquals(true, helper.toHashMap(sampleObject).get("field3"));
  }

  private static class SampleObject {
    private String field1;
    private int field2;
    private boolean field3;

    SampleObject(String field1, int field2, boolean field3) {
      this.field1 = field1;
      this.field2 = field2;
      this.field3 = field3;
    }
  }

  @Test
  void testtoDate() {
    assertEquals("2022-01-15", helper.toDate("15/01/2022"));
    assertEquals("", helper.toDate("invalid"));
    assertEquals("", helper.toDate(""));
    assertEquals("", helper.toDate(null));
    assertEquals("", helper.toDate("15-01-2022"));
    assertEquals("", helper.toDate("2022/01/15"));
  }

  @Test
  void testIsString() {
    assertTrue(helper.isString("hello"));
    assertFalse(helper.isString(123));
    assertFalse(helper.isString(null));
  }

  @Test
  void testIsNumeric() {
    assertTrue(helper.isNumeric(123));
    assertTrue(helper.isNumeric(123.45));
    assertFalse(helper.isNumeric("123"));
    assertFalse(helper.isNumeric(null));
  }

  @Test
  void testIsDate() {
    assertTrue(helper.isDate(new Date()));
    assertFalse(helper.isDate("2022-01-15"));
    assertFalse(helper.isDate(null));
  }

  @Test
  void testGetNestedValue() {
    Map<String, Object> map = new HashMap<>();
    map.put("key1", "value1");
    map.put("key2", new HashMap<String, Object>() {
      {
        put("key3", "value3");
        put("key4", new HashMap<String, Object>() {
          {
            put("key5", "value5");
          }
        });
      }
    });

    assertEquals("value1", helper.getNestedValue(map, "key1"));
    assertEquals("value3", helper.getNestedValue(map, "key2", "key3"));
    assertEquals("value5", helper.getNestedValue(map, "key2", "key4", "key5"));
    assertNull(helper.getNestedValue(map, "key2", "key4", "key6"));
    assertNull(helper.getNestedValue(map, "key3"));
    assertNull(helper.getNestedValue(null, "key1"));

  }

  @Test
  void testGetJsonObject() {

    String jsonString = "{\"name\":\"John\", \"age\":30, \"car\":null}";
    Map<String, Object> expected = new HashMap<>();
    expected.put("name", "John");
    expected.put("age", 30);
    expected.put("car", null);
    Map<String, Object> actual = helper.getJsonObject(jsonString);
    assertEquals(expected, actual);
  }
}