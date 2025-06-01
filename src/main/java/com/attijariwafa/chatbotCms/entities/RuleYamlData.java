package com.attijariwafa.chatbotCms.entities;

import java.util.List;

public class RuleYamlData {

    private List<Rule> rules;

    public List<Rule> getRules() { return rules; }

    public RuleYamlData() {
    }

    public RuleYamlData(List<Rule> rules) {
        this.rules = rules;
    }

    public void setRules(List<Rule> rules) { this.rules = rules; }

    public static class Rule {
        private String rule;
        private List<Step> steps;

        public String getRule() { return rule; }
        public void setRule(String rule) { this.rule = rule; }

        public List<Step> getSteps() { return steps; }
        public void setSteps(List<Step> steps) { this.steps = steps; }
    }

    public static class Step {
        private String intent;
        private String action;

        public String getIntent() { return intent; }
        public void setIntent(String intent) { this.intent = intent; }

        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }
    }
}

