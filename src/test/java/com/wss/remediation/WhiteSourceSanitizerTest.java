package com.wss.remediation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.owasp.esapi.ESAPI;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


class WhiteSourceSanitizerTest {


    @BeforeAll
    static void setUp() {
    }

    @Test
    void OSParameterEncoder_windows_successfullyWithResult() {
        Path cwd = Paths.get("esapiConfigurations").toAbsolutePath();
        System.out.println(cwd);
        ESAPI.securityConfiguration().setResourceDirectory(cwd.toString());

        String input = "windows";
        String expected = "asd";

        var actual = WhiteSourceSanitizer.OSParameterEncoder(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void OSParameterEncoder_unix_successfullyWithResult() {

        String input = "unix";
        String expected = "asd";

        var actual = WhiteSourceSanitizer.OSParameterEncoder(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void OSParameterEncoder_null_successfully() {

        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.OSParameterEncoder(null));

    }

    @Test
    void isFileInDir_outside_successfullyWithResult() throws IOException {

        Path sourcePath = Paths.get("src").toAbsolutePath();
        Path cwd = Paths.get("").toAbsolutePath();

        var isOutside = WhiteSourceSanitizer.isFileOutsideDir(cwd.toString(), sourcePath.toString());

        Assertions.assertTrue(isOutside);
    }

    @Test
    void isFileInDir_inside_successfullyWithResult() throws IOException {

        Path sourcePath = Paths.get("src").toAbsolutePath();
        Path cwd = Paths.get("").toAbsolutePath();

        var isOutside = WhiteSourceSanitizer.isFileOutsideDir(sourcePath.toString(), cwd.toString());

        Assertions.assertFalse(isOutside);
    }

    @Test
    void isFileInDir_null_successfully() {

        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.isFileOutsideDir(null, null));

        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.isFileOutsideDir("file-path-place-holder", null));

        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.isFileOutsideDir(null, "base-dir-place-holder"));

    }

    @Test
    void multiLogContentEncoder_oneElementArray_successfullyWithResult() {

        String[] oneElementStringArray = new String[]{"Barbi\n\r\t><"};
        String[] expectedEncodedArray = new String[]{"Barbi___&gt&lt"};

        var actualEncodedArray = WhiteSourceSanitizer.multiLogContentEncoder(oneElementStringArray);

        Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
    }

    @Test
    void multiLogContentEncoder_threeElementArray_successfullyWithResult() {

        String[] threeElementStringArray = new String[]{"I\n\r\t", "am>", "Barbi<"};
        String[] expectedEncodedArray = new String[]{"I___", "am&gt", "Barbi&lt"};

        var actualEncodedArray = WhiteSourceSanitizer.multiLogContentEncoder(threeElementStringArray);

        Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
    }

    @Test
    void multiLogContentEncoder_null_successfully() {

        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.multiLogContentEncoder(null));

    }

    @Test
    void logContentEncoder_fullEncodingCapabilities_successfullyWithResult() {

        var barbi = "Barbi\n\r\t><";
        var expected = "Barbi___&gt&lt";

        var actual = WhiteSourceSanitizer.logContentEncoder(barbi);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void LogContentEncoder_null_successfully() {

        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.logContentEncoder(null));

    }

}