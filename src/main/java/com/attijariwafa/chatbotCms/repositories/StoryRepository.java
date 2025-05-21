package com.attijariwafa.chatbotCms.repositories;

import com.attijariwafa.chatbotCms.entities.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<StoryEntity,Long> {
}
