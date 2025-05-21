package com.attijariwafa.chatbotCms.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GitAuthServices {

    @Value("${git.username}")
    private String username;

    @Value("${git.token}")
    private String token;

    private CredentialsProvider getAuthIfPrivateRepo() {
        return new UsernamePasswordCredentialsProvider(username, token);
    }

    public String cloneRepository(String remoteUrl, String localPath) {
        try {
            Git.cloneRepository()
                    .setURI(remoteUrl)
                    .setDirectory(new File(localPath))
                    .setCredentialsProvider(getAuthIfPrivateRepo())
                    .call();
            return "Clonage (privé) réussi.";
        } catch (Exception e1) {
            try {
                Git.cloneRepository()
                        .setURI(remoteUrl)
                        .setDirectory(new File(localPath))
                        .call();
                return "Clonage (public) réussi.";
            } catch (Exception e2) {
                return "Erreur lors du clonage : " + e2.getMessage();
            }
        }
    }

    public String pullChanges(String localPath) {
        try {
            Git git = Git.open(new File(localPath));
            git.pull()
                    .setCredentialsProvider(getAuthIfPrivateRepo())
                    .call();
            return "Pull (privé) effectué avec succès.";
        } catch (Exception e1) {
            try {
                Git git = Git.open(new File(localPath));
                git.pull().call();
                return "Pull (public) effectué avec succès.";
            } catch (Exception e2) {
                return "Erreur lors du pull : " + e2.getMessage();
            }
        }
    }

//    public String pushChanges(String localPath) {
//        try {
//            Git git = Git.open(new File(localPath));
//            git.push()
//                    .setCredentialsProvider(getAuthIfPrivateRepo())
//                    .call();
//            return "Push effectué avec succès.";
//        } catch (Exception e) {
//            return "Erreur lors du push (auth requise ?) : " + e.getMessage();
//        }
//    }
public String pushChanges(String localPath) {
    try {
        Git git = Git.open(new File(localPath));

        // Étape 1 : add .
        git.add()
                .addFilepattern(".")
                .call();

        // Étape 2 : commit
        git.commit()
                .setMessage("Auto-commit via GitSyncService")
                .call();

        // Étape 3 : push
        git.push()
                .setCredentialsProvider(getAuthIfPrivateRepo())
                .call();

        return "Modifications ajoutées, commitées et poussées avec succès.";
    } catch (Exception e) {
        return "Erreur lors du push : " + e.getMessage();
    }
}

    public String syncRepository(String remoteUrl, String localPath) {
        File localDir = new File(localPath);
        if (!localDir.exists() || localDir.list().length == 0) {
            return cloneRepository(remoteUrl, localPath);
        } else {
            return pullChanges(localPath);
        }
    }

    public String syncRepository(String remoteUrl, String localPath, String username, String password) {
        try {
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(username, password);
            File localDir = new File(localPath);

            if (!localDir.exists() || localDir.list().length == 0) {
                Git.cloneRepository()
                        .setURI(remoteUrl)
                        .setDirectory(localDir)
                        .setCredentialsProvider(cp)
                        .call();
                return "Clonage (auth) terminé avec succès.";
            } else {
                Git git = Git.open(localDir);
                git.pull()
                        .setCredentialsProvider(cp)
                        .call();
                return "Pull (auth) effectué avec succès.";
            }
        } catch (Exception e) {
            return "Erreur (auth) : " + e.getMessage();
        }
    }
    public void createAndPushNewRepository(String name, String repoName, String localProjectPath)
            throws IOException, GitAPIException, URISyntaxException {

         String remoteUsername = this.username;
        // 1. Créer le dépôt sur GitHub
       // Git.init()
        createRepositoryOnGithub(repoName);

        // 2. Initialiser le dépôt local
        Git git = Git.init().setDirectory(new File(localProjectPath)).call();

        // 3. Ajouter un remote origin
        String remoteUrl = "https://github.com/" + remoteUsername + "/" + repoName + ".git";
        git.remoteAdd()
                .setName("origin")
                .setUri(new URIish(remoteUrl))
                .call();

        // 4. Ajouter tous les fichiers
        git.add().addFilepattern(".").call();

        // 5. Commit
        git.commit().setMessage("Initial commit").call();

        // 6. Pousser vers GitHub
        CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username, token);
        git.push()
                .setRemote("origin")
                .setCredentialsProvider(credentialsProvider)
                .call();
    }
    private void createRepositoryOnGithub(String repoName) {
        String apiUrl = "https://api.github.com/user/repos";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token); // GitHub Token

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", repoName);
        requestBody.put("private", false); // ou true si tu veux un dépôt privé

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
            response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.err.println("Erreur création dépôt GitHub : " + e.getMessage());
        }
    }
}
