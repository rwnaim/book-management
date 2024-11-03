package com.example.bookmanagementperqara.controller;

import com.example.bookmanagementperqara.book.controller.BookManagementController;
import com.example.bookmanagementperqara.book.dto.BookRequestDto;
import com.example.bookmanagementperqara.book.dto.BookResponseDto;
import com.example.bookmanagementperqara.book.service.BookManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(BookManagementController.class)
public class BookManagementControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookManagementService bookManagementService;

    @Autowired
    private ObjectMapper objectMapper;

    private BookRequestDto bookRequestDto;
    private BookResponseDto bookResponseDto;

    @BeforeEach
    void setUp() {
        bookRequestDto = new BookRequestDto();
        bookRequestDto.setTitle("Test Title Book");
        bookRequestDto.setAuthor("Naim");
        bookRequestDto.setCategory("Personal Development");
        bookRequestDto.setDescription("This is a book description");
        bookRequestDto.setPrice(BigDecimal.valueOf(100000));

        bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(1L);
        bookResponseDto.setTitle("Test Title Book");
        bookResponseDto.setAuthor("Naim");
        bookResponseDto.setCategory("Personal Development");
        bookResponseDto.setDescription("This is a book description");
        bookRequestDto.setPrice(BigDecimal.valueOf(100000));
        bookResponseDto.setIsDeleted(false);
    }

    @Test
    void getAllBooks() throws Exception{
        when(bookManagementService.getAllBook()).thenReturn(List.of(bookResponseDto));

        mockMvc.perform(get("/book-service/api/books"));
        verify(bookManagementService, times(1)).getAllBook();
    }

    @Test
    void getBookById() throws Exception {
        when(bookManagementService.getBookById(1L)).thenReturn(bookResponseDto);

        mockMvc.perform(get("/book-service/api/books/1"));
        verify(bookManagementService, times(1)).getBookById(1L);
    }

    @Test
    void createBook() throws Exception {
        when(bookManagementService.createBook(bookRequestDto)).thenReturn(bookResponseDto);

        mockMvc.perform(post("/book-service/api/books")
                .content(objectMapper.writeValueAsString(bookRequestDto)));
    }

    @Test
    void updateBookById() throws Exception {
        when(bookManagementService.updateBook(1L, bookRequestDto)).thenReturn(bookResponseDto);

        mockMvc.perform(post("/book-service/api/books/1")
                .content(objectMapper.writeValueAsString(bookRequestDto)));
    }

    @Test
    void deleteBookById() throws Exception {
        mockMvc.perform(post("/book-service/api/books/1"));
    }
}
