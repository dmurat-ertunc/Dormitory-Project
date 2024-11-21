package com.dme.DormitoryProject.business.services;

import com.dme.DormitoryProject.dtos.bookRental.BookRentalDto;
import com.dme.DormitoryProject.response.Result;

public interface IBookRentalSerivice {
    Result getAll();
    Result emptyBooks();
    Result addRentalBook(BookRentalDto bookRentalDto);
    Result bookDelivery(Long id);
}
