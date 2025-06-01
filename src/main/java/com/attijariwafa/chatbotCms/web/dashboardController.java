package com.attijariwafa.chatbotCms.web;


import com.attijariwafa.chatbotCms.entities.ExampleEntity;
import com.attijariwafa.chatbotCms.entities.Intent;
import com.attijariwafa.chatbotCms.entities.IntentEntity;
import com.attijariwafa.chatbotCms.entities.NluData;
import com.attijariwafa.chatbotCms.repositories.IntentRepository;
import com.attijariwafa.chatbotCms.services.IntentMapperService;
import com.attijariwafa.chatbotCms.services.RasaContentService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.webresources.JarResourceRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class dashboardController {
    @Autowired
    private RasaContentService rasaContentService;
    @Autowired
    private IntentRepository intentRepository ;
    @Autowired
    private IntentMapperService intentMapperService;

    public dashboardController(RasaContentService rasaContentService, IntentRepository intentRepository) {
        this.rasaContentService = rasaContentService;
        this.intentRepository = intentRepository;
    }

    @GetMapping("/")
    public String nlu(Model model, HttpServletRequest request) throws IOException {
      // yamlNluImporter("src/main/resources/project/nlu.yml");
       // NluData nluData = this.rasaContentService.loadNlu("src/main/resources/rasa-content/data/nlu.yml");
      //  List<Intent> nlu = nluData.getNlu() ;
       // System.out.println(nluData);
//        for (Intent nluItem : nlu) {
////            intents.add(nluItem.getIntent().toString());
////            this.nluPersistenceService.saveNlu(nluItem);
//            intentRepository.save(nluItem) ;
//        }
       // this.nluPersistenceService.saveNluData(nluData);
        return "dashboard";
    }

    private void yamlNluImporter(String s) {

    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) throws IOException {
    //   rasaNluService.loadNluFromFile("project/nlu.yml");
        return "dashboard";
    }
    @PostMapping("/dashboard")
    public String handleIntentSelection(@RequestParam String intent, Model model) throws IOException {

        return "dashboard";
    }
//    @GetMapping("/test")
//    public String ajouterNLuExmples(Model model{
//        NluData nluData = new NluData();
//        List<IntentEntity> nlu =intentRepository.findAll();
//        nluData.setNlu(nlu);
//    model.addAttribute("data",nluData)
//        return "test";
//    }
        @GetMapping("/test1")
        public String ajouterNLuExemples(Model model) {
            List<Intent> intents = intentMapperService.mapToIntentList(intentRepository.findAll());
            model.addAttribute("data", intents);
            return "test";
        }

    @GetMapping("/test")
    public String afficherListeIntents(Model model) {
        List<IntentEntity> intents = intentRepository.findAll();
        model.addAttribute("data", intents);
        return "list-intents";
    }

    @GetMapping("/intent/edit/{id}")
    public String editIntentForm(@PathVariable Long id, Model model) {
        IntentEntity intentEntity = intentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid intent ID"));
        if (intentEntity.getExamples() == null){
            intentEntity.setExamples(new ArrayList<>());
        }
        model.addAttribute("intentEntity", intentEntity);
        return "edit-intent";
    }

    @PostMapping("/intent/update")
    public String updateIntent(@ModelAttribute IntentEntity intentEntity) {
        // Pour que les exemples soient bien liés à l'intent
        if (intentEntity.getExamples() != null) {
            intentEntity.getExamples().forEach(example -> example.setIntent(intentEntity));
        }
        intentRepository.save(intentEntity);
        return "redirect:/test";
    }

//
//    @GetMapping("/admin/newProduct")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String newProduct(Model model) {
//        model.addAttribute("product", new Product());
//        return "new-product";
//    }
}
