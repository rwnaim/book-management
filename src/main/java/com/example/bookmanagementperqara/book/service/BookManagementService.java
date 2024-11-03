package com.example.bookmanagementperqara.book.service;

import com.example.bookmanagementperqara.book.dto.BookRequestDto;
import com.example.bookmanagementperqara.book.dto.BookResponseDto;
import com.example.bookmanagementperqara.book.entity.BookEntity;
import com.example.bookmanagementperqara.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookManagementService {
    private final BookRepository bookRepository;

    public List<BookResponseDto> getAllBook() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream().map(this::convertToResponseDto).toList();
    }

    public BookResponseDto getBookById(Long id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToResponseDto(book);
    }

    public BookResponseDto createBook(BookRequestDto requestDto) {
        BookEntity book = BookEntity.builder()
                .title(requestDto.getTitle())
                .author(requestDto.getAuthor())
                .description(requestDto.getDescription())
                .category(requestDto.getCategory())
                .isDeleted(false)
                .createdAt(LocalDateTime.now())
                .build();
        BookEntity savedBook = bookRepository.save(book);
        return convertToResponseDto(savedBook);
    }

    public BookResponseDto updateBook(Long id, BookRequestDto requestDto) {
        BookEntity bookToUpdate = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookToUpdate.setTitle(requestDto.getTitle());
        bookToUpdate.setAuthor(requestDto.getAuthor());
        bookToUpdate.setDescription(requestDto.getDescription());
        bookToUpdate.setCategory(requestDto.getCategory());
        bookToUpdate.setUpdatedAt(LocalDateTime.now());

        BookEntity updatedBook = bookRepository.save(bookToUpdate);
        return convertToResponseDto(updatedBook);
    }

    public void deleteBook(Long id) {
        BookEntity bookToDelete = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookToDelete.setDeletedAt(LocalDateTime.now());
        bookToDelete.setDeleted(true);
        bookRepository.save(bookToDelete);
    }

    private BookResponseDto convertToResponseDto(BookEntity book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setAuthor(book.getAuthor());
        bookResponseDto.setDescription(book.getDescription());
        bookResponseDto.setCategory(book.getCategory());
        bookResponseDto.setIsDeleted(book.isDeleted());
        return bookResponseDto;
    }
}
