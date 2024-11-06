package fourman.project1.mapper.board;

import fourman.project1.domain.board.Board;
import fourman.project1.domain.board.BoardRequestDto;
import fourman.project1.domain.board.BoardResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board boardRequestDtoToBoard(BoardRequestDto boardRequestDto);
    BoardResponseDto boardToBoardResponseDto(Board board);
}
