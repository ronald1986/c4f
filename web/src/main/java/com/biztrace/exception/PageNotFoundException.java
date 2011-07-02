package com.biztrace.exception;

public class PageNotFoundException extends BusinessException {
    private static final long serialVersionUID = 5945882389711954190L;

    public PageNotFoundException() {
        super(1000001L);
    }
}
