package com.dme.DormitoryProject.dtos.Book;

import com.dme.DormitoryProject.entity.Book;

public class BookMapper {

    public static BookDto toDto(Book book){

        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setType(book.getType());
        bookDto.setEmpty(book.isEmpty());

        return bookDto;
    }

    public static Book toEntity(BookDto bookDto){

        Book book = new Book();

        book.setType(bookDto.getType());
        book.setEmpty(bookDto.isEmpty());
        book.setName(bookDto.getName());

        return book;
    }
}
