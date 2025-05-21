package com.attijariwafa.chatbotCms.dto;

public class GitSyncRequest {
    private String remoteUrl;
    private String localPath;
    private String username;
    private String password;
    private String repoName;
    private String remoteUsername;
    private String localProjectPath;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRemoteUsername() {
        return remoteUsername;
    }

    public void setRemoteUsername(String remoteUsername) {
        this.remoteUsername = remoteUsername;
    }

    public String getLocalProjectPath() {
        return localProjectPath;
    }

    public void setLocalProjectPath(String localProjectPath) {
        this.localProjectPath = localProjectPath;
    }

    // Getters & Setters
    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}