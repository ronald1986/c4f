package com.biztrace.util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.Assert;

import com.biztrace.exception.BusinessException;

public class MD5Encoder {
    private static MD5Encoder instance = new MD5Encoder();
    private MD5Encoder() {
    }

    public static MD5Encoder getInstance() {
        return instance;
    }

    /**
     * Encode input source with MD5 algorithm
     * @param source source string
     * @param salt optional salt to mix
     */
    public String encode(final String source, final Object salt) {
        Assert.notNull(source);

        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(1000003L);
        }
        ByteBuffer b = ByteBuffer.wrap(source.getBytes());
        if (salt != null) {
            byte[] saltBytes = String.valueOf(salt).getBytes();
            int origLen = b.capacity();
            for (int i = 0; i < saltBytes.length; i++) {
                b.put(origLen / 2, saltBytes[i]);
            }
        }
        md5.update(b);

        byte[] result = md5.digest();
        StringBuffer encodedValue = new StringBuffer();
        for (byte each : result) {
            int a = each & 0xFF;
            if (a < 0x10) {
                encodedValue.append('0');
            }
            encodedValue.append(Integer.toHexString(a));
        }
        return encodedValue.toString();
    }
}