package madcamp_week2.repet;

import madcamp_week2.repet.Domain.Role;
import madcamp_week2.repet.Domain.User;
import madcamp_week2.repet.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindByEmail() {
        // Given
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        user.setName("Test User");
        user.setPicture("http://example.com/picture.jpg");
        user.setRole(Role.USER);

        // When
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findByEmail(email);

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals(email, foundUser.get().getEmail());
    }
}
