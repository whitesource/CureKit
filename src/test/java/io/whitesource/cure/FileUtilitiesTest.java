package io.whitesource.cure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileUtilitiesTest {

  @Test
  void isFileInDir_outside_successfullyWithResult() throws IOException {
    Path sourcePath = Paths.get("src").toAbsolutePath();
    Path cwd = Paths.get("").toAbsolutePath();

    boolean isOutside = FileUtilities.isFileOutsideDir(cwd.toString(), sourcePath.toString());
    Assertions.assertTrue(isOutside);
  }

  @Test
  void isFileInDir_inside_successfullyWithResult() throws IOException {
    Path sourcePath = Paths.get("src").toAbsolutePath();
    Path cwd = Paths.get("").toAbsolutePath();

    boolean isOutside = FileUtilities.isFileOutsideDir(sourcePath.toString(), cwd.toString());
    Assertions.assertFalse(isOutside);
  }

  @Test
  void isFileInDir_null_successfully() {
    Assertions.assertThrows(
            NullPointerException.class, () -> FileUtilities.isFileOutsideDir(null, null));

    Assertions.assertThrows(
            NullPointerException.class,
            () -> FileUtilities.isFileOutsideDir("file-path-place-holder", null));

    Assertions.assertThrows(
            NullPointerException.class,
            () -> FileUtilities.isFileOutsideDir(null, "base-dir-place-holder"));
  }

  @Test
  void normalize_validInput_successfullyWithResult() throws IOException {
    String validInput = "./In/../Valid/Un/../Normalized/./Path";
    String expectedResult = "Valid" + File.separator + "Normalized" + File.separator + "Path";

    String actualResult = FileUtilities.normalize(validInput);
    Assertions.assertEquals(expectedResult, actualResult);
  }

  @Test
  void normalize_null_successfully() {
    Assertions.assertNull(FileUtilities.normalize(null));
  }
}
