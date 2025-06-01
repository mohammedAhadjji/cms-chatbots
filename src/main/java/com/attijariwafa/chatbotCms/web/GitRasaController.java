package com.attijariwafa.chatbotCms.web;


import com.attijariwafa.chatbotCms.dto.GitSyncRequest;
import com.attijariwafa.chatbotCms.services.GitAuthServices;
import com.attijariwafa.chatbotCms.services.GitSyncService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/git")
public class GitRasaController {


    @Autowired
    private GitSyncService gitSyncService;

    @Autowired
    private GitAuthServices gitAuthServices;

    @PostMapping(value = "/sync", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> syncRepository(@RequestBody GitSyncRequest request) {
        try {
            String result = gitSyncService.syncRepository(
                    request.getRemoteUrl(),
                    "./src/main/resources/demo"
            );
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur: " + e.getMessage());
        }
    }

   @PostMapping(value = "/pull", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pullRepository(@RequestBody GitSyncRequest request) {
        try {
            String localPath = "./src/main/resources/" + request.getLocalPath();
            String result = gitSyncService.pullChanges(localPath);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur : " + e.getMessage());
        }
    }

    @PostMapping(value = "/push", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pushRepository(@RequestBody GitSyncRequest request,@RequestParam String CommitMessage) {
        try {
            String localPath = "./src/main/resources/" + request.getLocalPath();
            String result = gitAuthServices.pushChanges(localPath,CommitMessage);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur : " + e.getMessage());
        }
    }

    @PostMapping(value = "/clone", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cloneRepository(@RequestBody GitSyncRequest request) {
        try {
            String result = gitAuthServices.cloneRepository( request.getRemoteUrl(),"./src/main/resources/" + request.getLocalPath());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur: " + e.getMessage());
        }
    }

    @PostMapping("/create-and-push")
    public ResponseEntity<String> createAndPushRepository(
            @RequestParam String repoName,
            @RequestParam String remoteUsername,
            @RequestParam String localProjectPath
    ) {
        try {
            gitAuthServices.createAndPushNewRepository(repoName, remoteUsername, localProjectPath);
            return ResponseEntity.ok("Dépôt GitHub créé et projet local poussé avec succès.");
        } catch (IOException | URISyntaxException e) {
            return ResponseEntity.internalServerError().body("Erreur : " + e.getMessage());
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/sync-auth")
    public ResponseEntity<String> syncRepositoryWithAuth(
            @RequestParam String remoteUrl,
            @RequestParam String localPath,
            @RequestParam String username,
            @RequestParam String password
    ) {
        try {
            String result = gitSyncService.syncRepository(remoteUrl, localPath, username, password);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur : " + e.getMessage());
        }
    }
}