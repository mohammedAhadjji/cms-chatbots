package com.attijariwafa.chatbotCms.repositories;

import com.attijariwafa.chatbotCms.entities.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<ResponseEntity,Long> {
}
