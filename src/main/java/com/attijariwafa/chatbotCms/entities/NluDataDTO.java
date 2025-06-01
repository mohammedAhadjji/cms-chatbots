package com.attijariwafa.chatbotCms.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NluDataDTO {
    private String version;
    private List<Intent> nlu;

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
