package com.kamalsha.helper.commonutils.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class HelperTest {

  private final Helper helper = new Helper();

  @Test
  void testGetDoubleValue() {
    assertEquals(5.0, helper.getDoubleValue("5.0"));
    assertEquals(0.0, helper.getDoubleValue("invalid"));
    assertEquals(0.0, helper.getDoubleValue(""));
    assertEquals(0.0, helper.getDoubleValue(null));
    assertEquals(0.0, helper.getDoubleValue("5.0.0"));
  }

  @Test
  void testGetInteger() {
    assertEquals(10, helper.getInteger("10"));
    assertEquals(-5, helper.getInteger("-5"));
    assertEquals(0, helper.getInteger("invalid"));
    assertEquals(0, helper.getInteger(""));
    assertEquals(0, helper.getInteger(null));
    assertEquals(123456789, helper.getInteger("123,456,789"));
    assertEquals(-123456789, helper.getInteger("-123,456,789"));
  }

  @Test
  void testConvertObjectToHashMap() {
    // Assuming you have a sample object to test with
    SampleObject sampleObject = new SampleObject("field1", 42, true);
    assertEquals(3, helper.convertObjectToHashMap(sampleObject).size());
    assertEquals("field1", helper.convertObjectToHashMap(sampleObject).get("field1"));
    assertEquals(42, helper.convertObjectToHashMap(sampleObject).get("field2"));
    assertEquals(true, helper.convertObjectToHashMap(sampleObject).get("field3"));
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
  void testFormatDate() {
    assertEquals("2022-01-15", helper.formatDate("15/01/2022"));
    assertEquals("", helper.formatDate("invalid"));
    assertEquals("", helper.formatDate(""));
    assertEquals("", helper.formatDate(null));
    assertEquals("", helper.formatDate("15-01-2022"));
    assertEquals("", helper.formatDate("2022/01/15"));
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
}