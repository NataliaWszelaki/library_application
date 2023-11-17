package com.crud.library_application.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_TITLE_ID")
    private BookTitle bookTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "LOAN_STATUS")
    private LoanStatus loanStatus;

    @OneToMany(
            targetEntity = BookLoan.class,
            mappedBy = "bookCopy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BookLoan> bookLoanList = new ArrayList<>();

    public BookCopy(BookTitle bookTitle, LoanStatus loanStatus) {
        this.bookTitle = bookTitle;
        this.loanStatus = loanStatus;
    }
}
