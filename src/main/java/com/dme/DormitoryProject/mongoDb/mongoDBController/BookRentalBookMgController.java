package com.dme.DormitoryProject.mongoDb.mongoDBController;

import com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDbService.IBookRentalMgService;
import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IBookRentalMgDao;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mg/bookRental")
public class BookRentalBookMgController {

    private IBookRentalMgService bookRentalMgService;

    @Autowired
    public BookRentalBookMgController(IBookRentalMgService bookRentalMgService) {
        this.bookRentalMgService = bookRentalMgService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.bookRentalMgService.getAll();
    }

    @GetMapping("emptyBook")
    public Result emptyBook(){
        return this.bookRentalMgService.emptyBooks();
    }
}
