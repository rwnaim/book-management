package com.example.bookmanagementperqara.book.repository;

import com.example.bookmanagementperqara.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
