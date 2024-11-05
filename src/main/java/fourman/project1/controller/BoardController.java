package fourman.project1.controller;

import fourman.project1.domain.board.Board;
import fourman.project1.domain.board.BoardRequestDto;
import fourman.project1.domain.board.BoardResponseDto;
import fourman.project1.mapper.BoardMapper;
import fourman.project1.service.BoardService;
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

   @PostMapping
    public String createBoard(@ModelAttribute BoardRequestDto boardRequestDto) {
       Board board = boardMapper.boardRequestDtoToBoard(boardRequestDto);
       Board newBoard = boardService.createBoard(board);
       BoardResponseDto boardResponseDto = boardMapper.boardToBoardResponseDto(newBoard);
       
       return "redirect:/boards";
   }

   @GetMapping("/{boardId}/update")
    public String updateBoard(@PathVariable Long boardId, Model model) {
       Board board = boardService.findBoardById(boardId);
       model.addAttribute("board", board);
       return "";
   }

   @PatchMapping("/{boardId}")
    public String updateBoard(@PathVariable Long boardId, Board board, Model model) {
       board.setBoardId(boardId);
       Board updatedBoard = boardService.updateBoard(board);

       return "redirect:/boards/" + boardId;
   }

   @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable Long boardId) {
       boardService.deleteBoard(boardId);
       return "redirect:/boards";
   }

}
