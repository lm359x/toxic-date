package lmx.toxicdating.repository;

import lmx.toxicdating.domain.Chat;
import lmx.toxicdating.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {
    List<Chat> findAllByUsersContaining(User user);
}
