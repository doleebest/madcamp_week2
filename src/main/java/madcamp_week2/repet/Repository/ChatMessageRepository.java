package madcamp_week2.repet.Repository;

import madcamp_week2.repet.Domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByPetIdOrderByCreatedAtDesc(Long petId);
}