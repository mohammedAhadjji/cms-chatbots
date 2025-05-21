package com.attijariwafa.chatbotCms.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class GitSyncService {


//    public String syncRepository(String remoteUrl, String localPath) {
//        try {
//            File gitDir = new File(localPath, ".git");
//
//            if (gitDir.exists()) {
//                return pullChanges(localPath);
//            } else {
//                return cloneRepository(remoteUrl, localPath);
//            }
//        } catch (Exception e) {
//            return "Erreur lors de la synchronisation: " + e.getMessage();
//        }
//    }

    private final ResourceLoader resourceLoader;

    public GitSyncService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    public String pushChanges(String repoPath) throws Exception {
        try (Git git = Git.open(new File(repoPath))) {
            git.add().addFilepattern(".").call(); // ajouter tous les fichiers modifiés
            git.commit().setMessage("Auto commit").call(); // créer un commit
            git.push().call(); // pousser vers le remote
            return "Push effectué avec succès depuis : " + repoPath;
        } catch (Exception e) {
            throw new Exception("Erreur lors du push : " + e.getMessage(), e);
        }
    }
//    public String syncRepository(String remoteUrl, String relativePath) throws Exception {
//        Resource resource = resourceLoader.getResource("classpath:");
//        File resourcesDir = resource.getFile();
//        File targetDir = new File(resourcesDir, relativePath);
//
//        if (!targetDir.exists()) {
//            if (!targetDir.mkdirs()) {
//                throw new IOException("Failed to create directory: " + targetDir.getAbsolutePath());
//            }
//        }
//
//        Git.cloneRepository()
//                .setURI(remoteUrl)
//                .setDirectory(targetDir)
//                .call();
//
//        return "Repository cloned successfully to: " + targetDir.getAbsolutePath();
//    }
public String syncRepository(String remoteUrl, String localPath) throws Exception {
    File targetDir = new File(localPath);

    // Création du répertoire si inexistant
    if (!targetDir.exists()) {
        if (!targetDir.mkdirs()) {
            throw new IOException("Impossible de créer le dossier: " + targetDir.getAbsolutePath());
        }
    }

    // Clone du dépôt
    Git.cloneRepository()
            .setURI(remoteUrl)
            .setDirectory(targetDir)
            .call();

    return "Dépôt cloné avec succès dans: " + targetDir.getAbsolutePath();
}
    private String cloneRepository(String remoteUrl, String localPath) throws GitAPIException {
        Git.cloneRepository()
                .setURI(remoteUrl)
                .setDirectory(new File(localPath))
                .call();
        return "Dépôt cloné avec succès dans: " + localPath;
    }

    public String pullChanges(String localPath) throws IOException, GitAPIException {
        File gitDir = new File(localPath, ".git");
        try (Repository repository = new FileRepositoryBuilder()
                .setGitDir(gitDir)
                .build()) {

            try (Git git = new Git(repository)) {
                PullCommand pull = git.pull();
                PullResult result = pull.call();

                if (result.isSuccessful()) {
                    return "Mises à jour récupérées avec succès. " +
                            (result.getFetchResult().getMessages() != null ?
                                    result.getFetchResult().getMessages() : "");
                } else {
                    return "Échec lors de la récupération des mises à jour";
                }
            }
        }
    }

    /**
     * Version avec authentification
     */
    public String syncRepository(String remoteUrl, String localPath,
                                 String username, String password) {
        try {
            File gitDir = new File(localPath, ".git");

            if (gitDir.exists()) {
                return pullChanges(localPath, username, password);
            } else {
                return cloneRepository(remoteUrl, localPath, username, password);
            }
        } catch (Exception e) {
            return "Erreur lors de la synchronisation: " + e.getMessage();
        }
    }

    public String cloneRepository(String remoteUrl, String localPath,
                                  String username, String password) throws GitAPIException {
        Git.cloneRepository()
                .setURI(remoteUrl)
                .setDirectory(new File(localPath))
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(
                        username, password))
                .call();
        return "Dépôt cloné avec succès dans: " + localPath;
    }

    private String pullChanges(String localPath, String username, String password)
            throws IOException, GitAPIException {
        File gitDir = new File(localPath, ".git");
        try (Repository repository = new FileRepositoryBuilder()
                .setGitDir(gitDir)
                .build()) {

            try (Git git = new Git(repository)) {
                PullCommand pull = git.pull()
                        .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));

                PullResult result = pull.call();

                if (result.isSuccessful()) {
                    return "Mises à jour récupérées avec succès";
                } else {
                    return "Échec lors de la récupération des mises à jour";
                }
            }
        }
    }
}
