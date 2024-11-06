package fourman.project1.service.board;

import fourman.project1.domain.board.Board;
import fourman.project1.repository.board.BoardMyBatisMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    @Autowired private final BoardMyBatisMapper boardMyBatisMapper;

    public List<Board> findBoards() {
        return boardMyBatisMapper.findBoards();
    }

    public Board findBoardById(Long boardId) {
        return boardMyBatisMapper.findBoardById(boardId).orElse(null);
    }

    public Board createBoard(Board board) {
        return boardMyBatisMapper.createBoard(board);
    }

    public Board updateBoard(Board board) {
        Board findBoard = boardMyBatisMapper.findBoardById(board.getBoardId())
                .orElse(null);

        Optional.ofNullable(board.getBoardname())
                .ifPresent(boardname -> findBoard.setBoardname(boardname));

        return boardMyBatisMapper.updateBoard(findBoard);
    }

    public void deleteBoard(Long boardId) {
        Board board = boardMyBatisMapper.findBoardById(boardId)
                .orElse(null);

        boardMyBatisMapper.deleteBoard(board);
    }
}
