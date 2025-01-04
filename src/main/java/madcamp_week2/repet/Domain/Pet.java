package madcamp_week2.repet.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String birthDate;  // "YYYY-MM-DD" 형식으로 저장
    private String gender;     // "Male" or "Female"
    private String breed;      // 품종
    private String personality; // 성격
    private String traits;     // 특징
    private String happy_memory; // 행복한 추억
    private String mishap;     // 사고친 경험
    private String strengths;  // 장점
    private String weaknesses; // 단점
    }

