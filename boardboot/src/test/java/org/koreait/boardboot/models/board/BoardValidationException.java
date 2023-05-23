package org.koreait.boardboot.models.board;

public class BoardValidationException extends RuntimeException{
    public BoardValidationException(String message) {
        super(message);
    }
}
