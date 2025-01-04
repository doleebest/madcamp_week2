package madcamp_week2.repet.Controller;

import madcamp_week2.repet.Domain.Pet;
import madcamp_week2.repet.Service.ChatGPTService;
import madcamp_week2.repet.Service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatGPTService chatGPTService;
    private final PetService petService;

    public ChatController(ChatGPTService chatGPTService, PetService petService) {
        this.chatGPTService = chatGPTService;
        this.petService = petService;
    }

    @GetMapping("/{id}")
    public String chatPage(@PathVariable Long id, Model model) {
        // Pet ID를 받아서 Pet 정보 가져오기
        Optional<Pet> petOptional = petService.getPetById(id);
        if (petOptional.isEmpty()) {
            model.addAttribute("error", "Pet not found!");
            return "chat";
        }

        Pet pet = petOptional.get();
        String petInfo = String.format(
                "Name: %s\nBirthDate: %s\nGender: %s\nBreed: %s\nPersonality: %s\nTraits: %s\nHappy Memory: %s\nMishap: %s\nStrengths: %s\nWeaknesses: %s",
                pet.getName(), pet.getBirthDate(), pet.getGender(), pet.getBreed(), pet.getPersonality(),
                pet.getTraits(), pet.getHappy_memory(), pet.getMishap(), pet.getStrengths(), pet.getWeaknesses()
        );

        model.addAttribute("petInfo", petInfo);
        return "chat"; // chat.html 파일을 반환
    }

    @PostMapping("/send/{id}")
    public String sendMessage(@PathVariable Long id, @RequestParam String message, Model model) {
        // Pet 정보 가져오기
        Optional<Pet> petOptional = petService.getPetById(id);
        if (petOptional.isEmpty()) {
            model.addAttribute("error", "Pet not found!");
            return "chat";
        }

        Pet pet = petOptional.get();
        String petInfo = String.format(
                "Name: %s\nBirthDate: %s\nGender: %s\nBreed: %s\nPersonality: %s\nTraits: %s\nHappy Memory: %s\nMishap: %s\nStrengths: %s\nWeaknesses: %s",
                pet.getName(), pet.getBirthDate(), pet.getGender(), pet.getBreed(), pet.getPersonality(),
                pet.getTraits(), pet.getHappy_memory(), pet.getMishap(), pet.getStrengths(), pet.getWeaknesses()
        );

        // ChatGPTService 호출
        String response = chatGPTService.chatWithPet(petInfo, message);
        model.addAttribute("response", response);

        return "chat"; // chat 페이지에서 응답을 표시
    }
}
