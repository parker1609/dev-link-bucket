package com.codemcd.myarchive.domain.exception;

public class NotFoundLinkTypeException extends RuntimeException {
    private static final String MESSAGE = "존재하지 않는 타입입니다.";

    public NotFoundLinkTypeException() {
        super(MESSAGE);
    }
}
