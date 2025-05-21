package com.attijariwafa.chatbotCms.web;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class dashboardController {



    @GetMapping("/")
    public String nlu(Model model, HttpServletRequest request) throws IOException {
      // yamlNluImporter("src/main/resources/project/nlu.yml");
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

}
