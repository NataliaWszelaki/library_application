package com.crud.library_application.RepositoryTestSuite;

import com.crud.library_application.domain.BookCopy;
import com.crud.library_application.domain.BookTitle;
import com.crud.library_application.domain.LoanStatus;
import com.crud.library_application.repository.BookCopyRepository;
import com.crud.library_application.repository.BookTitleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookTitleRepositoryTests {

    @Autowired
    private BookTitleRepository bookTitleRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    private BookTitle bookTitle;

    @BeforeEach
    void setUp() {
        bookTitle = new BookTitle("Biohacking", "John Smith", LocalDate.of(1999, 12, 12));
        bookTitleRepository.save(bookTitle);
    }

    @Test
    void testFindAllBy() {

        //When
        List<BookTitle> bookTitleList = bookTitleRepository.findAll();

        //Then
        assertNotNull(bookTitleList);
        assertEquals(1, bookTitleList.size());

        //CleanUp
        Long id = bookTitleList.get(0).getId();
        bookTitleRepository.deleteById(id);
    }

    @Test
    void testFindById() {

        //When
        BookTitle foundBookTitle = bookTitleRepository.findById(bookTitle.getId()).orElse(null);
        assertNotNull(foundBookTitle);

        //Then
        assertEquals(bookTitle.getId(), foundBookTitle.getId());
        assertEquals(bookTitle.getTitle(), foundBookTitle.getTitle());
        assertEquals(bookTitle.getAuthorName(), foundBookTitle.getAuthorName());
        assertEquals(bookTitle.getBookPublicationDate(), foundBookTitle.getBookPublicationDate());

        //CleanUp
        Long id = foundBookTitle.getId();
        bookTitleRepository.deleteById(id);
    }

    @Test
    void testCreate() {

        //Then
        Long id = bookTitle.getId();
        Optional<BookTitle> bookTitleOptional = bookTitleRepository.findById(id);
        assertTrue(bookTitleOptional.isPresent());

        //CleanUp
        Long id2 = bookTitleOptional.get().getId();
        bookTitleRepository.deleteById(id2);
    }

    @Test
    void testUpdate() {

        //When
        bookTitle.setTitle("Fountain");
        bookTitleRepository.save(bookTitle);
        BookTitle updatedBookTitle = bookTitleRepository.findById(bookTitle.getId()).orElse(null);
        assertNotNull(updatedBookTitle);

        //Then
        assertEquals("Fountain", bookTitle.getTitle());

        //CleanUp
        Long id = updatedBookTitle.getId();
        bookTitleRepository.deleteById(id);
    }

    @Test
    void testDelete() {

        //When
        Long id = bookTitle.getId();
        bookTitleRepository.deleteById(id);

        //Then
        BookTitle bookTitle1 = bookTitleRepository.findById(id).orElse(null);
        assertNull(bookTitle1);
    }
}
