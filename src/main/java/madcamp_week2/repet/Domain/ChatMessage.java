package madcamp_week2.repet.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String userMessage;

    @Column(columnDefinition = "TEXT")  // VARCHAR 대신 TEXT 타입 사용
    private String petResponse;
    private LocalDateTime createdAt;

    @Builder
    public ChatMessage(Long id, Pet pet, User user, String userMessage, String petResponse, LocalDateTime createdAt){
        this.id = id;
        this.pet = pet;
        this.user = user;
        this.userMessage = userMessage;
        this.petResponse = petResponse;
        this.createdAt = createdAt;

    }



}
