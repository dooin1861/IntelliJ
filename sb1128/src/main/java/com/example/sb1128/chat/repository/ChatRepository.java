package com.example.sb1128.chat.repository;

import com.example.sb1128.chat.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    List<ChatEntity> findTop100ByOrderBySendTimeDesc();

    @Modifying
    @Query("DELETE FROM ChatEntity c WHERE c.sendTime < :oneWeekAgo")
    void deleteOldMessages(@Param("oneWeekAgo") LocalDateTime oneWeekAgo);
}