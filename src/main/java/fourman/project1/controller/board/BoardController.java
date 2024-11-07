package fourman.project1.controller.board;

import fourman.project1.domain.board.Board;
import fourman.project1.domain.board.BoardRequestDto;
import fourman.project1.domain.board.BoardResponseDto;
import fourman.project1.mapper.board.BoardMapper;
import fourman.project1.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/boards")
@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

   private final BoardService boardService;
   private final BoardMapper boardMapper;

   @GetMapping
    public String findBoards(Model model) {
       List<Board> boards = boardService.findBoards();
       model.addAttribute("boards", boards);
       log.info("findBoards {}", boards.size());
       return "main-test";
   }

   @GetMapping("/{boardId}")
    public String findBoardById(@PathVariable Long boardId, Model model) {
       Board findBoard = boardService.findBoardById(boardId);
       model.addAttribute("board", findBoard);
       log.info("findBoardById {}", findBoard.getBoardId());
       return "";
   }

   @GetMapping("/create")
    public String createBoard(Model model) {
       model.addAttribute("boardRequestDto", new BoardRequestDto());
       return "";
   }

   @PostMapping("/create")
    public String createBoard(@ModelAttribute BoardRequestDto boardRequestDto, Model model) {
      log.info("Create board: {} ### {}", boardRequestDto.getBoardname(), boardRequestDto.getPosts());
       // Request Dto를 Board 엔티티로 변환
       Board board = boardMapper.boardRequestDtoToBoard(boardRequestDto);
       // Board 객체 생성 (ID 자동 생성)
       boardService.createBoard(board);
       log.info("1boardId : {}", board.getBoardId());
       log.info("1boardname : {}", board.getBoardname());
       // 생성된 ID로 Board 다시 조회
       Board newBoard = boardService.findBoardById(board.getBoardId());
       log.info("#######################++++++++++++++++++++ ");
       // 조회된 Board를 Response Dto로 변환
       BoardResponseDto boardResponseDto = boardMapper.boardToBoardResponseDto(newBoard);
       model.addAttribute("board", boardResponseDto);
       return "redirect:/boards";
   }


   @GetMapping("/{boardId}/edit")
    public String updateBoard(@PathVariable Long boardId, Model model) {
       Board board = boardService.findBoardById(boardId);
       model.addAttribute("board", board);
       return "";
   }

   @PatchMapping("/{boardId}/edit")
    public String updateBoard(@PathVariable Long boardId, @ModelAttribute BoardRequestDto boardRequestDto, Model model) {
       // Request Dto를 Board 엔티티로 변환
       Board board = boardMapper.boardRequestDtoToBoard(boardRequestDto);
       board.setBoardId(boardId);
       // Board 객체 업데이트
       boardService.updateBoard(board);
       // 업데이트 된 ID로 Board 다시 조회
       Board updatedBoard = boardService.findBoardById(boardId);
       // 조회된 Board를 Response Dto로 변환
       BoardResponseDto boardResponseDto = boardMapper.boardToBoardResponseDto(updatedBoard);
       model.addAttribute("board", boardResponseDto);

       return "redirect:/boards/" + boardId;
   }

   @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable Long boardId) {
       boardService.deleteBoard(boardId);
       return "redirect:/boards";
   }

}
