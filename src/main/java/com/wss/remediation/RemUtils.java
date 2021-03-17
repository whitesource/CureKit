package com.wss.remediation;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.lang3.SystemUtils;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.AbstractCharacterCodec;
import org.owasp.esapi.codecs.UnixCodec;
import org.owasp.esapi.codecs.WindowsCodec;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemUtils {

  @SneakyThrows
  public static String encodeForOS(@NonNull String param) {
    if (SystemUtils.IS_OS_WINDOWS) {
      return getEncodeForOS(new WindowsCodec(), param);
    } else if (SystemUtils.IS_OS_UNIX) {
      return getEncodeForOS(new UnixCodec(), param);
    }

    throw new UnsupportedOperationException("Unsupported encoder for operating system");
  }

  private static String getEncodeForOS(AbstractCharacterCodec codec, String param) {
    return ESAPI.encoder().encodeForOS(codec, param);
  }

  public static boolean isFileInsideDir(@NonNull String filePath, @NonNull String baseDirPath) throws IOException {
    File file = new File(filePath);
    File baseDir = new File(baseDirPath);
    return file.getCanonicalPath()
            .startsWith(baseDir.getCanonicalPath());
  }

  public static String[] encodeForLog(@NonNull Object... contents) {

    List<String> results = new ArrayList<>();

    for (Object content : contents) {
      results.add(encodeForLogCommands(content));
    }

    return results.toArray(String[]::new);

  }

  public static String encodeForLogCommands(@NonNull Object content) {
    return content
            .toString()
            .replaceAll("[\n|\r|\t]", "_")
            .replaceAll("<", "&lt")
            .replaceAll(">", "&gt");
  }
}
