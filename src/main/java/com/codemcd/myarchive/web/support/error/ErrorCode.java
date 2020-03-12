package com.codemcd.myarchive.web.support.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    /* Common Error */
    INVALID_INPUT_VALUE(400, "C001", "Invalid input value"),
    SERVER_ERROR(500, "C002", "Server error")
    ;

    private int status;
    private String errorCode;
    private String message;
}
