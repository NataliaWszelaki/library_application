package com.crud.library_application.RepositoryTestSuite;

import com.crud.library_application.domain.*;
import com.crud.library_application.repository.BookCopyRepository;
import com.crud.library_application.repository.BookLoanRepository;
import com.crud.library_application.repository.BookTitleRepository;
import com.crud.library_application.repository.ReaderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookLoanRepositoryTests {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookTitleRepository bookTitleRepository;

    BookLoan bookLoan;
    BookTitle bookTitle;
    BookCopy bookCopy;
    Reader reader;

    @BeforeEach
    void setUp() {
        bookTitle = new BookTitle("Biohacking", "John Smith", LocalDate.of(2000, 1, 1));
        bookTitleRepository.save(bookTitle);
        bookCopy = new BookCopy(bookTitle, LoanStatus.AVAILABLE);
        bookCopyRepository.save(bookCopy);
        reader = new Reader("John", "Smith", LocalDate.of(2021, 12, 12));
        readerRepository.save(reader);
        bookLoan = new BookLoan(bookCopy, reader, LocalDate.of(2023, 11, 12), null);
        bookLoanRepository.save(bookLoan);
    }

    @Test
    void testFindAllBy() {

        //When
        List<BookLoan> bookLoanList = bookLoanRepository.findAll();

        //Then
        assertNotNull(bookLoanList);
        assertEquals(1, bookLoanList.size());

        //CleanUp
        Long id = bookLoanList.get(0).getId();
        bookLoanRepository.deleteById(id);
    }

    @Test
    void testFindById() {

        //When
        BookLoan foundBookLoan = bookLoanRepository.findById(bookLoan.getId()).orElse(null);
        assertNotNull(foundBookLoan);

        //Then
        assertEquals(bookLoan.getId(), foundBookLoan.getId());
        assertEquals(bookLoan.getBookCopy().getLoanStatus(), foundBookLoan.getBookCopy().getLoanStatus());
        assertEquals(bookLoan.getReader().getName(), foundBookLoan.getReader().getName());
        assertEquals(bookLoan.getBookBorrowDate(), foundBookLoan.getBookBorrowDate());
        assertEquals(bookLoan.getBookReturnDate(), foundBookLoan.getBookReturnDate());

        //CleanUp
        Long id = foundBookLoan.getId();
        bookLoanRepository.deleteById(id);
    }

    @Test
    void testCreate() {

        //Then
        Long id = bookLoan.getId();
        Optional<BookLoan> bookLoanOptional = bookLoanRepository.findById(id);
        assertTrue(bookLoanOptional.isPresent());

        //CleanUp
        Long id2 = bookLoanOptional.get().getId();
        bookLoanRepository.deleteById(id2);
    }

    @Test
    void testUpDate() {

        //When
        bookLoan.setBookReturnDate(LocalDate.now());
        bookLoanRepository.save(bookLoan);
        BookLoan updatedBookLoan = bookLoanRepository.findById(bookLoan.getId()).orElse(null);
        assertNotNull(updatedBookLoan);

        //Then
        assertEquals(LocalDate.now(), bookLoan.getBookReturnDate());

        //CleanUp
        Long id = updatedBookLoan.getId();
        bookLoanRepository.deleteById(id);
    }

    @Test
    void testDelete() {

        //When
        Long id = bookLoan.getId();
        bookLoanRepository.deleteById(id);

        //Then
        Optional<BookLoan> optionalBookLoan = bookLoanRepository.findById(id);
        assertFalse(optionalBookLoan.isPresent());
    }

    @Test
    void testRelationBookLoanBookCopyReader() {

        //Given
        bookTitle.getBookCopyList().add(bookCopy);
        bookTitleRepository.save(bookTitle);
        reader.getBookLoanList().add(bookLoan);
        readerRepository.save(reader);
        bookCopy.getBookLoanList().add(bookLoan);
        bookCopyRepository.save(bookCopy);

        //When
        BookLoan bookLoanWithAllData = bookLoanRepository.findById(bookLoan.getId()).orElse(null);
        assertNotNull(bookLoanWithAllData);
        Reader readerWithAllData = readerRepository.findById(reader.getId()).orElse(null);
        assertNotNull(readerWithAllData);
        BookCopy bookCopyWithAllData = bookCopyRepository.findById(bookCopy.getId()).orElse(null);
        assertNotNull(bookCopyWithAllData);
        BookTitle bookTitleWithAllData = bookTitleRepository.findById(bookTitle.getId()).orElse(null);
        assertNotNull(bookTitleWithAllData);

        //Then
        assertEquals("John", bookLoanWithAllData.getReader().getName());
        assertEquals("John Smith", bookLoanWithAllData.getBookCopy().getBookTitle().getAuthorName());
        assertEquals(LoanStatus.AVAILABLE, bookLoanWithAllData.getBookCopy().getLoanStatus());
        assertNotNull(readerWithAllData.getBookLoanList());
        assertNotNull(bookCopyWithAllData.getBookLoanList());
        assertNotNull(bookTitleWithAllData.getBookCopyList());

        //CleanUp
        Long id = bookLoanWithAllData.getId();
        bookLoanRepository.deleteById(id);
    }
}
