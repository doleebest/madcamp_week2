package madcamp_week2.repet.Repository;

import madcamp_week2.repet.Domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // 이름으로 애완견을 찾는 쿼리 메소드 정의
    Pet findByName(String name);
}
