package madcamp_week2.repet.Controller;

import lombok.RequiredArgsConstructor;
import madcamp_week2.repet.DTO.BoardRequest;
import madcamp_week2.repet.DTO.BoardDto;
import madcamp_week2.repet.Domain.Board;
import madcamp_week2.repet.Domain.User;
import madcamp_week2.repet.Repository.BoardRepository;
import madcamp_week2.repet.Service.BoardService;
import madcamp_week2.repet.config.auth.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final SecurityUtil securityUtil;

    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardRequest request) {
        User currentUser = securityUtil.getCurrentUser();
        BoardDto boardDto = boardService.createBoard(request, currentUser);
        return ResponseEntity.ok(boardDto);
    }

    @GetMapping
    public ResponseEntity<List<BoardDto>> getMyBoards() {
        User currentUser = securityUtil.getCurrentUser();
        List<BoardDto> boards = boardService.getMyBoards(currentUser);
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long id) {
        User currentUser = securityUtil.getCurrentUser();
        try {
            BoardDto boardDto = boardService.getBoard(id, currentUser);
            return ResponseEntity.ok(boardDto);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Access denied")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequest request) {
        User currentUser = securityUtil.getCurrentUser();
        try {
            BoardDto boardDto = boardService.updateBoard(id, request, currentUser);
            return ResponseEntity.ok(boardDto);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Access denied")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        User currentUser = securityUtil.getCurrentUser();
        try {
            boardService.deleteBoard(id, currentUser);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Access denied")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            throw e;
        }
    }
}