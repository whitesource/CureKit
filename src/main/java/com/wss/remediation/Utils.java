package com.wss.remediation;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.AbstractCharacterCodec;

public class Utils {

  static String esapiEncoder(final AbstractCharacterCodec codec, final String param) {
    return ESAPI.encoder().encodeForOS(codec, param);
  }
}
