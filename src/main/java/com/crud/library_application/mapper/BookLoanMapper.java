package com.crud.library_application.mapper;

import com.crud.library_application.domain.BookLoan;
import com.crud.library_application.dto.BookLoanDto;
import com.crud.library_application.exception.BookCopyNotFoundException;
import com.crud.library_application.exception.ReaderNotFoundException;
import com.crud.library_application.repository.BookCopyRepository;
import com.crud.library_application.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookLoanMapper {

    private final BookCopyRepository bookCopyRepository;
    private final ReaderRepository readerRepository;

    public BookLoan mapToBookLoan(BookLoanDto bookLoanDto) throws BookCopyNotFoundException, ReaderNotFoundException {
        return new BookLoan(
                bookLoanDto.getId(),
                bookCopyRepository.findById(bookLoanDto.getBookCopyId()).orElseThrow(BookCopyNotFoundException::new),
                readerRepository.findById(bookLoanDto.getReaderId()).orElseThrow(ReaderNotFoundException::new),
                bookLoanDto.getBookBorrowDate(),
                bookLoanDto.getBookReturnDate()
        );
    }
}
