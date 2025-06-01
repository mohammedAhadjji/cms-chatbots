package com.attijariwafa.chatbotCms.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Intent {
    private String intent;
    private List<String> examples;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public List<String> getExamples() {
        return examples;
    }

    // Setter personnalisé pour transformer un bloc de texte en liste
    @JsonProperty("examples")
    public void setExamples(String examplesBlock) {
        this.examples = Arrays.stream(examplesBlock.split("\n"))
                .map(String::trim)
                .filter(line -> line.startsWith("- "))
                .map(line -> line.substring(2)) // remove "- "
                .toList();
    }

    @Override
    public String toString() {
        return "Intent{" +
                "intent='" + intent + '\'' +
                ", examples=" + examples +
                '}';
    }

}