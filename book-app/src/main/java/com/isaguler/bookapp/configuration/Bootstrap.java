package com.isaguler.bookapp.configuration;

import com.isaguler.bookapp.model.Book;
import com.isaguler.bookapp.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Bootstrap {

    private final BookRepository bookRepository;

    public Bootstrap(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    void init() {
        Book book1 = new Book();
        book1.setTitle("The Lord of the Rings");
        book1.setAuthor("J.R.R. Tolkien");
        book1.setPrice(new BigDecimal(100));

        Book book2 = new Book();
        book2.setTitle("Animal Farm");
        book2.setAuthor("George Orwell");
        book2.setPrice(new BigDecimal(90));

        Book book3 = new Book();
        book3.setTitle("Improbable");
        book3.setAuthor("Adam Fawer");
        book3.setPrice(new BigDecimal(80));

        bookRepository.saveAll(List.of(book1, book2, book3));
    }

}
