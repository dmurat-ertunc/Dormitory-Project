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
    private DepartmentMg department;
    @DBRef
    private TitleMg title;
    @DBRef
    private ManagerMg manager;
    private String description;
    @BsonRepresentation(BsonType.STRING)
    private RequestStatus status = RequestStatus.Pending;
    @DBRef
    private ManagerMg requestorManager;

    public DepartmentMg getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentMg department) {
        this.department = department;
    }

    public TitleMg getTitle() {
        return title;
    }

    public void setTitle(TitleMg title) {
        this.title = title;
    }

    public ManagerMg getManager() {
        return manager;
    }

    public void setManager(ManagerMg manager) {
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

    public ManagerMg getRequestorManager() {
        return requestorManager;
    }

    public void setRequestorManager(ManagerMg requestorManager) {
        this.requestorManager = requestorManager;
    }

    public Long getPersonnelRequestFormId() {
        return personnelRequestFormId;
    }

    public void setPersonnelRequestFormId(Long personnelRequestFormId) {
        this.personnelRequestFormId = personnelRequestFormId;
    }
}
