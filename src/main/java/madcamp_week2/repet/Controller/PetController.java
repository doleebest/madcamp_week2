package madcamp_week2.repet.Controller;

import lombok.RequiredArgsConstructor;
import madcamp_week2.repet.Domain.Pet;
import madcamp_week2.repet.Domain.User;
import madcamp_week2.repet.Repository.PetRepository;
import madcamp_week2.repet.config.auth.SecurityUtil;
import madcamp_week2.repet.DTO.PetRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {
    private final PetRepository petRepository;
    private final SecurityUtil securityUtil;

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody PetRequest request) {
        User currentUser = securityUtil.getCurrentUser();

        Pet pet = Pet.builder()
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .species(request.getSpecies())
                .personality(request.getPersonality())
                .traits(request.getTraits())
                .happyMemory(request.getHappyMemory())
                .mishap(request.getMishap())
                .strengths(request.getStrengths())
                .weaknesses(request.getWeaknesses())
                .user(currentUser)
                .build();

        Pet savedPet = petRepository.save(pet);
        return ResponseEntity.ok(savedPet);
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getMyPets() {
        User currentUser = securityUtil.getCurrentUser();
        List<Pet> pets = petRepository.findByUserId(currentUser.getId());
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        User currentUser = securityUtil.getCurrentUser();
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        if (!pet.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody PetRequest request) {
        User currentUser = securityUtil.getCurrentUser();
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        if (!pet.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        pet.update(
                request.getName(),
                request.getBirthDate(),
                request.getGender(),
                request.getSpecies(),
                request.getPersonality(),
                request.getTraits(),
                request.getHappyMemory(),
                request.getMishap(),
                request.getStrengths(),
                request.getWeaknesses()
        );

        Pet updatedPet = petRepository.save(pet);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        User currentUser = securityUtil.getCurrentUser();
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        if (!pet.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        petRepository.delete(pet);
        return ResponseEntity.ok().build();
    }
}