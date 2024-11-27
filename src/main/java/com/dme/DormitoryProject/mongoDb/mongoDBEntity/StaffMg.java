package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.Department;
import com.dme.DormitoryProject.entity.Manager;
import com.dme.DormitoryProject.entity.Title;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "staff")
public class StaffMg extends BaseEntityMg{
    private Long staffId;
    private String name;
    private String surName;
    private String mail;
    private int salary;
    private String phoneNumber;
    @DBRef
    private Department department;
    @DBRef
    private Title title;
    @DBRef
    private Manager manager;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSurName(){
        return surName;
    }
    public void setSurName(String surName){
        this.surName=surName;
    }
    public String getMail(){
        return mail;
    }
    public void setMail(String mail){
        this.mail=mail;
    }
    public int getSalary(){
        return  salary;
    }
    public void setSalary(int salary){
        this.salary=salary;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public Department getDepartment(){
        return department;
    }
    public void setDepartment(Department department){
        this.department=department;
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

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
