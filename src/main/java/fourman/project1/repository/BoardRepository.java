package fourman.project1.repository;

import fourman.project1.domain.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    List<Board> findBoardAll(); // 전체 목록을 조회

    Optional<Board> findBoardById(Long boardId); // 1건 조회

    Board saveBoard(Board board); // 저장 or 수정 (나눠서 메서드를 )

    void deleteBoard(Board board); // 1건 삭제
}
