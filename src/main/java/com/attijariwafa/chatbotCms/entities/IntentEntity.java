package com.attijariwafa.chatbotCms.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String intent;

    @OneToMany(mappedBy = "intent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExampleEntity> examples;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ExampleEntity> getExamples() {
        return examples;
    }

    public void setExamples(List<ExampleEntity> examples) {
        this.examples = examples;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

}