package com.crud.library_application.repository;

import com.crud.library_application.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    boolean existsByNameAndSurname(String name, String surname);
}
