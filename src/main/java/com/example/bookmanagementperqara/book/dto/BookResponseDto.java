package com.example.bookmanagementperqara.book.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String category;
    private BigDecimal price;
    private Boolean isDeleted;
}
