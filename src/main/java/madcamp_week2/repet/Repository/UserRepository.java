package madcamp_week2.repet.Repository;

import madcamp_week2.repet.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email); // 중복 가입 확인
}