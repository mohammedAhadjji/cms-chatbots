package com.attijariwafa.chatbotCms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RasaAPiService {

 private final RestTemplate restTemplate = new RestTemplate();
 private String rasaHost = "http://rasa_host";

 public ResponseEntity<String> loadDomain(String Host) {
  this.rasaHost = Host;
 String url = rasaHost + "/domain";
 return restTemplate.postForEntity(url, null, String.class);
 }

public ResponseEntity<String> trainModel(String Host) {
 this.rasaHost = Host;
 String url = rasaHost + "/model/train";
 return restTemplate.postForEntity(url, null, String.class);
}

public ResponseEntity<String> getStatus(String Host) {
 this.rasaHost = Host;
String url = rasaHost + "/status";
 return restTemplate.getForEntity(url, String.class);
 }
}
