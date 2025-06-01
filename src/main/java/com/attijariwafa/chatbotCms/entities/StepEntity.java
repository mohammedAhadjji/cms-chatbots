package com.attijariwafa.chatbotCms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;  // "intent" ou "action"
    private String name;  // ex: "greet" ou "utter_greet"

    @ManyToOne
    private RuleEntity rule;

    @ManyToOne
    private StoryEntity story;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RuleEntity getRule() {
        return rule;
    }

    public void setRule(RuleEntity rule) {
        this.rule = rule;
    }

    public StoryEntity getStory() {
        return story;
    }

    public void setStory(StoryEntity story) {
        this.story = story;
    }
}

