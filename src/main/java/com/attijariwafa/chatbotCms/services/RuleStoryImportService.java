package com.attijariwafa.chatbotCms.services;

import com.attijariwafa.chatbotCms.entities.RuleEntity;
import com.attijariwafa.chatbotCms.entities.RuleYamlData;
import com.attijariwafa.chatbotCms.entities.StepEntity;
import com.attijariwafa.chatbotCms.entities.StoryEntity;
import com.attijariwafa.chatbotCms.repositories.RuleRepository;
import com.attijariwafa.chatbotCms.repositories.StepRepository;
import com.attijariwafa.chatbotCms.repositories.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class RuleStoryImportService {

    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private StoryRepository storyRepository;
    @Autowired private StepRepository stepRepository;

    public void importRules() throws Exception {
        InputStream input = new ClassPathResource("rules.yml").getInputStream();
        Yaml yaml = new Yaml();
        RuleYamlData data = yaml.loadAs(input, RuleYamlData.class);

        for (RuleYamlData.Rule rule : data.getRules()) {
            RuleEntity ruleEntity = new RuleEntity();
            ruleEntity.setRuleName(rule.getRule());
            List<StepEntity> stepEntities = new ArrayList<>();

            for (RuleYamlData.Step step : rule.getSteps()) {
                StepEntity stepEntity = new StepEntity();

                if (step.getIntent() != null) {
                    stepEntity.setType("intent");
                    stepEntity.setName(step.getIntent());
                } else if (step.getAction() != null) {
                    stepEntity.setType("action");
                    stepEntity.setName(step.getAction());
                }

                stepEntity.setRule(ruleEntity);
                stepEntities.add(stepEntity);
            }

            ruleEntity.setSteps(stepEntities);
            ruleRepository.save(ruleEntity);
        }
    }

    public void importStories() throws Exception {
        InputStream input = new ClassPathResource("stories.yml").getInputStream();
        Yaml yaml = new Yaml();
        RuleYamlData data = yaml.loadAs(input, RuleYamlData.class); // même format que rule

        for (RuleYamlData.Rule story : data.getRules()) {
            StoryEntity storyEntity = new StoryEntity();
            storyEntity.setStoryName(story.getRule());
            List<StepEntity> stepEntities = new ArrayList<>();

            for (RuleYamlData.Step step : story.getSteps()) {
                StepEntity stepEntity = new StepEntity();

                if (step.getIntent() != null) {
                    stepEntity.setType("intent");
                    stepEntity.setName(step.getIntent());
                } else if (step.getAction() != null) {
                    stepEntity.setType("action");
                    stepEntity.setName(step.getAction());
                }

                stepEntity.setStory(storyEntity);
                stepEntities.add(stepEntity);
            }

            storyEntity.setSteps(stepEntities);
            storyRepository.save(storyEntity);
        }
    }
}

