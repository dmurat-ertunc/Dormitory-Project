package com.dme.DormitoryProject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roomTbl")
public class Room extends BaseEntity{

    private Long roomNo;
    private int roomSize;
    private int manySize;
    private float perPersonPrice;
    private boolean isFull;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    public Long getRoomNo() {
        return roomNo;
    }
    public void setRoomNo(Long roomNo) {
        this.roomNo = roomNo;
    }
    public int getRoomSize() {
        return roomSize;
    }
    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }
    public int getManySize() {
        return manySize;
    }
    public void setManySize(int manySize) {
        this.manySize = manySize;
    }
    public float getPerPersonPrice() {
        return perPersonPrice;
    }
    public void setPerPersonPrice(float perPersonPrice) {
        this.perPersonPrice = perPersonPrice;
    }
    public boolean isFull() {
        return isFull;
    }
    public void setFull(boolean full) {
        isFull = full;
    }
}
