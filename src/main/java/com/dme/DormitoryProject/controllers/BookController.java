package com.dme.DormitoryProject.controllers;

import com.dme.DormitoryProject.business.services.IBookService;
import com.dme.DormitoryProject.dtos.Book.BookDto;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

    private IBookService bookService;

    @Autowired
    public BookController(IBookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.bookService.getAll();
    }

    @GetMapping("getName")
    public Result getName(@RequestParam String name){
        return this.bookService.getName(name);
    }

    @PostMapping("save")
    public Result save(@Valid @RequestBody BookDto bookDto){
        return this.bookService.save(bookDto);
    }

    @PostMapping("saveAll")
    public Result saveAll(@Valid @RequestBody List<BookDto> bookDtoList){
        return this.bookService.saveAll(bookDtoList);
    }

    @PutMapping("update/{id}")
    public Result update(@Valid @RequestBody BookDto bookDto,@PathVariable Long id){
        return this.bookService.update(id,bookDto);
    }

    @PutMapping("delete/{id}")
    public Result delete(@PathVariable Long id){
        return this.bookService.delete(id);
    }
}
