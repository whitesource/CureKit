package com.wss.remediation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.owasp.esapi.ESAPI;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.wss.remediation.WhiteSourceDirectoryManager.isFileOutsideDir;

class WhiteSourceDirectoryManagerTest {

  @BeforeAll
  static void setUp() {
    Path cwd = Paths.get("esapiConfigurations").toAbsolutePath();
    ESAPI.securityConfiguration().setResourceDirectory(cwd.toString());
  }

  @Test
  void isFileInDir_outside_successfullyWithResult() throws IOException {

    Path sourcePath = Paths.get("src").toAbsolutePath();
    Path cwd = Paths.get("").toAbsolutePath();

    var isOutside = isFileOutsideDir(cwd.toString(), sourcePath.toString());

    Assertions.assertTrue(isOutside);
  }

  @Test
  void isFileInDir_inside_successfullyWithResult() throws IOException {

    Path sourcePath = Paths.get("src").toAbsolutePath();
    Path cwd = Paths.get("").toAbsolutePath();

    var isOutside = isFileOutsideDir(sourcePath.toString(), cwd.toString());

    Assertions.assertFalse(isOutside);
  }

  @Test
  void isFileInDir_null_successfully() {

    Assertions.assertThrows(
            NullPointerException.class, () -> isFileOutsideDir(null, null));

    Assertions.assertThrows(
            NullPointerException.class,
            () -> isFileOutsideDir("file-path-place-holder", null));

    Assertions.assertThrows(
            NullPointerException.class,
            () -> isFileOutsideDir(null, "base-dir-place-holder"));
  }

}
