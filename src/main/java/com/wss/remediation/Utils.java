package com.wss.remediation;

import lombok.NonNull;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.AbstractCharacterCodec;

public class Utils {


    static String EsapiEncoder(AbstractCharacterCodec codec, String param) {
        return ESAPI.encoder()
                .encodeForOS(codec, param);
    }

}
