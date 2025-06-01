package com.attijariwafa.chatbotCms.entities;


import java.util.List;
import java.util.Map;

public class DomainData {
    private List<String> intents;
    private Map<String, List<ResponseText>> responses;

    public List<String> getIntents() {
        return intents;
    }

    public DomainData() {
    }

    public DomainData(List<String> intents, Map<String, List<ResponseText>> responses) {
        this.intents = intents;
        this.responses = responses;
    }

    public void setIntents(List<String> intents) {
        this.intents = intents;
    }

    public Map<String, List<ResponseText>> getResponses() {
        return responses;
    }

    public void setResponses(Map<String, List<ResponseText>> responses) {
        this.responses = responses;
    }

    public static class ResponseText {
        private String text;
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }
}