package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.Book.BookDto;
import com.dme.DormitoryProject.response.Result;

import java.util.List;

public interface IBookService {
    Result getAll();
    Result getName(String name);
    Result save(BookDto bookDto);
    Result saveAll(List<BookDto> bookDtoList);
    Result update(Long id,BookDto bookDto);
    Result delete(Long id);
}
