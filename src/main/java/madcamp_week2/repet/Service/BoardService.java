package madcamp_week2.repet.Service;

import lombok.RequiredArgsConstructor;
import madcamp_week2.repet.DTO.BoardDto;
import madcamp_week2.repet.DTO.BoardRequest;
import madcamp_week2.repet.Domain.Board;
import madcamp_week2.repet.Domain.User;
import madcamp_week2.repet.Repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 생성
    @Transactional
    public BoardDto createBoard(BoardRequest request, User user) {
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();

        Board savedBoard = boardRepository.save(board);
        return BoardDto.from(savedBoard);
    }

    // 사용자의 모든 게시글 조회
    public List<BoardDto> getMyBoards(User user) {
        List<Board> boards = boardRepository.findByUserId(user.getId());
        return boards.stream()
                .map(BoardDto::from)
                .collect(Collectors.toList());
    }

    // 특정 게시글 조회
    public BoardDto getBoard(Long id, User user) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        if (!board.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        return BoardDto.from(board);
    }

    // 게시글 수정
    @Transactional
    public BoardDto updateBoard(Long id, BoardRequest request, User user) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        if (!board.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        board.update(request.getTitle(), request.getContent());
        return BoardDto.from(board);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long id, User user) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        if (!board.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        boardRepository.delete(board);
    }

    // 게시글의 소유자 확인
    public boolean isOwner(Long boardId, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        return board.getUser().getId().equals(user.getId());
    }
}