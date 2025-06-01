package com.attijariwafa.chatbotCms.web;

import com.attijariwafa.chatbotCms.entities.Intent;
import com.attijariwafa.chatbotCms.entities.NluData;
import com.attijariwafa.chatbotCms.entities.NluDataDTO;
import com.attijariwafa.chatbotCms.services.NluService;
import com.attijariwafa.chatbotCms.services.RasaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NluController {
    @Autowired
    private final NluService nluService;
    @Autowired
    private RasaContentService rasaContentService;
    

    public NluController(NluService nluService) {
        this.nluService = nluService;
    }

    @GetMapping("/nlu")
    public NluData getNluData() {
        return nluService.loadNluData();
    }
    @GetMapping("/edit-file-nlu")
    public String ecrireNluData(@RequestParam String path) throws IOException {
        NluDataDTO data = rasaContentService.loadNlu("src/main/resources/rasa-content/data/nlu.yml");
        rasaContentService.updateNlu(data,path);
        return "succes !!";
    }
    @GetMapping("/testm")
    public String ecrireNluDatam(@RequestParam String path) throws IOException {
        NluDataDTO data = new NluDataDTO();
        List<Intent> intents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Intent intent = new Intent();
            intent.setIntent("applictaion"+ i);
            intent.setExamples("- comment effectuer un retrait en caisse \n" +
                    "      - y a-t-il une limite de retrait en caisse \n" +
                    "      - cette banque autorise-t-il le retrait");
            intents.add(intent);
        }

        data.setNlu((List<Intent>) intents);
        rasaContentService.updateNlu(data,path);
        return "succes !!";
    }


    //

}
