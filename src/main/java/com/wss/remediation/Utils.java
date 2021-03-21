package com.wss.remediation;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.AbstractCharacterCodec;
import org.owasp.esapi.codecs.UnixCodec;

public class Utils {


    static String EsapiEncoder(AbstractCharacterCodec codec, String param) {
        return ESAPI.encoder()
                .encodeForOS(new UnixCodec(), param);
    }

}
