package fourman.project1.service;

import fourman.project1.domain.board.Board;
import fourman.project1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    @Autowired private final BoardRepository boardRepository;

    public List<Board> findBoards() {
        return boardRepository.findBoardAll();
    }

    public Board findBoardById(Long boardId) {
        return boardRepository.findBoardById(boardId).orElse(null);
    }

    public Board createBoard(Board board) {
        return boardRepository.saveBoard(board);
    }

    public Board updateBoard(Board board) {
        Board findBoard = boardRepository.findBoardById(board.getBoardId())
                .orElse(null);

        Optional.ofNullable(board.getBoardname())
                .ifPresent(boardname -> findBoard.setBoardname(boardname));

        return boardRepository.updateBoard(findBoard);
    }

    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findBoardById(boardId)
                .orElse(null);

        boardRepository.deleteBoard(board);
    }
}
