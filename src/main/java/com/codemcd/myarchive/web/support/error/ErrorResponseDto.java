package com.codemcd.myarchive.web.support.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ErrorResponseDto {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    public ErrorResponseDto(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.errors = new ArrayList<>();
        this.code = errorCode.getErrorCode();
    }

    public ErrorResponseDto(ErrorCode errorCode, List<FieldError> errors) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.errors = errors;
        this.code = errorCode.getErrorCode();
    }

    public ErrorResponseDto(ErrorCode errorCode, BindingResult bindingResult) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.errors = FieldError.of(bindingResult);
        this.code = errorCode.getErrorCode();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
