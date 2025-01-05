package madcamp_week2.repet.DTO;

import lombok.*;
import madcamp_week2.repet.Domain.Board;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String userEmail;

    // Entity로 변환
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public BoardDto(Long id, String title, String content,
                    LocalDateTime createdDate, LocalDateTime modifiedDate,
                    String userEmail) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.userEmail = userEmail;
    }

    // Entity를 DTO로 변환
    public static BoardDto from(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .userEmail(board.getUser().getEmail())
                .build();
    }
}