package madcamp_week2.repet.DTO;

import lombok.*;
import madcamp_week2.repet.Domain.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    // dto에서 엔티티로 변환하는 역할
    // 데이터를 저장/업데이트하는 작업에 사용
    // 엔티티를 생성, dto에서 받아온 필드 값을 엔티티에 설정하여 반환
    public Board toEntity(){
        Board build = Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();
        return build;
    }


    // 데이터 전달용 객체 생성
    // 클라이언트/뷰에서 데이터 전달 시
    @Builder
    public BoardDto(Long id, String author, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate){
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
