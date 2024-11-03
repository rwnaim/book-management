package com.example.bookmanagementperqara.book.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String category;
    private Boolean isDeleted;
}
