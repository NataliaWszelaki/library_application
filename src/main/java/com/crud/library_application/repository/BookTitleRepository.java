package com.crud.library_application.repository;

import com.crud.library_application.domain.BookTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface BookTitleRepository extends CrudRepository<BookTitle, Long> {

    @Override
    List<BookTitle> findAll();
}
