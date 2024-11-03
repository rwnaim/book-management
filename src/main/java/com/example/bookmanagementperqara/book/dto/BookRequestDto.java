package com.example.bookmanagementperqara.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto {

    @NotBlank(message = "Title is mandatory")
    @Size(max = 50, message = "Title must be less than 50 characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    private String author;

    @Size(max = 100, message = "Description must be less than 100 characters")
    private String description;

    @NotBlank(message = "Category is mandatory")
    private String category;
}
