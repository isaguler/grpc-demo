package com.isaguler.bookapp.service;

import com.isaguler.bookapp.dto.BookResponse;
import com.isaguler.bookapp.model.Book;
import com.isaguler.bookapp.repository.BookRepository;
import com.isaguler.grpc.WarehouseInfoServiceGrpc;
import com.isaguler.grpc.WarehouseRequest;
import com.isaguler.grpc.WarehouseResponse;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookService {

    @GrpcClient("warehouse")
    private WarehouseInfoServiceGrpc.WarehouseInfoServiceBlockingStub warehouseInfoServiceBlockingStub;

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse getBookInfoById(Long bookId) {
        Optional<Book> byId = bookRepository.findById(bookId);

        if (byId.isPresent()) {
            WarehouseResponse warehouseResponse =
                    warehouseInfoServiceBlockingStub.getByBookId(WarehouseRequest.newBuilder().setId(byId.get().getId()).build());

            BookResponse bookResponse = BookResponse.convertToDto(byId.get(), warehouseResponse);

            log.info("BookResponse : " + bookResponse);

            return bookResponse;
        } else {
            log.error("book not found for id : " + bookId);
            return new BookResponse();
        }
    }

}
