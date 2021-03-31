package com.wss.remediation;

import lombok.NonNull;

import java.io.File;
import java.io.IOException;

/**
 * Remediation Solver static class written by WhiteSource with the community ❤. Here you can find
 * wrapper functions to secure unsafe operations in your code.
 */
public class WhiteSourceDirectoryManager {


  /**
   * Checking if a specific file is in his relevant folder.
   *
   * @param filePath The file Path.
   * @param baseDirPath The base folder of the specific file.
   * @return True - if the file is outside the base dir, False - otherwise.
   */
  public static boolean isFileOutsideDir(
      @NonNull final String filePath, @NonNull final String baseDirPath) throws IOException {
    File file = new File(filePath);
    File baseDir = new File(baseDirPath);
    return !file.getCanonicalPath().startsWith(baseDir.getCanonicalPath());
  }

}
