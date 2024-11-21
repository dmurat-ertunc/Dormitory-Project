package com.dme.DormitoryProject.dtos.Book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookDto {

    private Long id;
    @NotNull(message = "Kitap ismi alanı boş geçilemez")
    @NotEmpty(message = "Kitap ismi alanı boş geçilemez")
    private String name;
    @NotNull(message = "Kitap türü alanı boş geçilemez")
    @NotEmpty(message = "Kitap türü alanı boş geçilemez")
    private String type;
    private boolean isEmpty;

    public BookDto(){

    }

    public BookDto(Long id, String name, String type, boolean isEmpty) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isEmpty = isEmpty;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
}
