package com.example.bookmanagementperqara.book.service;

import com.example.bookmanagementperqara.book.dto.BookRequestDto;
import com.example.bookmanagementperqara.book.dto.BookResponseDto;
import com.example.bookmanagementperqara.book.entity.BookEntity;
import com.example.bookmanagementperqara.book.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class BookManagementService {
    private final BookRepository bookRepository;

    public List<BookResponseDto> getAllBook() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    public BookResponseDto getBookById(Long id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
        return convertToResponseDto(book);
    }

    public BookResponseDto createBook(@Valid BookRequestDto requestDto) {
        BookEntity book = BookEntity.builder()
                .title(requestDto.getTitle())
                .author(requestDto.getAuthor())
                .description(requestDto.getDescription())
                .category(requestDto.getCategory())
                .price(requestDto.getPrice())
                .isDeleted(false)
                .createdAt(LocalDateTime.now())
                .build();
        BookEntity savedBook = bookRepository.save(book);
        return convertToResponseDto(savedBook);
    }

    public BookResponseDto updateBook(Long id, BookRequestDto requestDto) {
        BookEntity bookToUpdate = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
        bookToUpdate.setTitle(requestDto.getTitle());
        bookToUpdate.setAuthor(requestDto.getAuthor());
        bookToUpdate.setDescription(requestDto.getDescription());
        bookToUpdate.setCategory(requestDto.getCategory());
        bookToUpdate.setPrice(requestDto.getPrice());
        bookToUpdate.setUpdatedAt(LocalDateTime.now());

        BookEntity updatedBook = bookRepository.save(bookToUpdate);
        return convertToResponseDto(updatedBook);
    }

    public void deleteBook(Long id) {
        BookEntity bookToDelete = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
        bookToDelete.setDeletedAt(LocalDateTime.now());
        bookToDelete.setIsDeleted(true);
        bookRepository.save(bookToDelete);
    }

    private BookResponseDto convertToResponseDto(BookEntity book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setAuthor(book.getAuthor());
        bookResponseDto.setDescription(book.getDescription());
        bookResponseDto.setCategory(book.getCategory());
        bookResponseDto.setIsDeleted(book.getIsDeleted());
        bookResponseDto.setPrice(book.getPrice());
        return bookResponseDto;
    }
}
