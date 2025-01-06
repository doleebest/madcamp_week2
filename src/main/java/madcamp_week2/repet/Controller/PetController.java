package madcamp_week2.repet.Controller;

import com.nimbusds.jose.util.Resource;
import lombok.RequiredArgsConstructor;
import madcamp_week2.repet.Domain.Pet;
import madcamp_week2.repet.Domain.User;
import madcamp_week2.repet.Repository.PetRepository;
import madcamp_week2.repet.config.auth.SecurityUtil;
import madcamp_week2.repet.DTO.PetRequest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PetController {
    private final PetRepository petRepository;
    private final SecurityUtil securityUtil;

    @PostMapping("/pets")
    public ResponseEntity<Pet> createPet(
            @RequestPart("image") MultipartFile image,
            @RequestPart("name") String name,
            @RequestPart("birthDate") String birthDate,
            @RequestPart("gender") String gender,
            @RequestPart("species") String species,
            @RequestPart("personality") String personality,
            @RequestPart("traits") String traits,
            @RequestPart("happyMemory") String happyMemory,
            @RequestPart("mishap") String mishap,
            @RequestPart("strengths") String strengths,
            @RequestPart("weaknesses") String weaknesses
    ) {
        User currentUser = securityUtil.getCurrentUser();

        try {
            // 이미지 저장
            String imageUrl = saveImage(image);

            Pet pet = Pet.builder()
                    .name(name)
                    .birthDate(birthDate)
                    .gender(gender)
                    .species(species)
                    .personality(personality)
                    .traits(traits)
                    .happyMemory(happyMemory)
                    .mishap(mishap)
                    .strengths(strengths)
                    .weaknesses(weaknesses)
                    .imageUrl(imageUrl)
                    .user(currentUser)
                    .build();
            Pet savedPet = petRepository.save(pet);
            return ResponseEntity.ok(savedPet);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 이미지 저장 메서드
    private String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Empty file");
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path directory = Paths.get("uploads/pets");
        Files.createDirectories(directory);

        Path targetPath = directory.resolve(fileName);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/pets/" + fileName;
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getMyPets() {
        User currentUser = securityUtil.getCurrentUser();
        List<Pet> pets = petRepository.findByUserId(currentUser.getId());
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        User currentUser = securityUtil.getCurrentUser();
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        if (!pet.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(pet);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(
            @PathVariable Long id,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart("name") String name,
            @RequestPart("birthDate") String birthDate,
            @RequestPart("gender") String gender,
            @RequestPart("species") String species,
            @RequestPart("personality") String personality,
            @RequestPart("traits") String traits,
            @RequestPart("happyMemory") String happyMemory,
            @RequestPart("mishap") String mishap,
            @RequestPart("strengths") String strengths,
            @RequestPart("weaknesses") String weaknesses
    ) {
        try {
            User currentUser = securityUtil.getCurrentUser();
            Pet pet = petRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pet not found"));

            if (!pet.getUser().getId().equals(currentUser.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            // 새 이미지가 제공된 경우에만 이미지 업데이트
            String imageUrl = pet.getImageUrl();
            if (image != null && !image.isEmpty()) {
                imageUrl = saveImage(image);
            }

            pet.update(
                    name,
                    birthDate,
                    gender,
                    species,
                    personality,
                    traits,
                    happyMemory,
                    mishap,
                    strengths,
                    weaknesses,
                    imageUrl
            );

            Pet updatedPet = petRepository.save(pet);
            return ResponseEntity.ok(updatedPet);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/pets/{id}")
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