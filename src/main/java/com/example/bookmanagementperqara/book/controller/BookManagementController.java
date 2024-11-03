package com.example.bookmanagementperqara.book.controller;

import com.example.bookmanagementperqara.book.dto.BookRequestDto;
import com.example.bookmanagementperqara.book.dto.BookResponseDto;
import com.example.bookmanagementperqara.book.service.BookManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-service/api/books")
public class BookManagementController {

    private final BookManagementService bookManagementService;

    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all books")
    @GetMapping
    public List<BookResponseDto> getBooks() {
        return bookManagementService.getAllBook();
    }

    @Operation(summary = "Get book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the book"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookManagementService.getBookById(id));
    }

    @Operation(summary = "Create a book")
    @ApiResponse(responseCode = "201", description = "Successfully created the book")
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto requestDto) {
        return ResponseEntity.ok(bookManagementService.createBook(requestDto));
    }

    @Operation(summary = "Update a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the book"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookRequestDto requestDto) {
        return ResponseEntity.ok(bookManagementService.updateBook(id, requestDto));
    }

    @Operation(summary = "Delete a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the book"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookManagementService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
