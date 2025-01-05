package madcamp_week2.repet.Service;

import lombok.RequiredArgsConstructor;
import madcamp_week2.repet.Domain.ChatMessage;
import madcamp_week2.repet.Domain.Pet;
import madcamp_week2.repet.Domain.User;
import madcamp_week2.repet.Repository.ChatMessageRepository;
import madcamp_week2.repet.Repository.PetRepository;
import madcamp_week2.repet.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final ChatGPTService chatGPTService;
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage startChat(Long petId, String userId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        User user = userRepository.findById(userId)  // 이제 String 그대로 사용
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!pet.getUser().getId().equals(userId)) {  // String 비교
            throw new IllegalArgumentException("Not authorized");
        }

        String initialResponse = chatGPTService.generatePetResponse(pet, "안녕, 보고 싶었어.");

        return chatMessageRepository.save(ChatMessage.builder()
                .pet(pet)
                .user(user)
                .userMessage("안녕, 보고 싶었어.")
                .petResponse(initialResponse)
                .createdAt(LocalDateTime.now())
                .build());
    }

    public ChatMessage sendMessage(Long petId, String userId, String message) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        User user = userRepository.findById(userId)  // 이제 String 그대로 사용
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!pet.getUser().getId().equals(userId)) {  // String 비교
            throw new IllegalArgumentException("Not authorized");
        }

        String response = chatGPTService.generatePetResponse(pet, message);

        return chatMessageRepository.save(ChatMessage.builder()
                .pet(pet)
                .user(user)
                .userMessage(message)
                .petResponse(response)
                .createdAt(LocalDateTime.now())
                .build());
    }
}