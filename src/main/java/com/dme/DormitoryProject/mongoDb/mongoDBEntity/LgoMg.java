package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.LogLevel;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lgo")
public class LgoMg extends BaseEntityMg{
    private Long lgoId;
    @DBRef
    private LogLevelMg logLevel;
    private String message;

    public LogLevelMg getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevelMg logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getLgoId() {
        return lgoId;
    }

    public void setLgoId(Long lgoId) {
        this.lgoId=lgoId;
    }
}
