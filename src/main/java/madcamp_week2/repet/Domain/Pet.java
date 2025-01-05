package madcamp_week2.repet.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthDate;  // "YYYY-MM-DD" 형식

    @Column(nullable = false)
    private String gender;     // "Male" or "Female"

    @Column(nullable = false)
    private String species;    // 종류 : 강아지, 고양이

    @Column
    private String personality;

    @Column
    private String traits;

    @Column(name = "happy_memory")
    private String happyMemory;

    @Column
    private String mishap;

    @Column
    private String strengths;

    @Column
    private String weaknesses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Pet(String name, String birthDate, String gender, String species,
               String personality, String traits, String happyMemory, String mishap,
               String strengths, String weaknesses, User user) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.species = species;
        this.personality = personality;
        this.traits = traits;
        this.happyMemory = happyMemory;
        this.mishap = mishap;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.user = user;
    }

    public void update(String name, String birthDate, String gender, String species,
                       String personality, String traits, String happyMemory, String mishap,
                       String strengths, String weaknesses) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.species = species;
        this.personality = personality;
        this.traits = traits;
        this.happyMemory = happyMemory;
        this.mishap = mishap;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
    }
}