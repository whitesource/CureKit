package com.wss.remediation;

import lombok.NonNull;
import org.apache.commons.lang3.SystemUtils;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.UnixCodec;
import org.owasp.esapi.codecs.WindowsCodec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Remediation Solver static class written by WhiteSource & the community ❤.
 * Here you can find wrapper functions to secure unsafe operations in your code.
 */
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WhiteSourceSanitizer {

  WhiteSourceSanitizer() {
    Path cwd = Paths.get("esapiConfigurations").toAbsolutePath();
    System.out.println(cwd);
    ESAPI.securityConfiguration().setResourceDirectory(cwd.toString());
  }

  /**
   * Encoding operating system parameters.
   *
   * @param params The parameters for the operating systems.
   * @return Encoded parameters.
   */
  public static String OSParameterEncoder(@NonNull String params) throws UnsupportedOperationException {
    if (SystemUtils.IS_OS_WINDOWS) {
      return Utils.esapiEncoder(new WindowsCodec(), params);
    } else if (SystemUtils.IS_OS_UNIX) {
      return Utils.esapiEncoder(new UnixCodec(), params);
    }

    throw new UnsupportedOperationException("Unsupported encoder for operating system");
  }

  /**
   * Checking if a specific file is in his relevant folder.
   *
   * @param filePath    The file Path.
   * @param baseDirPath The base folder of the specific file.
   * @return True - if the file is outside the base dir, False - otherwise.
   */
  public static boolean isFileOutsideDir(@NonNull String filePath, @NonNull String baseDirPath) throws IOException {
    File file = new File(filePath);
    File baseDir = new File(baseDirPath);
    return !file.getCanonicalPath()
            .startsWith(baseDir.getCanonicalPath());
  }

  /**
   * Encoding content for logs.
   *
   * @param contents arrays {@link Object} contains all the contents.
   * @return encoded log content.
   */
  public static String[] multiLogContentEncoder( Object[] contents) {

    List<String> results = new ArrayList<>();

    for (Object content : contents) {
      results.add(logContentEncoder(content));
    }
    return results.toArray(String[]::new);

  }

  /**
   * Encoding content for logs.
   *
   * @param content {@link Object} contains the content.
   * @return encoded log content.
   */
  public static String logContentEncoder(@NonNull Object content) {
    return content
            .toString()
            .replaceAll("[\n|\r|\t]", "_")
            .replaceAll("<", "&lt")
            .replaceAll(">", "&gt");
  }

}

