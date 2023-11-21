package com.crud.library_application.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookLoanDto {

    private Long id;
    private Long bookCopyId;
    private Long readerId;
    private LocalDate bookBorrowDate;
    private LocalDate bookReturnDate;
}
