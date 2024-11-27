package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import jakarta.persistence.*;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDate;
@MappedSuperclass
public class BaseEntityMg {
    LocalDate date = LocalDate.now();

    @BsonId
    private String id;
    private boolean isDeleted = false;
    private LocalDate addDate = date;
    private boolean throwMongo = false;
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public boolean getIsDeleted(){
        return isDeleted;
    }
    public LocalDate getAddDate() {
        return addDate;
    }
    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }
    public boolean isThrowMongo() {
        return throwMongo;
    }
    public void setThrowMongo(boolean throwMongo) {
        this.throwMongo = throwMongo;}

}
