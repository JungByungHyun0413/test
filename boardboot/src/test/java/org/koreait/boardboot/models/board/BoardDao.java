package org.koreait.boardboot.models.board;

import org.koreait.boardboot.controller.board.BoardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(BoardForm boardForm){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO BOARDDATA (ID, SUBJECT, CONTENT) VALUES (BOARDDATA_SEQ.nextval, ?, ?)";
		jdbcTemplate.update(con -> {
			PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"Id"});
			pstmt.setString(1, boardForm.getSubject());
			pstmt.setString(2, boardForm.getContent());

			return pstmt;
		}, keyHolder);
				Number key = keyHolder.getKey();
				boardForm.setId(key.longValue());
	}
	public Integer delete(Long id){
		String sql = "DELETE FROM BOARDDATA WHERE ID = ?";
		Integer success = jdbcTemplate.update(sql, id);

		return success;

	}
	public Board get(Long id){
		try {
			String sql = "SELECT * FROM BOARDDATA WHERE ID = ?";
			Board board = jdbcTemplate.queryForObject(sql, this::mapper);
			return board;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private Board mapper(ResultSet resultSet, int i)throws SQLException {
		Board board = new Board();
		board.setId(resultSet.getLong("ID"));
		board.setSubject(resultSet.getString("SUBJECT"));
		board.setContent(resultSet.getString("CONTENT"));
		board.setRegdt(resultSet.getTimestamp("REGDT").toLocalDateTime());

		return board;
	}

	public List<Board> gets(){
		try {
			String sql = "SELECT * FROM BOARDDATA";
			List<Board> boards = jdbcTemplate.query(sql, this::mapper);

			return boards;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
