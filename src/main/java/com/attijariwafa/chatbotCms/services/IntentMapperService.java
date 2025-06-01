package com.attijariwafa.chatbotCms.services;

import com.attijariwafa.chatbotCms.entities.IntentEntity;
import com.attijariwafa.chatbotCms.entities.Intent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntentMapperService {

    public Intent mapToIntent(IntentEntity entity) {
        Intent intent = new Intent();
        intent.setIntent(entity.getIntent());
        intent.setExamples(
                (List<String>) entity.getExamples().stream()
                        .map(example -> example.getText()) // Assure-toi que getExample() existe
                        .collect(Collectors.toList())
        );
        return intent;
    }

    public List<Intent> mapToIntentList(List<IntentEntity> entities) {
        return entities.stream()
                .map(this::mapToIntent)
                .collect(Collectors.toList());
    }
}
