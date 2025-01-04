package madcamp_week2.repet.Service;

import madcamp_week2.repet.Domain.Pet;
import madcamp_week2.repet.Repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository){
        this.petRepository = petRepository;
    }

    public Pet savePet(Pet pet){
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetByName(String name) {
        return petRepository.findByName(name);
    }

}
