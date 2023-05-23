package com.rainyjune.board.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_REFRESH_TOKEN(HttpStatus.FORBIDDEN, "INVALID_REFRESH_TOKEN", "Refresh token is invalid");

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
