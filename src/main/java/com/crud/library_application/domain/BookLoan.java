package com.crud.library_application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOK_LOANS")
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @Column(name = "BOOK_BORROW_DATE")
    private LocalDate bookBorrowDate;

    @Column(name = "BOOK_RETURN_DATE")
    private LocalDate bookReturnDate;

    public BookLoan(BookCopy bookCopy, Reader reader, LocalDate bookBorrowDate, LocalDate bookReturnDate) {
        this.bookCopy = bookCopy;
        this.reader = reader;
        this.bookBorrowDate = bookBorrowDate;
        this.bookReturnDate = bookReturnDate;
    }
}
