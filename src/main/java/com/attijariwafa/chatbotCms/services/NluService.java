package com.attijariwafa.chatbotCms.services;

import com.attijariwafa.chatbotCms.entities.NluData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

@Service
public class NluService {

    public NluData loadNluData() {
        try {
            InputStream input = new ClassPathResource("nlu_data.yml").getInputStream();
            Yaml yaml = new Yaml();
            return yaml.loadAs(input, NluData.class);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement du fichier YAML", e);
        }
    }
}
