package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.Lgo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "logLevel")
public class LogLevelMg extends BaseEntityMg{
    private Long logLevelId;
    private String description;
    @DBRef
    private List<LgoMg> lgoList;

    public List<LgoMg> getLgoList() {
        return lgoList;
    }
    public void setLgoList(List<LgoMg> lgoList) {
        this.lgoList = lgoList;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getLogLevelId() {
        return logLevelId;
    }
    public void setLogLevelId(Long logLevelId) {
        this.logLevelId = logLevelId;
    }
}
