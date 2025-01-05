package madcamp_week2.repet.Controller;

import madcamp_week2.repet.DTO.ChatRequest;
import lombok.RequiredArgsConstructor;
import madcamp_week2.repet.Domain.ChatMessage;
import madcamp_week2.repet.Service.ChatService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/start/{petId}")
    public ResponseEntity<ChatMessage> startChat(
            @PathVariable Long petId,
            @AuthenticationPrincipal OAuth2User principal) {
        ChatMessage response = chatService.startChat(petId, principal.getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{petId}/message")
    public ResponseEntity<ChatMessage> sendMessage(
            @PathVariable Long petId,
            @RequestBody ChatRequest request,
            @AuthenticationPrincipal OAuth2User principal) {
        ChatMessage response = chatService.sendMessage(
                petId,
                principal.getName(),
                request.getMessage()
        );
        return ResponseEntity.ok(response);
    }

}
