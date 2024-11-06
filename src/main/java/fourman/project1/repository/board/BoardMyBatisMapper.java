package fourman.project1.repository.board;

import fourman.project1.domain.board.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMyBatisMapper {
    List<Board> findBoards(); // 전체 목록을 조회

    Optional<Board> findBoardById(Long boardId); // 1건 조회

    Board createBoard(Board board); // 저장

    Board updateBoard(Board board); // 수정

    void deleteBoard(Board board); // 1건 삭제
}
