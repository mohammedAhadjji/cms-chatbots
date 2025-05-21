package com.attijariwafa.chatbotCms.web;

import com.attijariwafa.chatbotCms.services.DomainImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DomainImportController {
    @Autowired
    private DomainImportService domainImportService;
    @GetMapping("/load-domain")
    public String loadDomain() {
        domainImportService.importFromDomainYaml();
        return "Domain loaded!";
    }
}
