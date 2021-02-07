package com.wss.remediation;

import java.io.File;
import java.io.IOException;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.UnixCodec;
import org.owasp.esapi.codecs.WindowsCodec;

public class RemUtils {

  public RemUtils() {};

  public static String encodeForOS(String param) {
    if (System.getProperty("os.name").toLowerCase().contains("win")) {
      return ESAPI.encoder().encodeForOS(new WindowsCodec(), param);
    } else {
      return ESAPI.encoder().encodeForOS(new UnixCodec(), param);
    }
  }

  public static boolean isFileInsideDir(String filePath, String baseDirPath) throws IOException {
    File file = new File(filePath);
    File baseDir = new File(baseDirPath);
    return file.getCanonicalPath().startsWith(baseDir.getCanonicalPath());
  }
}
