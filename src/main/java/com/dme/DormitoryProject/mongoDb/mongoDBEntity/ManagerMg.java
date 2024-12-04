package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.PersonnelRequestForm;
import com.dme.DormitoryProject.entity.Staff;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "manager")
public class ManagerMg extends BaseEntityMg{
    private Long managerId;
    private String name;
    private String surName;
    private String mail;
    @DBRef
    private List<StaffMg> staffList;
    private String phoneNumber;
    private String title;
    private int salary;
    @DBRef
    private List<PersonnelRequestFormMg> personnelRequestForms;


    public List<StaffMg> getStaffList() {
        return staffList;
    }
    public void setStaffList(List<StaffMg> staffList) {
        this.staffList = staffList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public List<PersonnelRequestFormMg> getManagers() {
        return personnelRequestForms;
    }
    public void setManagers(List<PersonnelRequestFormMg> personnelRequestForms) {
        this.personnelRequestForms = personnelRequestForms;
    }
    public Long getManagerId() {
        return managerId;
    }
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public List<PersonnelRequestFormMg> getPersonnelRequestForms() {
        return personnelRequestForms;
    }

    public void setPersonnelRequestForms(List<PersonnelRequestFormMg> personnelRequestForms) {
        this.personnelRequestForms = personnelRequestForms;
    }
}
