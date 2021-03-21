package com.wss.remediation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


class WhiteSourceSanitizerTest {


    private String computerName;

    @BeforeEach
    void setUp() {
        this.computerName = System.getenv("COMPUTERNAME");
    }

    @Test
    void OSParameterEncoder_windows_successfullyWithResult() {
        // Arrange
        String input = "windows";
//        System.setProperty(System.getenv("COMPUTERNAME"), "windows");
        String expected = "asd";

        // Act
        var actual = WhiteSourceSanitizer.OSParameterEncoder(input);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void OSParameterEncoder_unix_successfullyWithResult() {
        // Arrange
        String input = "unix";
//        System.setProperty(System.getenv("COMPUTERNAME"), "windows");
        String expected = "asd";

        // Act
        var actual = WhiteSourceSanitizer.OSParameterEncoder(input);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void OSParameterEncoder_null_successfullyWithResult() {
        // Arrange

        // Act
        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.OSParameterEncoder(null));

        // Assert

    }

    @Test
    void isFileInDir_outside_successfullyWithResult() throws IOException {
        // Arrange
        Path sourcePath = Paths.get("src").toAbsolutePath();
        Path cwd = Paths.get("").toAbsolutePath();
        // Act
        var isOutside = WhiteSourceSanitizer.isFileOutsideDir(cwd.toString(), sourcePath.toString());

        // Assert
        Assertions.assertTrue(isOutside);
    }

    @Test
    void isFileInDir_inside_successfullyWithResult() throws IOException {
        // Arrange
        Path sourcePath = Paths.get("src").toAbsolutePath();
        Path cwd = Paths.get("").toAbsolutePath();
        // Act
        var isOutside = WhiteSourceSanitizer.isFileOutsideDir(sourcePath.toString(), cwd.toString());

        // Assert
        Assertions.assertFalse(isOutside);
    }

    @Test
    void isFileInDir_null_successfullyWithResult() {
        // Arrange

        // Act
        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.isFileOutsideDir(null, null));

        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.isFileOutsideDir("file-path-place-holder", null));

        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.isFileOutsideDir(null, "base-dir-place-holder"));

        // Assert

    }

    @Test
    void multiLogContentEncoder_oneElementArray_successfullyWithResult() {
        // Arrange
        String[] oneElementStringArray = new String[]{"Barbi"};
        String[] expectedEncodedArray = new String[]{"Barbi"};
        // Act
        var actualEncodedArray = WhiteSourceSanitizer.multiLogContentEncoder(oneElementStringArray);

        // Assert
        Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
    }

    @Test
    void multiLogContentEncoder_threeElementArray_successfullyWithResult() {
        // Arrange
        String[] threeElementStringArray = new String[]{"I", "am", "Barbi"};
        String[] expectedEncodedArray = new String[]{"I", "am", "Barbi"};
        // Act
        var actualEncodedArray = WhiteSourceSanitizer.multiLogContentEncoder(threeElementStringArray);

        // Assert
        Assertions.assertArrayEquals(expectedEncodedArray, actualEncodedArray);
    }

    @Test
    void multiLogContentEncoder_null_successfullyWithResult() {
        // Arrange

        // Act
        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.multiLogContentEncoder(null));

        // Assert

    }
    @Test
    void logContentEncoder_threeElementArray_successfullyWithResult() {
        // Arrange
        var barbi = "Barbi";
        var expected = "Barbi";
        // Act
        var actual = WhiteSourceSanitizer.logContentEncoder(barbi);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void LogContentEncoder_null_successfullyWithResult() {
        // Arrange

        // Act
        Assertions.assertThrows(NullPointerException.class,
                () -> WhiteSourceSanitizer.logContentEncoder(null));

        // Assert

    }

}