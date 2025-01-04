package madcamp_week2.repet.Controller;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.ui.Model;
import madcamp_week2.repet.Domain.Pet;
import madcamp_week2.repet.Service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import madcamp_week2.repet.Service.ChatGPTService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;
    private final ChatGPTService chatGPTService;

    public PetController(PetService petService, ChatGPTService chatGPTService)
    {
        this.petService = petService;
        this.chatGPTService = chatGPTService;
    }

    @GetMapping
    public String listPets(Model model){
        List<Pet> pets = petService.getAllPets();
        model.addAttribute("pets",pets); // "pets"라는 이름으로 pets 데이터를 뷰에 전달
        return "pets/list";
    }

    @GetMapping("/new") // 새로운 폼
    public String newPetForm(Model model){
        model.addAttribute("pet", new Pet()); // pet 이라는 이름으로 새로운 빈 pet 객체를 model에 추가. 펫 정보를 담기 위한 공간 마련
        return "pets/new";
    }

    @PostMapping // 폼으로 입력한 펫 데이터를 저장
    public String savePet(@ModelAttribute Pet pet){
        petService.savePet(pet);
        return "redirect:/pets";
    }

    @GetMapping("/{id}/chat")
    public String chatWithPet(@PathVariable Long id, @RequestParam String message) {
        Optional<Pet> petOptional = petService.getPetById(id);
        if (petOptional.isEmpty()) {
            return "Pet not found!";
        }

        Pet pet = petOptional.get();
        String petInfo = String.format(
                "Name: %s\nBirthDate: %s\nGender: %s\nBreed: %s\nPersonality: %s\nTraits: %s\nHappy Memory: %s\nMishap: %s\nStrengths: %s\nWeaknesses: %s",
                pet.getName(), pet.getBirthDate(), pet.getGender(), pet.getBreed(), pet.getPersonality(),
                pet.getTraits(), pet.getHappy_memory(), pet.getMishap(), pet.getStrengths(), pet.getWeaknesses()
        );

        return chatGPTService.chatWithPet(petInfo, message);
    }
}
