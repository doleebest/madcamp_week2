package madcamp_week2.repet.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PetRequest {
    private String name;
    private String birthDate;
    private String gender;
    private String species;
    private String personality;
    private String traits;
    private String happyMemory;
    private String mishap;
    private String strengths;
    private String weaknesses;
}