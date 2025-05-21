package com.attijariwafa.chatbotCms.entities;
import java.util.List;

public class Intent {
    private String intent;
    private List<String> examples;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }
}