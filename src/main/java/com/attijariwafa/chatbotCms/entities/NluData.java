package com.attijariwafa.chatbotCms.entities;
import jakarta.persistence.*;

import java.util.List;

public class NluData {

    private String version;
      private List<Intent> nlu;

    public NluData() {
    }

    public NluData(String version, List<Intent> nlu) {
        this.version = version;
        this.nlu = nlu;
    }

    public List<Intent> getNlu() {
        return nlu;
    }

    public void setNlu(List<Intent> nlu) {
        this.nlu = nlu;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
