package com.isaguler.bookapp.dto;

import com.isaguler.bookapp.model.Book;
import com.isaguler.grpc.WarehouseResponse;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private BigDecimal price;

    private Long count;

    private BigDecimal discountAmount;

    public static BookResponse convertToDto(Book book, WarehouseResponse warehouseResponse) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setCount(warehouseResponse.getCount());
        bookResponse.setDiscountAmount(new BigDecimal(warehouseResponse.getDiscountAmount()));

        return bookResponse;
    }

}
