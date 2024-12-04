package com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDbService;

import com.dme.DormitoryProject.dtos.Book.BookDto;
import com.dme.DormitoryProject.response.Result;

public interface IBookMgService {

    Result getAll();
    Result getByIdAndRentals(Long id);
    Result save(BookDto bookDto);
}
