package com.crud.library_application.repository;

import com.crud.library_application.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();
}
