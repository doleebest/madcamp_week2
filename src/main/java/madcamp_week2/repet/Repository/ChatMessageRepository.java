package madcamp_week2.repet.Repository;

import madcamp_week2.repet.Domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByPetIdOrderByCreatedAtDesc(Long petId);

    @Modifying
    @Query("DELETE FROM ChatMessage c WHERE c.pet.id = :petId AND c.user.id = :userid")
    void deleteAllByPetIdUserId(@Param("petId") Long petId, @Param("userid") String userid);
}