package madcamp_week2.repet.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime modifiedDate;

    private String imageFileName;    // 저장된 파일명
    private String originalFileName; // 원본 파일명

    @Builder
    public Board(String title, String content, User user, String imageFileName, String originalFileName) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
        this.imageFileName = imageFileName;
        this.originalFileName = originalFileName;
    }

    // 이미지 없이 제목과 내용만 업데이트
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifiedDate = LocalDateTime.now();
    }

    // 이미지 정보만 업데이트
    public void updateImage(String imageFileName, String originalFileName) {
        this.imageFileName = imageFileName;
        this.originalFileName = originalFileName;
        this.modifiedDate = LocalDateTime.now();
    }

    // 모든 정보 업데이트
    public void updateAll(String title, String content, String imageFileName, String originalFileName) {
        this.title = title;
        this.content = content;
        this.imageFileName = imageFileName;
        this.originalFileName = originalFileName;
        this.modifiedDate = LocalDateTime.now();
    }
}