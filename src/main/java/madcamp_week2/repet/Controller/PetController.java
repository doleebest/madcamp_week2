package madcamp_week2.repet.Controller;

import org.springframework.ui.Model;
import madcamp_week2.repet.Domain.Pet;
import madcamp_week2.repet.Service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService){
        this.petService = petService;
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
}
