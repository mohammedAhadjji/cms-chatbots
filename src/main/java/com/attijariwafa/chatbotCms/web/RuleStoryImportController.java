package com.attijariwafa.chatbotCms.web;

import com.attijariwafa.chatbotCms.services.RuleStoryImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RuleStoryImportController {

    @Autowired
    private RuleStoryImportService service;

    @GetMapping("/load-rules")
    public String loadRules() throws Exception {
        service.importRules();
        return "Rules imported!";
    }

    @GetMapping("/load-stories")
    public String loadStories() throws Exception {
        service.importStories();
        return "Stories imported!";
    }
}

