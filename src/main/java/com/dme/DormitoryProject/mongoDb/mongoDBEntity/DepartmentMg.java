package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.PersonnelRequestForm;
import com.dme.DormitoryProject.entity.Staff;
import jakarta.persistence.OneToMany;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "department")
public class DepartmentMg extends BaseEntityMg{
    private Long departmentId;
    private String name;
    @DBRef
    private List<StaffMg> staffList;
    @DBRef
    private List<PersonnelRequestFormMg> personnelRequestForms;

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public List<StaffMg> getStaffList(){
        return staffList;
    }
    public void setStaffList(List<StaffMg> staffList) {
        this.staffList = staffList;
    }
    public List<PersonnelRequestFormMg> getPersonnelRequestForms() {
        return personnelRequestForms;
    }
    public void setPersonnelRequestForms(List<PersonnelRequestFormMg> personnelRequestForms) {
        this.personnelRequestForms = personnelRequestForms;
    }
    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
