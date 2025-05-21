package com.attijariwafa.chatbotCms.web;

import com.attijariwafa.chatbotCms.entities.NluData;
import com.attijariwafa.chatbotCms.services.NluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NluController {
    @Autowired
    private final NluService nluService;

    public NluController(NluService nluService) {
        this.nluService = nluService;
    }

    @GetMapping("/nlu")
    public NluData getNluData() {
        return nluService.loadNluData();
    }
}
