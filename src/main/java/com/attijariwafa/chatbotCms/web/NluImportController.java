package com.attijariwafa.chatbotCms.web;

import com.attijariwafa.chatbotCms.services.NluImportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NluImportController {

    private final NluImportService importService;

    public NluImportController(NluImportService importService) {
        this.importService = importService;
    }

    @GetMapping("/load-nlu")
    public String loadNlu(@RequestParam String pathNLu) {
        importService.importFromYaml(pathNLu);
        return "Import terminé !";
    }
}