package com.rainyjune.board.security.exception;

import org.hibernate.service.spi.ServiceException;

public class DuplicateUserException extends ServiceException {

    public DuplicateUserException(String message, Throwable root) {
        super(message, root);
    }

    public DuplicateUserException(String message) {
        super(message);
    }
}
