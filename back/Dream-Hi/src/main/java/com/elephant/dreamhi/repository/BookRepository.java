package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    @Query("SELECT b "
            + "FROM Book b "
            + "JOIN b.volunteer v "
            + "JOIN v.user u "
            + "JOIN b.process p "
            + "WHERE u.id = :userId "
            + "AND p.id = :processId ")
    Optional<Book> findByUserIdAndProcessId(@Param("userId") Long userId, @Param("processId") Long processId);

    Optional<Book> findByIdAndVolunteerIdIsNull(Long id);

}
