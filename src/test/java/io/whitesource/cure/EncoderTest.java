package io.whitesource.cure;

import static io.whitesource.cure.Encoder.*;

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
  void forCrlf_htmlContent_successfullyWithResult() {
    String input = "a1\rb2";
    String expected = "a1b2";

    String actual = forCrlf(input);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void forCrlf_null_successfully() {
    Assertions.assertThrows(NullPointerException.class, () -> forCrlf(null));
  }

  @Test
  @Disabled
  void forLogContent_oneElementArray_successfullyWithResult() {

    String[] oneElementStringArray = new String[] {"Barbi\n\r\t><"};
    String[] expectedEncodedArray = new String[] {"Barbi___&gt&lt"};

    String[] actualEncodedArray = Encoder.forLogContent(oneElementStringArray);
    Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
  }

  @Test
  @Disabled
  void forLogContent_threeElementArray_successfullyWithResult() {

    String[] threeElementStringArray = new String[] {"I\n\r\t", "am>", "Barbi<"};
    String[] expectedEncodedArray = new String[] {"I___", "am&gt", "Barbi&lt"};

    String[] actualEncodedArray = Encoder.forLogContent(threeElementStringArray);
    Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
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
  void forHtmlAttributeXss_successfullyWithResult_array() {
    char[] chars = {'a', 'b', 'c', 'd', 'e', '<', '>'};
    String expected = "abcde&lt;>";

    String actual = forHtmlAttributeXss(chars);
    Assertions.assertEquals(expected, actual);
  }
}
