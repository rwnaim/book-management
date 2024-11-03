package com.example.bookmanagementperqara.book.controller;

import com.example.bookmanagementperqara.book.dto.BookRequestDto;
import com.example.bookmanagementperqara.book.dto.BookResponseDto;
import com.example.bookmanagementperqara.book.service.BookManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-service/api/books")
@Validated
public class BookManagementController {

    private final BookManagementService bookManagementService;

    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all books")
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getBooks() {
        List<BookResponseDto> books = bookManagementService.getAllBook();
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the book"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        BookResponseDto bookResponse = bookManagementService.getBookById(id);
        return ResponseEntity.ok(bookResponse);
    }

    @Operation(summary = "Create a book")
    @ApiResponse(responseCode = "201", description = "Successfully created the book")
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto requestDto) {
        BookResponseDto createdBook = bookManagementService.createBook(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @Operation(summary = "Update a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the book"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDto requestDto) {
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
