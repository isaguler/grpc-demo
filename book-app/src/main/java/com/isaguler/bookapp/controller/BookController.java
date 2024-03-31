package com.isaguler.bookapp.controller;

import com.isaguler.bookapp.dto.BookResponse;
import com.isaguler.bookapp.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/info")
    public ResponseEntity<Object> getBookInfo(@RequestParam Long id) {

        BookResponse bookInfoById = bookService.getBookInfoById(id);

        return ResponseEntity.ok().body(bookInfoById);
    }
}
