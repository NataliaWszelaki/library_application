package com.crud.library_application.service;

import com.crud.library_application.domain.BookCopyStatus;
import com.crud.library_application.domain.BookLoan;
import com.crud.library_application.dto.BookLoanDto;
import com.crud.library_application.exception.BookCopyNotAvailableException;
import com.crud.library_application.exception.BookCopyNotFoundException;
import com.crud.library_application.exception.BookLoanNotFoundException;
import com.crud.library_application.exception.ReaderNotFoundException;
import com.crud.library_application.mapper.BookLoanMapper;
import com.crud.library_application.repository.BookLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookLoanDbService {

    private final BookLoanRepository bookLoanRepository;
    private final BookLoanMapper bookLoanMapper;

    public void createBookLoan(BookLoanDto bookLoanDto)
            throws BookCopyNotFoundException, ReaderNotFoundException, BookCopyNotAvailableException {
        BookLoan bookLoan = bookLoanMapper.mapToBookLoan(bookLoanDto);
        bookLoan.setBookBorrowDate(LocalDate.now());
        if(bookLoan.getBookCopy().getBookCopyStatus().equals(BookCopyStatus.AVAILABLE)) {
            bookLoan.getBookCopy().setBookCopyStatus(BookCopyStatus.BORROWED);
        } else {
            throw new BookCopyNotAvailableException();
        }
        bookLoanRepository.save(bookLoan);
    }

    public void returnBookCopy(Long loanId) throws BookLoanNotFoundException {
        BookLoan bookLoan = bookLoanRepository.findById(loanId).orElseThrow(BookLoanNotFoundException::new);
        bookLoan.setBookReturnDate(LocalDate.now());
        bookLoan.getBookCopy().setBookCopyStatus(BookCopyStatus.AVAILABLE);
        bookLoanRepository.save(bookLoan);
    }
}
