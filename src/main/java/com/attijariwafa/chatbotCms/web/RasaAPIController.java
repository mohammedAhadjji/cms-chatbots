package com.attijariwafa.chatbotCms.web;

import com.attijariwafa.chatbotCms.services.RasaAPiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rasa")
public class RasaAPIController {

 @Autowired
 private RasaAPiService rasaService;

 @PostMapping("/load-domain")
 public ResponseEntity<String> loadDomain(@RequestParam String rasaHost) {
 return rasaService.loadDomain(rasaHost);
}
 @PostMapping("/train-model")
 public ResponseEntity<String> trainModel(@RequestParam String rasaHost) {
return rasaService.trainModel(rasaHost);
}

 @GetMapping("/status")
 public ResponseEntity<String> getStatus(@RequestParam String rasaHost) {
 return rasaService.getStatus(rasaHost);
 }

}
