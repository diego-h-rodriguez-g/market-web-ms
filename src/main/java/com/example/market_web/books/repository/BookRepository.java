package com.example.market_web.books.repository;

import com.example.market_web.books.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    Page<BookEntity> findByStockGreaterThanEqual(Integer stock, Pageable pageable);
}
