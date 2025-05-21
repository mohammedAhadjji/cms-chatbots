package com.attijariwafa.chatbotCms.services;

import com.attijariwafa.chatbotCms.entities.DomainData;
import com.attijariwafa.chatbotCms.entities.IntentEntity;
import com.attijariwafa.chatbotCms.entities.ResponseEntity;
import com.attijariwafa.chatbotCms.repositories.IntentRepository;
import com.attijariwafa.chatbotCms.repositories.ResponseRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DomainImportService {

    private final IntentRepository intentRepository;
    private final ResponseRepository responseRepository;

    public DomainImportService(IntentRepository intentRepository, ResponseRepository responseRepository) {
        this.intentRepository = intentRepository;
        this.responseRepository = responseRepository;
    }

    public void importFromDomainYaml() {
        try {
            InputStream input = new ClassPathResource("domain.yml").getInputStream();
            Yaml yaml = new Yaml();
            DomainData domainData = yaml.loadAs(input, DomainData.class);


            List<IntentEntity> intents = new ArrayList<>();
            for (String intentName : domainData.getIntents()) {
                IntentEntity entity = new IntentEntity();
                entity.setIntent(intentName);
                entity.setExamples(new ArrayList<>());
                intents.add(entity);
            }
            intentRepository.saveAll(intents);


            List<ResponseEntity> responses = new ArrayList<>();
            domainData.getResponses().forEach((name, responseList) -> {
                for (DomainData.ResponseText resp : responseList) {
                    ResponseEntity entity = new ResponseEntity();
                    entity.setName(name);
                    entity.setText(resp.getText());
                    responses.add(entity);
                }
            });
            responseRepository.saveAll(responses);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement de domain.yml", e);
        }
    }
}
