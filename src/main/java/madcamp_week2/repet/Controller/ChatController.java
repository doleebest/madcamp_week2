package madcamp_week2.repet.Controller;

import madcamp_week2.repet.DTO.ChatRequest;
import lombok.RequiredArgsConstructor;
import madcamp_week2.repet.DTO.ChatResponseDTO;
import madcamp_week2.repet.Domain.ChatMessage;
import madcamp_week2.repet.Service.ChatService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/start/{petId}")
    public ResponseEntity<ChatResponseDTO> startChat(
            @PathVariable Long petId,
            @AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(
                ChatResponseDTO.from(
                        chatService.startChat(petId, principal.getName())
                )
        );
    }

    @PostMapping("/{petId}/message")
    public ResponseEntity<ChatResponseDTO> sendMessage(
            @PathVariable Long petId,
            @RequestBody ChatRequest request,
            @AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(
                ChatResponseDTO.from(
                        chatService.sendMessage(
                                petId,
                                principal.getName(),
                                request.getMessage()
                        )
                )
        );
    }

    // 채팅 히스토리 조회 API 추가
    @GetMapping("/{petId}")
    public ResponseEntity<List<ChatResponseDTO>> getChatHistory(
            @PathVariable Long petId,
            @AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(
                chatService.getChatHistory(petId, principal.getName())
        );
    }
}
