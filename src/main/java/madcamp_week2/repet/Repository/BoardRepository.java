package madcamp_week2.repet.Repository;

import madcamp_week2.repet.Domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUserId(String userId);
}