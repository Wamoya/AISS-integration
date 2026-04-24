package aiss.dailymotionminer.repository;

import aiss.dailymotionminer.model.dailymotion.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
