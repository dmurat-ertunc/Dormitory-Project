package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.enums.EntryExit.EntryOrExit;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalTime;

@Document(collection = "entryExit")
public class EntryExitMg extends BaseEntityMg{
    private Long entryExitID;
    @BsonRepresentation(BsonType.STRING)
    private EntryOrExit entryExit;
    @DBRef
    private Student student;
    private LocalTime momentaryTime = LocalTime.now();

    public EntryOrExit getEntryExit() {
        return entryExit;
    }
    public void setEntryExit(EntryOrExit entryExit) {
        this.entryExit = entryExit;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public LocalTime getMomentaryTime() {
        return momentaryTime;
    }
    public void setMomentaryTime(LocalTime momentaryTime) {
        this.momentaryTime = momentaryTime;
    }
    public Long getEntryExitID() {
        return entryExitID;
    }
    public void setEntryExitID(Long entryExitID) {
        this.entryExitID = entryExitID;
    }
}
