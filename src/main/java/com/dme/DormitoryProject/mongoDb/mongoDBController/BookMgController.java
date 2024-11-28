package com.dme.DormitoryProject.mongoDb.mongoDBController;

import com.dme.DormitoryProject.dtos.Book.BookDto;
import com.dme.DormitoryProject.mongoDb.mongoDBBusiness.mongoDbService.IBookMgService;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mg/books")
public class BookMgController {

    private IBookMgService bookMgService;

    @Autowired
    public BookMgController(IBookMgService bookMgService) {
        this.bookMgService = bookMgService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.bookMgService.getAll();
    }

    @PostMapping("save")
    public Result save(BookDto bookDto){
        return this.bookMgService.save(bookDto);
    }


}
