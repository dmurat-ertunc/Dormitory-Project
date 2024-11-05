package com.dme.DormitoryProject.entity;

import com.dme.DormitoryProject.SportType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sportAreaTbl")
public class SportArea extends BaseEntity{

    private String sporType;
    @OneToMany(mappedBy = "sportArea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rental> rentalList;
    @OneToMany(mappedBy = "sportArea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentRequestRental> studentRequestRentalList;

    public List<StudentRequestRental> getStudentRequestRentalList() {
        return studentRequestRentalList;
    }
    public void setStudentRequestRentalList(List<StudentRequestRental> studentRequestRentalList) {
        this.studentRequestRentalList = studentRequestRentalList;
    }
    public List<Rental> getRentalList() {
        return rentalList;
    }
    public void setRentalList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }
    public String getSporType(){
        return sporType;
    }
    public void setSporType(String sporType){
        this.sporType=sporType;
    }
}
