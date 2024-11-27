package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.StudentRequestRental;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "sportArea")
public class SportAreaMg extends BaseEntityMg{
    private Long sportAreaId;
    private String sporType;
    @DBRef
    private List<Rental> rentalList;
    @DBRef
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

    public Long getSportAreaId() {
        return sportAreaId;
    }

    public void setSportAreaId(Long sportAreaId) {
        this.sportAreaId = sportAreaId;
    }
}
