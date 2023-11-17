package com.crud.library_application.repository;

import com.crud.library_application.domain.BookLoan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface BookLoanRepository extends CrudRepository<BookLoan, Long> {

    @Override
    List<BookLoan> findAll();
}
