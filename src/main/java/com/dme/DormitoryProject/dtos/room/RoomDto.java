package com.dme.DormitoryProject.dtos.room;

import jakarta.validation.constraints.NotNull;

public class RoomDto {

    @NotNull(message = "Oda numarası alanı boş geçilemez")
    private Long roomNo;
    @NotNull(message = "Oda maksimum kişi sayısı alanı boş geçilemez")
    private int roomSize;
    @NotNull(message = "Odada ki kişi sayısı alanı boş geçilemez")
    private int manySize;
    @NotNull(message = "Odanın aylık kişi başı ücreti alanı boş geçilemez")
    private float perPersonSize;
    private boolean isFull = false;

    public RoomDto(){

    }

    public RoomDto(Long roomNo, int roomSize, int manySize, float perPersonSize, boolean isFull) {
        this.roomNo = roomNo;
        this.roomSize = roomSize;
        this.manySize = manySize;
        this.perPersonSize = perPersonSize;
        this.isFull = isFull;
    }

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
    public float getPerPersonSize() {
        return perPersonSize;
    }
    public void setPerPersonSize(float perPersonSize) {
        this.perPersonSize = perPersonSize;
    }
    public boolean isFull() {
        return isFull;
    }
    public void setFull(boolean full) {
        isFull = full;
    }
}
