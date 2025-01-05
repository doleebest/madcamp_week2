package madcamp_week2.repet.DTO;

import lombok.Builder;
import lombok.Getter;
import madcamp_week2.repet.Domain.ChatMessage;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatResponseDTO {
    private Long id;
    private PetInfo pet;
    private String userMessage;
    private String petResponse;
    private LocalDateTime createdAt;

    @Getter
    @Builder
    public static class PetInfo {
        private Long id;
        private String name;
        private String species;
    }

    public static ChatResponseDTO from(ChatMessage chatMessage) {
        return ChatResponseDTO.builder()
                .id(chatMessage.getId())
                .pet(PetInfo.builder()
                        .id(chatMessage.getPet().getId())
                        .name(chatMessage.getPet().getName())
                        .species(chatMessage.getPet().getSpecies())
                        .build())
                .userMessage(chatMessage.getUserMessage())
                .petResponse(chatMessage.getPetResponse())
                .createdAt(chatMessage.getCreatedAt())
                .build();
    }
}