package com.crud.library_application.controller;

import com.crud.library_application.dto.BookLoanDto;
import com.crud.library_application.exception.*;
import com.crud.library_application.service.BookLoanDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book-loans")
@RequiredArgsConstructor
public class BookLoanController {

    private final BookLoanDbService bookLoanDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBookLoan(@RequestBody BookLoanDto bookLoanDto)
            throws BookCopyNotFoundException, ReaderNotFoundException, BookCopyNotAvailableException {
        bookLoanDbService.createBookLoan(bookLoanDto);
        return new ResponseEntity<>("The Book Loan has been created.", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{loanId}/return")
    public ResponseEntity<String> returnBookLoan(@PathVariable Long loanId) throws BookLoanNotFoundException {
        bookLoanDbService.returnBookCopy(loanId);
        return new ResponseEntity<>("The Book Copy has been return.", HttpStatus.OK);
    }
}
