package com.crud.library_application.repository;

import com.crud.library_application.domain.BookTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookTitleRepository extends CrudRepository<BookTitle, Long> {

    @Override
    List<BookTitle> findAll();

    boolean existsByTitleAndAuthorNameAndBookPublicationYear(String title, String authorName, Integer bookPublicationDate);

    Optional<BookTitle> findByTitleAndAuthorNameAndBookPublicationYear(String title, String authorName, Integer bookPublicationDate);
}
