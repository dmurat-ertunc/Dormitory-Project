package com.dme.DormitoryProject.entity;

import com.dme.DormitoryProject.enums.EntryExit.EntryOrExit;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "EntryExitTbl")
public class EntryExit extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private EntryOrExit entryExit;
    @ManyToOne
    @JoinColumn(name = "studentId")
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
}
