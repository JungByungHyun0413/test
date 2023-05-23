package org.koreait.boardboot.models.board;

import org.koreait.boardboot.controller.board.BoardForm;
import org.koreait.boardboot.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class BoardSaveValidator implements Validator<BoardForm> {
    @Override
    public void check(BoardForm boardForm) {
        requiredCheck(boardForm.getSubject(), new BoardValidationException("제목을 입력하세요"));

        requiredCheck(boardForm.getContent(), new BoardValidationException("내용을 입력하세요"));
    }
}
