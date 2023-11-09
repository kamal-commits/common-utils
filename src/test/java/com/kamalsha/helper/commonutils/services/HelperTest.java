package com.kamalsha.helper.commonutils.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

  private final Helper helper = new Helper();

  @Test
  void testGetDoubleValue() {
    assertEquals(5.0, helper.getDoubleValue("5.0"));
    assertEquals(0.0, helper.getDoubleValue("invalid"));
  }

  @Test
  void testGetInteger() {
    assertEquals(10, helper.getInteger("10"));
    assertEquals(-5, helper.getInteger("-5"));
    assertEquals(0, helper.getInteger("invalid"));
  }

  @Test
  void testConvertObjectToHashMap() {
    // Assuming you have a sample object to test with
    SampleObject sampleObject = new SampleObject("field1", 42, true);
    assertEquals(3, helper.convertObjectToHashMap(sampleObject).size());
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
  }
}