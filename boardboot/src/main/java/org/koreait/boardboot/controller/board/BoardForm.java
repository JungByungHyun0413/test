package org.koreait.boardboot.controller.board;

import lombok.Data;

@Data
public class BoardForm {

    private Long id;
    private String subject;
    private String content;
}
