package com.whitesource.remediation;

import static com.whitesource.remediation.Encoder.*;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class EncoderTest {

  @Test
  void forOsCommand_operatingSystem_successfullyWithResult() {
    String input = "!windows123";
    String expected = "";
    if (SystemUtils.IS_OS_WINDOWS) {
      expected = "^!windows123";
    } else if (SystemUtils.IS_OS_UNIX) {
      expected = "\\!windows123";
    }

    String actual = forOsCommand(input);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void forOsCommand_null_successfully() {
    Assertions.assertThrows(NullPointerException.class, () -> forOsCommand(null));
  }

  @Test
  @Disabled
  void multiLogContentEncoder_oneElementArray_successfullyWithResult() {

    String[] oneElementStringArray = new String[] {"Barbi\n\r\t><"};
    String[] expectedEncodedArray = new String[] {"Barbi___&gt&lt"};

    String[] actualEncodedArray = multiLogContentEncoder(oneElementStringArray);
    Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
  }

  @Test
  @Disabled
  void multiLogContentEncoder_threeElementArray_successfullyWithResult() {

    String[] threeElementStringArray = new String[] {"I\n\r\t", "am>", "Barbi<"};
    String[] expectedEncodedArray = new String[] {"I___", "am&gt", "Barbi&lt"};

    String[] actualEncodedArray = multiLogContentEncoder(threeElementStringArray);
    Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
  }

  @Test
  void multiLogContentEncoder_null_successfully() {

    Assertions.assertThrows(NullPointerException.class, () -> multiLogContentEncoder(null));
  }

  @Test
  void logContentEncoder_fullEncodingCapabilities_successfullyWithResult() {

    String barbi = "Barbi\n\r\t><";
    String expected = "Barbi___&gt&lt";

    String actual = logContentEncoder(barbi);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void LogContentEncoder_null_successfully() {

    Assertions.assertThrows(NullPointerException.class, () -> logContentEncoder(null));
  }
}
