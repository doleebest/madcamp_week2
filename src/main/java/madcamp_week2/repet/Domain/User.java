package madcamp_week2.repet.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String picture;

    @Builder
    public User(String name, String email, Role role, String picture) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.picture = picture;
    }

    public User update(String name, String picture) {
        System.out.println(name + picture);
        this.name = name;
        this.picture = picture;
        return this;
    }
}