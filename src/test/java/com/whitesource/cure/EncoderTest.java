package com.whitesource.cure;

import static com.whitesource.cure.Encoder.*;

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
  void forCrlfApache_htmlContent_successfullyWithResult() {
    String input = "a1\rb2";
    String expected = "a1b2";

    String actual = forCrlfApache(input);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void forCrlfApache_null_successfully() {
    Assertions.assertThrows(NullPointerException.class, () -> forCrlfApache(null));
  }

  @Test
  @Disabled
  void forMultiLogContent_oneElementArray_successfullyWithResult() {

    String[] oneElementStringArray = new String[] {"Barbi\n\r\t><"};
    String[] expectedEncodedArray = new String[] {"Barbi___&gt&lt"};

    String[] actualEncodedArray = forMultiLogContent(oneElementStringArray);
    Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
  }

  @Test
  @Disabled
  void forMultiLogContent_threeElementArray_successfullyWithResult() {

    String[] threeElementStringArray = new String[] {"I\n\r\t", "am>", "Barbi<"};
    String[] expectedEncodedArray = new String[] {"I___", "am&gt", "Barbi&lt"};

    String[] actualEncodedArray = forMultiLogContent(threeElementStringArray);
    Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
  }

  @Test
  void forMultiLogContent_null_successfully() {

    Assertions.assertThrows(NullPointerException.class, () -> forMultiLogContent(null));
  }

  @Test
  void forLogContent_fullEncodingCapabilities_successfullyWithResult() {

    String barbi = "Barbi\n\r\t><";
    String expected = "Barbi___&gt&lt";

    String actual = forLogContent(barbi);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void forLogContent_null_successfully() {

    Assertions.assertThrows(NullPointerException.class, () -> forLogContent(null));
  }

  @Test
  void forHtmlAttribute_successfullyWithResult_array() {
    char[] chars = {'a', 'b', 'c', 'd', 'e', '<', '>'};
    String expected = "abcde&lt;>";

    String actual = forHtmlAttribute(chars);
    Assertions.assertEquals(expected, actual);
  }
}
