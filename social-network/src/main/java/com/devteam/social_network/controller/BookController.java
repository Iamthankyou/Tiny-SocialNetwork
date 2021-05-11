package com.devteam.social_network.controller;

import com.devteam.social_network.domain.Book;
import com.devteam.social_network.sdo.TopTenBookSdo;
import com.devteam.social_network.service.BookServiceCustom;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-controller")
public class BookController {
    @Autowired
    BookServiceCustom bookServiceCustom;

    @PostMapping("/insert-book")
    @ApiOperation("insert-book")
    public ResponseEntity<Book> insertBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.OK).body(bookServiceCustom.insertBook(book));
    }

    @GetMapping("/get-list-book-by-type")
    @ApiOperation("get-list-book-by-type")
    public ResponseEntity<List<Book>> getListBookByType(@RequestParam(required = true) String type){
        return ResponseEntity.status(HttpStatus.OK).body(bookServiceCustom.getListBookByType(type));
    }

    @GetMapping("/get-list-book-by-name")
    @ApiOperation("get-list-book-by-name")
    public ResponseEntity<List<Book>> getListBookByName(@RequestParam(required = true) String name){
        return ResponseEntity.status(HttpStatus.OK).body(bookServiceCustom.getListBookByName(name));
    }

    @GetMapping("/get-book-by-name")
    @ApiOperation("get-book-by-name")
    public ResponseEntity<Book> getBookByName(@RequestParam(required = true) String name){
        return ResponseEntity.status(HttpStatus.OK).body(bookServiceCustom.getBookByName(name));
    }

    @GetMapping("/get-book-by-id")
    @ApiOperation("get-book-by-id")
    public ResponseEntity<Book> getBookById(@RequestParam(required = true) String bookId){
        return ResponseEntity.status(HttpStatus.OK).body(bookServiceCustom.getBookById(bookId));
    }

    @PostMapping("/get-list-book-by-list-id")
    @ApiOperation("get-list-book-by-list-id")
    public ResponseEntity<List<Book>> getListBookByListId(@RequestBody List<String> listId){
        return ResponseEntity.status(HttpStatus.OK).body(bookServiceCustom.getListBookByListId(listId));
    }

    @GetMapping("/get-all-book")
    @ApiOperation("get-all-book")
    public ResponseEntity<List<Book>> getAllBook(){
        return ResponseEntity.status(HttpStatus.OK).body(bookServiceCustom.getAllBook());
    }
}
