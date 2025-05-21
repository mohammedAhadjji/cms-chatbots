package com.attijariwafa.chatbotCms.services;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class RasaNluService {/*
    private final IntentRepository intentRepository;

    public RasaNluService(IntentRepository intentRepository) {
        this.intentRepository = intentRepository;
    }

    public void loadNluFromFile(String filePath) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Fichier introuvable : " + filePath);
            }

            Map<String, Object> obj = yaml.load(inputStream);
            List<Map<String, Object>> nluData = (List<Map<String, Object>>) obj.get("nlu");

            for (Map<String, Object> intentMap : nluData) {
                System.out.println("intentMap : "+intentMap.size());
                String intentName = (String) intentMap.get("intent");
                String examplesBlock = (String) intentMap.get("examples");

                List<String> examples = Arrays.stream(examplesBlock.split("\n"))
                        .map(String::trim)
                        .filter(e -> e.startsWith("-"))
                        .map(e -> e.substring(1).trim())
                        .toList();

                Intent intent = new Intent();
                intent.setName(intentName);
                intent.setExamples(examples);
                intentRepository.save(intent);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement du fichier NLU : " + e.getMessage(), e);
        }
    }*/
}
