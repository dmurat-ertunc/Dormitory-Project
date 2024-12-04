package com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDbService;

import com.dme.DormitoryProject.dtos.bookRental.BookRentalDto;
import com.dme.DormitoryProject.response.Result;

public interface IBookRentalMgService {
    Result getAll();
    Result emptyBooks();
    Result addRentalBook(BookRentalDto bookRentalDto);
    Result bookDelivery(Long id);
}
