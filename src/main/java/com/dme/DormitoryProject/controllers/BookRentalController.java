package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IBookRentalSerivice;
import com.dme.DormitoryProject.dtos.bookRental.BookRentalDto;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bookRentals")
public class BookRentalController {

    private IBookRentalSerivice bookRentalSerivice;

    @Autowired
    public BookRentalController(IBookRentalSerivice bookRentalSerivice){
        this.bookRentalSerivice=bookRentalSerivice;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.bookRentalSerivice.getAll();
    }
    @GetMapping("emptyBook")
    public Result emptyBook(){
        return this.bookRentalSerivice.emptyBooks();
    }
    @PostMapping("addRentalBook")
    public Result addRentalBook(@Valid @RequestBody BookRentalDto bookRentalDto){
        return this.bookRentalSerivice.addRentalBook(bookRentalDto);
    }
    @PutMapping("bookDelivery/{id}")
    public Result bookDelivery(@PathVariable Long id){
        return this.bookRentalSerivice.bookDelivery(id);
    }
}
