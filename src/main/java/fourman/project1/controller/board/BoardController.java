package fourman.project1.controller.board;

import fourman.project1.domain.board.Board;
import fourman.project1.domain.board.BoardRequestDto;
import fourman.project1.domain.board.BoardResponseDto;
import fourman.project1.mapper.board.BoardMapper;
import fourman.project1.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/boards")
@Controller
@RequiredArgsConstructor
public class BoardController {

   private final BoardService boardService;
   private final BoardMapper boardMapper;

   @GetMapping
    public String findBoards(Model model) {
       List<Board> boards = boardService.findBoards();
       model.addAttribute("boards", boards);
       return "";
   }

   @GetMapping("/{boardId}")
    public String findBoardById(@PathVariable Long boardId, Model model) {
       Board findBoard = boardService.findBoardById(boardId);
       model.addAttribute("board", findBoard);
       return "";
   }

   @GetMapping("/create")
    public String createBoard(Model model) {
       model.addAttribute("boardRequestDto", new BoardRequestDto());
       return "";
   }

   @PostMapping("/create")
    public String createBoard(@ModelAttribute BoardRequestDto boardRequestDto, Model model) {
       Board board = boardMapper.boardRequestDtoToBoard(boardRequestDto);
       Board newBoard = boardService.createBoard(board);
       BoardResponseDto boardResponseDto = boardMapper.boardToBoardResponseDto(newBoard);
       model.addAttribute("board", boardResponseDto);

       return "redirect:/boards";
   }

   @GetMapping("/{boardId}")
    public String updateBoard(@PathVariable Long boardId, Model model) {
       Board board = boardService.findBoardById(boardId);
       model.addAttribute("board", board);
       return "";
   }

   @PatchMapping("/{boardId}")
    public String updateBoard(@PathVariable Long boardId, Board board, Model model) {
       board.setBoardId(boardId);
       Board updatedBoard = boardService.updateBoard(board);
       model.addAttribute("board", updatedBoard);

       return "redirect:/boards/" + boardId;
   }

   @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable Long boardId) {
       boardService.deleteBoard(boardId);
       return "redirect:/boards";
   }

}
