package org.koreait.boardboot.models.board;

import org.koreait.boardboot.controller.board.BoardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardSaveService {
    @Autowired
    private BoardSaveValidator saveValidator;
    public void save(BoardForm boardForm){
        saveValidator.check(boardForm);
    }
}
