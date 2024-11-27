package com.dme.DormitoryProject.mongoDb.mongoDBEntity;

import com.dme.DormitoryProject.entity.BookRental;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "book")
public class BookMg extends BaseEntityMg{
    private Long bookId;
    private String name;
    private String type;
    private boolean isEmpty=true;
    @DBRef
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
        isEmpty = empty;
    }
    public List<BookRental> getBookRentals() {
        return bookRentals;
    }
    public void setBookRentals(List<BookRental> bookRentals) {
        this.bookRentals = bookRentals;
    }
}
