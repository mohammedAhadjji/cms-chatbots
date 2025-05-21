package com.attijariwafa.chatbotCms.repositories;

import com.attijariwafa.chatbotCms.entities.IntentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntentRepository extends JpaRepository<IntentEntity,Long> {
}
