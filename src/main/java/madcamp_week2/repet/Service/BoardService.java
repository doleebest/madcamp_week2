package madcamp_week2.repet.Service;

import jakarta.transaction.Transactional;
import madcamp_week2.repet.DTO.BoardDto;
import madcamp_week2.repet.Domain.Board;
import madcamp_week2.repet.Repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    // 게시글 저장 로직
    @Transactional
    public Long savePost(BoardDto boardDto){ // 게시글 데이터를 담고 있는 dto 객체를 입력으로 받는다. 반환값은 저장된 게시글의 고유 Id
        return boardRepository.save(boardDto.toEntity()).getId();
        // boardDto.toEntity() 는 dto 객체를 엔티티 객체로 변환해 데베에 저장할 수 있도록 변환.
    }

    // 데베에서 게시글을 조회하여 dto로 변환 후 반환
        @Transactional
    public List<BoardDto> getBoardList(){
        List<Board> boardList = boardRepository.findAll(); // board 엔티티를 모두 조회해서 List<Board> 형태로 반환
        List<BoardDto> boardDtoList = new ArrayList<>(); // 엔티티 데이터를 dto로 변환한 결과를 저장할 리스트

        for(Board board : boardList) { // 반복문 돌면서 Dto로 변환
            BoardDto boardDto = BoardDto.builder() // 엔티티의 각 필드를 DTO의 필드로 매핑
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreated_date())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
}
