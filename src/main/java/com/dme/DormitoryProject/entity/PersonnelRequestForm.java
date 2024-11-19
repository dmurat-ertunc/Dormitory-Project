package com.dme.DormitoryProject.entity;

import com.dme.DormitoryProject.enums.RequestStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "personnelRequestForm")
public class PersonnelRequestForm extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "titleId")
    private Title title;
    @ManyToOne
    @JoinColumn(name = "managerId")
    private Manager manager;
    private String description;
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.Pending;
    @ManyToOne
    @JoinColumn(name = "requestorManagerId")
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
}
