package com.crud.library_application.dto;

import com.crud.library_application.domain.BookLoan;
import com.crud.library_application.domain.BookCopyStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookCopyDto {

    private Long id;
    private Long bookTitleId;
    private BookCopyStatus bookCopyStatus;
    private List<BookLoan> bookLoanList = new ArrayList<>();

    public BookCopyDto(Long id, Long bookTitleId, BookCopyStatus bookCopyStatus) {
        this.id = id;
        this.bookTitleId = bookTitleId;
        this.bookCopyStatus = bookCopyStatus;
    }
}
