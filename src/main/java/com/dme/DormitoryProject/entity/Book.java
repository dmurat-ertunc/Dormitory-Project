package com.dme.DormitoryProject.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bookTbl")
public class Book extends BaseEntity{

    private String name;
    private String type;
    private boolean isEmpty;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookRental> bookRentals;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isEmpty() {
        return isEmpty;
    }
    public void setEmpty(boolean empty) {
        isEmpty = true;
    }
    public List<BookRental> getBookRentals() {
        return bookRentals;
    }
    public void setBookRentals(List<BookRental> bookRentals) {
        this.bookRentals = bookRentals;
    }
}
