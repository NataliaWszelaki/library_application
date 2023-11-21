package com.crud.library_application.RepositoryTestSuite;

import com.crud.library_application.domain.BookCopy;
import com.crud.library_application.domain.BookTitle;
import com.crud.library_application.domain.BookCopyStatus;
import com.crud.library_application.repository.BookCopyRepository;
import com.crud.library_application.repository.BookTitleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookCopyRepositoryTests {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookTitleRepository bookTitleRepository;

    BookCopy bookCopy;
    BookTitle bookTitle;

    @BeforeEach
    void setUp() {
        bookTitle = new BookTitle("Biohacking", "John Smith", 2020);
        bookTitleRepository.save(bookTitle);
        bookCopy = new BookCopy(bookTitle, BookCopyStatus.AVAILABLE);
        bookCopyRepository.save(bookCopy);
    }

    @Test
    void testFindAllBy() {

        //When
        List<BookCopy> bookCopyList = bookCopyRepository.findAll();

        //Then
        assertNotNull(bookCopyList);
        assertEquals(1, bookCopyList.size());

        //CleanUp
        Long id = bookCopyList.get(0).getId();
        bookCopyRepository.deleteById(id);
    }

    @Test
    void testFindById() {

        //When
        BookCopy foundBookCopy = bookCopyRepository.findById(bookCopy.getId()).orElse(null);
        assertNotNull(foundBookCopy);

        //Then
        assertEquals(bookCopy.getId(), foundBookCopy.getId());
        assertEquals(bookCopy.getBookTitle().getTitle(), foundBookCopy.getBookTitle().getTitle());
        assertEquals(bookCopy.getBookCopyStatus(), foundBookCopy.getBookCopyStatus());

        //CleanUp
        Long id = foundBookCopy.getId();
        bookCopyRepository.deleteById(id);
        Long id2 = bookTitle.getId();
        bookTitleRepository.deleteById(id2);
    }

    @Test
    void testCreate() {

        //Then
        Long id = bookCopy.getId();
        Optional<BookCopy> bookCopyOptional = bookCopyRepository.findById(id);
        assertTrue(bookCopyOptional.isPresent());

        //CleanUp
        Long id2 = bookCopyOptional.get().getId();
        bookCopyRepository.deleteById(id2);
    }

    @Test
    void testUpdate() {

        //When
        bookCopy.setBookCopyStatus(BookCopyStatus.LOST);
        bookCopyRepository.save(bookCopy);
        BookCopy updatedBookCopy = bookCopyRepository.findById(bookCopy.getId()).orElse(null);
        assertNotNull(updatedBookCopy);

        //Then
        assertEquals(BookCopyStatus.LOST, updatedBookCopy.getBookCopyStatus());

        //CleanUp
        Long id = updatedBookCopy.getId();
        bookCopyRepository.deleteById(id);
    }

    @Test
    void testDelete() {

        //When
        Long id = bookCopy.getId();
        bookCopyRepository.deleteById(id);

        //Then
        BookCopy deletedBookCopy = bookCopyRepository.findById(id).orElse(null);
        assertNull(deletedBookCopy);
    }

    @Test
    void testRelationBookTitleBookCopy() {

        //Given
        BookCopy bookCopy2 = new BookCopy(bookTitle, BookCopyStatus.LOST);
        bookCopyRepository.save(bookCopy2);

        //When
        bookTitle.getBookCopyList().add(bookCopy);
        bookTitle.getBookCopyList().add(bookCopy2);
        bookTitleRepository.save(bookTitle);
        BookCopy bookCopyTheSameTitle = bookCopyRepository.findById(bookCopy.getId()).orElse(null);
        assertNotNull(bookCopyTheSameTitle);
        BookCopy bookCopyTheSameTitle2 = bookCopyRepository.findById(bookCopy2.getId()).orElse(null);
        assertNotNull(bookCopyTheSameTitle2);
        BookTitle bookTitleWithBookCopies = bookTitleRepository.findById(bookTitle.getId()).orElse(null);
        assertNotNull(bookTitleWithBookCopies);

        //Then
        assertNotEquals(bookCopyTheSameTitle.getId(), bookCopyTheSameTitle2.getId());
        assertEquals(bookCopyTheSameTitle.getBookTitle().getTitle(), bookCopyTheSameTitle2.getBookTitle().getTitle());
        assertNotEquals(bookCopyTheSameTitle.getBookCopyStatus(), bookCopyTheSameTitle2.getBookCopyStatus());
        assertNotNull(bookTitleWithBookCopies.getBookCopyList());

        //CleanUp
        Long id = bookCopyTheSameTitle.getId();
        Long id2 = bookCopyTheSameTitle2.getId();
        bookCopyRepository.deleteById(id);
        bookCopyRepository.deleteById(id2);
    }
}
