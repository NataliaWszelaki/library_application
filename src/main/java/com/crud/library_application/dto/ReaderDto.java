package com.crud.library_application.dto;

import com.crud.library_application.domain.BookLoan;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@JsonFormat
public class ReaderDto {

    private Long id;
    private String name;
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate accountCreationDate;
    private List<BookLoan> bookLoanList = new ArrayList<>();

    public ReaderDto(String name, String surname, LocalDate accountCreationDate) {
        this.name = name;
        this.surname = surname;
        this.accountCreationDate = accountCreationDate;
    }
}
