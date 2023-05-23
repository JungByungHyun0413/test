package org.koreait.boardboot.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.boardboot.controller.board.BoardForm;
import org.koreait.boardboot.models.board.Board;
import org.koreait.boardboot.models.board.BoardDao;
import org.koreait.boardboot.models.board.BoardSaveService;
import org.koreait.boardboot.models.board.BoardValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardSaveTest {

    @Autowired
    private BoardSaveService saveService;

    @Autowired
    private BoardDao boardDao;

    BoardForm getBoardForm(){
        BoardForm boardForm = new BoardForm();
        boardForm.setSubject("제목");
        boardForm.setContent("내용");

        return boardForm;
    }
    @Test
    @DisplayName("게시판 등록 성공시 예외 없음")
    void BoardSuccessTest(){
        assertDoesNotThrow(()->{
            BoardForm boardForm = getBoardForm();
           saveService.save(boardForm);
        });
    }

    @Test
    @DisplayName("필수항목 체크 실패시 예외 발생")
    void requiredCheckTest(){
        assertAll(
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getBoardForm();
                    boardForm.setSubject(null);
                    saveService.save(boardForm);
                }),

                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getBoardForm();
                    boardForm.setSubject("    ");
                    saveService.save(boardForm);
                }),

                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getBoardForm();
                    boardForm.setContent(null);
                    saveService.save(boardForm);
                }),
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getBoardForm();
                    boardForm.setContent("    ");
                    saveService.save(boardForm);
                })

        );
    }

    @Test
    @DisplayName("동일 게시글 확인 - 게시글 존재 하지 않을시 fail()")
    void existBoardTest(){
        BoardForm boardForm = getBoardForm();
        assertDoesNotThrow(() -> {
            boardDao.insert(boardForm);
        });
        Long id = boardForm.getId();
        if(id == null ){
            fail();
        }

        Board board = boardDao.get(id);
        if(board == null){
            fail();
        }

        assertEquals(board.getSubject(), boardForm.getSubject());
        assertEquals(board.getContent(), boardForm.getContent());


    }
}
