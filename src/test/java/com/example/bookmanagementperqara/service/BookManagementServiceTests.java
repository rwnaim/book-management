package com.example.bookmanagementperqara.service;

import com.example.bookmanagementperqara.book.dto.BookRequestDto;
import com.example.bookmanagementperqara.book.dto.BookResponseDto;
import com.example.bookmanagementperqara.book.entity.BookEntity;
import com.example.bookmanagementperqara.book.repository.BookRepository;
import com.example.bookmanagementperqara.book.service.BookManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookManagementServiceTests {

    @InjectMocks
    private BookManagementService bookManagementService;

    @Mock
    private BookRepository bookRepository;


    private BookEntity bookEntity;
    private BookRequestDto bookRequest;

    @BeforeEach
    void setUp() {
        bookEntity = new BookEntity();
        bookEntity.setId(1L);
        bookEntity.setTitle("Test Title Book");
        bookEntity.setAuthor("Naim");
        bookEntity.setCategory("Personal Development");
        bookEntity.setDescription("This is a book description");
        bookEntity.setPrice(BigDecimal.valueOf(100000));
        bookEntity.setIsDeleted(false);
        bookEntity.setCreatedAt(LocalDateTime.now());

        bookRequest = new BookRequestDto();
        bookRequest.setTitle("Updated Title Book");
        bookRequest.setAuthor("Rana Wijdan Naim");
        bookRequest.setCategory("Personal Development and Self Help");
        bookRequest.setDescription("This is a book description after update");
        bookEntity.setPrice(BigDecimal.valueOf(100000));

        BookResponseDto bookResponse = new BookResponseDto();
        bookResponse.setId(1L);
        bookResponse.setTitle("Test Title Book");
        bookResponse.setAuthor("Naim");
        bookResponse.setCategory("Personal Development");
        bookResponse.setDescription("This is a book description");
        bookEntity.setPrice(BigDecimal.valueOf(100000));
        bookResponse.setIsDeleted(false);
    }

    @Test
    void testGetAllBook() {
        when(bookRepository.findAll()).thenReturn(List.of(bookEntity));
        List<BookResponseDto> bookResponseDto = bookManagementService.getAllBook();

        assertThat(bookResponseDto).hasSize(1);
        assertThat(bookResponseDto.getFirst().getTitle()).isEqualTo("Test Title Book");
        verify(bookRepository).findAll();
    }

    @Test
    void testGetBookById_Exists() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(bookEntity));
        BookResponseDto bookResponseDto = bookManagementService.getBookById(1L);

        assertNotNull(bookResponseDto);
        assertThat(bookResponseDto.getTitle()).isEqualTo("Test Title Book");
        verify(bookRepository).findById(1L);
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(RuntimeException.class, () -> bookManagementService.getBookById(1L));
    }

    @Test
    void testCreateBook() {
        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);
        BookResponseDto bookResponseDto = bookManagementService.createBook(bookRequest);

        assertThat(bookResponseDto.getTitle()).isEqualTo("Test Title Book");
        verify(bookRepository, times(1)).save(any(BookEntity.class));
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(bookEntity));
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);

        BookResponseDto bookResponseDto = bookManagementService.updateBook(1L, bookRequest);

        assertThat(bookResponseDto.getTitle()).isEqualTo("Updated Title Book");
        verify(bookRepository).findById(1L);
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(bookEntity));
        bookManagementService.deleteBook(1L);

        assertThat(bookEntity.getIsDeleted()).isTrue();
        verify(bookRepository).findById(1L);
    }

}
