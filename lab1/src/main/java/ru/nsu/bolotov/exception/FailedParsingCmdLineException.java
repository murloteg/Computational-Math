package ru.nsu.bolotov.exception;

public class FailedParsingCmdLineException extends RuntimeException {
    public FailedParsingCmdLineException(String message) {
        super(message);
    }
}
