package com.attijariwafa.chatbotCms.entities;
import java.util.List;

public class NluData {
    private List<Intent> nlu;

    public List<Intent> getNlu() {
        return nlu;
    }

    public void setNlu(List<Intent> nlu) {
        this.nlu = nlu;
    }
}
