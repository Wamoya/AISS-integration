package aiss.dailymotionminer.repository;

import aiss.dailymotionminer.model.dailymotion.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
