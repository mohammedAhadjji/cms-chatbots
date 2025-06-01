package com.attijariwafa.chatbotCms.web;

import com.attijariwafa.chatbotCms.entities.ExampleEntity;
import com.attijariwafa.chatbotCms.entities.Intent;
import com.attijariwafa.chatbotCms.entities.IntentEntity;
import com.attijariwafa.chatbotCms.entities.NluDataDTO;
import com.attijariwafa.chatbotCms.repositories.IntentRepository;
import com.attijariwafa.chatbotCms.services.NluImportService;
import com.attijariwafa.chatbotCms.services.RasaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class NluImportController {
    @Autowired
    private final NluImportService importService;
    @Autowired
    private IntentRepository intentRepository ;

    @Autowired
    private RasaContentService rasaContentService;
    public NluImportController(NluImportService importService) {
        this.importService = importService;
    }

    @GetMapping("/load")
    public String loadNlu(@RequestParam String pathNLu) {
        importService.importFromYaml(pathNLu);
        return "Import terminé !";
    }

    @GetMapping("/load-nlu-jackson")
    public Object loadNluJackson(@RequestParam String pathNLu) throws IOException {
        NluDataDTO nluData = rasaContentService.loadNlu(pathNLu);
        return nluData;
    }

    @GetMapping("/load-nlu-db")
    public ResponseEntity<NluDataDTO> load(@RequestParam String pathNLu) throws IOException {
        // Charger les données NLU depuis le fichier YAML
        NluDataDTO nluData = rasaContentService.loadNlu(pathNLu);
        List<Intent> data = nluData.getNlu();

        data.forEach(intent -> {
            // Créer l'entité Intent
            IntentEntity intentEntity = new IntentEntity();
            intentEntity.setIntent(intent.getIntent());

            // Créer les entités Example et les lier à l'intent
            List<ExampleEntity> exampleEntities = intent.getExamples().stream()
                    .map(exampleText -> {
                        ExampleEntity exampleEntity = new ExampleEntity();
                        exampleEntity.setText(exampleText);
                        exampleEntity.setIntent(intentEntity); // Lien bidirectionnel
                        return exampleEntity;
                    })
                    .collect(Collectors.toList());

            // Lier les exemples à l'intent
            intentEntity.setExamples(exampleEntities);

            // Sauvegarder l'intent (et ses exemples si cascade persist est activé)
            intentRepository.save(intentEntity);
        });

        return ResponseEntity.ok(nluData);
    }

}