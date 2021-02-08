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

  public static String[] encodeForLog(Object... content) {
    String[] result = new String[content.length];
    for (int i = 0; i < content.length; i++) {
      result[i] = RemUtils.encodeForLog(content[i]);
    }
    return result;
  }

  public static String encodeForLog(Object content) {
    return content
        .toString()
        .replaceAll("[\n|\r|\t]", "_")
        .replaceAll("<", "&lt")
        .replaceAll(">", "&gt");
  }
}
