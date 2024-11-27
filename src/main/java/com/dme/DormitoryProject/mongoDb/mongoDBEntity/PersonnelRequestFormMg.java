package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.Department;
import com.dme.DormitoryProject.entity.Manager;
import com.dme.DormitoryProject.entity.Title;
import com.dme.DormitoryProject.enums.RequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personnelRequestForm")
public class PersonnelRequestFormMg extends BaseEntityMg{
    private Long personnelRequestFormId;
    @DBRef
    private Department department;
    @DBRef
    private Title title;
    @DBRef
    private Manager manager;
    private String description;
    @BsonRepresentation(BsonType.STRING)
    private RequestStatus status = RequestStatus.Pending;
    @DBRef
    private Manager requestorManager;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Manager getRequestorManager() {
        return requestorManager;
    }

    public void setRequestorManager(Manager requestorManager) {
        this.requestorManager = requestorManager;
    }

    public Long getPersonnelRequestFormId() {
        return personnelRequestFormId;
    }

    public void setPersonnelRequestFormId(Long personnelRequestFormId) {
        this.personnelRequestFormId = personnelRequestFormId;
    }
}
