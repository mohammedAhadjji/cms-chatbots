package com.attijariwafa.chatbotCms.services;



import com.attijariwafa.chatbotCms.entities.Intent;
import com.attijariwafa.chatbotCms.entities.NluData;
import com.attijariwafa.chatbotCms.entities.NluDataDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
@Data
public class RasaContentService {
    private final String DOMAIN_FILE_PATH = "rasa/domain.yml";
    private final String NLU_FILE_PATH = "rasa-content/data/nlu.yml";
    private final String STORIES_FILE_PATH = "rasa/stories.yml";

    private final ObjectMapper objectMapper;

    public RasaContentService() {
        // Initialize ObjectMapper with YAMLFactory
        this.objectMapper = new ObjectMapper(new YAMLFactory());
    }

    // Load Domain
   /* public Domain loadDomain() throws IOException {
        File file = new File(DOMAIN_FILE_PATH);
        return objectMapper.readValue(file, Domain.class);
    }

    // Save Domain
   /* public void saveDomain(Domain domain) throws IOException {
        Files.write(Paths.get(DOMAIN_FILE_PATH), objectMapper.writeValueAsBytes(domain));
    }*/

    // Load NLU
    /*public NluData loadNlu() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(NLU_FILE_PATH);
        if (inputStream == null) {
            throw new IOException("Fichier non trouvé dans les ressources : " + NLU_FILE_PATH);
        }
        return objectMapper.readValue(inputStream, NluData.class);
    }*/
    //load valide
    public NluDataDTO loadNlu(String path) throws IOException {

        Path filePath = Paths.get(path);//"src/main/resources/rasa/nlu.yml"
        if (!Files.exists(filePath)) {
            throw new IOException("Le fichier n'existe pas à l'emplacement spécifié.");
        }
        byte[] bytes = Files.readAllBytes(filePath);
        return objectMapper.readValue(bytes, NluDataDTO.class);
    }

    public void updateNlu(NluDataDTO updatedData,String path) throws IOException {
        Path filePath = Paths.get(path);

        if (!Files.exists(filePath)) {
            throw new IOException("Le fichier nlu.yml n'existe pas.");
        }

        // Convertir l'objet en YAML (suppose que tu as bien configuré ObjectMapper pour YAML)
        String yamlContent = objectMapper.writeValueAsString(updatedData);

        // Écrire dans le fichier (remplace complètement le contenu)
        Files.write(filePath, yamlContent.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
    }


    // ✅ Save NLU dans un fichier local (ex. en mode test/développement uniquement)
    public void saveNlu(Intent nlu) throws IOException {
        // chemin vers un dossier temporaire ou un emplacement spécifique sur disque
        Path outputPath = Path.of("src/main/resources/rasa/nlu.yml");
        Files.write(outputPath, objectMapper.writeValueAsBytes(nlu));
    }

    // Load Stories
  /*  public Stories loadStories() throws IOException {
        File file = new File(STORIES_FILE_PATH);
        return objectMapper.readValue(file, Stories.class);
    }

    // Save Stories
    public void saveStories(Stories stories) throws IOException {
        Files.write(Paths.get(STORIES_FILE_PATH), objectMapper.writeValueAsBytes(stories));
    }*/
}
