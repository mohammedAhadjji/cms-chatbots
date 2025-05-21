package com.attijariwafa.chatbotCms.services;

import com.attijariwafa.chatbotCms.entities.ExampleEntity;
import com.attijariwafa.chatbotCms.entities.Intent;
import com.attijariwafa.chatbotCms.entities.IntentEntity;
import com.attijariwafa.chatbotCms.entities.NluData;
import com.attijariwafa.chatbotCms.repositories.IntentRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class NluImportService {

    private final IntentRepository intentRepository;

    public NluImportService(IntentRepository intentRepository) {
        this.intentRepository = intentRepository;
    }

    public void importFromYaml(String PathNLu) {
        try {
            InputStream input = new ClassPathResource(PathNLu).getInputStream();
            Yaml yaml = new Yaml();
            NluData data = yaml.loadAs(input, NluData.class);

            List<IntentEntity> entities = new ArrayList<>();

            for (Intent intent : data.getNlu()) {
                IntentEntity intentEntity = new IntentEntity();
                intentEntity.setIntent(intent.getIntent());

                List<ExampleEntity> examples = new ArrayList<>();
                for (String exampleText : intent.getExamples()) {
                    ExampleEntity ex = new ExampleEntity();
                    ex.setText(exampleText);
                    ex.setIntent(intentEntity); // lien bidirectionnel
                    examples.add(ex);
                }

                intentEntity.setExamples(examples);
                entities.add(intentEntity);
            }

            intentRepository.saveAll(entities);

        } catch (Exception e) {
            throw new RuntimeException("Erreur de chargement du YAML", e);
        }
    }
}