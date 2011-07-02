package com.biztrace.util;

import org.apache.commons.lang.StringUtils;
import org.safehaus.uuid.UUIDGenerator;

public final class GUIDGenerator {
    private static GUIDGenerator instance = new GUIDGenerator();

    private GUIDGenerator() {
    }
    public static GUIDGenerator getInstance() {
        return instance;
    }
    public String getGUID() {
        return StringUtils.replace(
        	UUIDGenerator.getInstance().generateRandomBasedUUID().toString(), "-", "");
    }
}