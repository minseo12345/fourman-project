package fourman.project1.repository;

import fourman.project1.domain.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardJdbcTemplateRepository implements BoardRepository {
    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public List<Board> findBoardAll() {
        return List.of();
    }

    @Override
    public Optional<Board> findBoardById(Long boardId) {
        return Optional.empty();
    }

    @Override
    public Board saveBoard(Board board) {
        return null;
    }

    @Override
    public void deleteBoard(Board board) {

    }
}
