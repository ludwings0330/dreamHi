package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
