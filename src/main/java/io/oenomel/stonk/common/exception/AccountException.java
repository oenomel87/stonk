package io.oenomel.stonk.common.exception;

public class AccountException extends Exception {

    private static final long serialVersionUID = -3104258548167415661L;

    public AccountException() {
        super();
    }

    public AccountException(String message) {
        super(message);
    }
}
