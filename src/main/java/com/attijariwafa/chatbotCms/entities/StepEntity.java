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
}

