package com.dme.DormitoryProject.dtos.Book;

import com.dme.DormitoryProject.entity.Book;
import com.dme.DormitoryProject.mongoDb.mongoDBEntity.BookMg;

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

    public static BookDto toDtoMg(BookMg bookMg){

        BookDto bookDto = new BookDto();

        bookDto.setId(bookMg.getBookId());
        bookDto.setName(bookMg.getName());
        bookDto.setType(bookMg.getType());
        bookDto.setEmpty(bookMg.isEmpty());

        return bookDto;
    }

    public static BookMg toEntityMg(BookDto bookDto){

        BookMg bookmg = new BookMg();

        bookmg.setType(bookDto.getType());
        bookmg.setEmpty(bookDto.isEmpty());
        bookmg.setName(bookDto.getName());

        return bookmg;
    }
}
