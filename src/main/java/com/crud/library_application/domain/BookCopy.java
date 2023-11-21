package com.crud.library_application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_TITLE_ID")
    private BookTitle bookTitle;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "COPY_BOOK_STATUS")
    private BookCopyStatus bookCopyStatus;

    @OneToMany(
            targetEntity = BookLoan.class,
            mappedBy = "bookCopy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BookLoan> bookLoanList = new ArrayList<>();

    public BookCopy(Long id, BookTitle bookTitle, BookCopyStatus bookCopyStatus) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookCopyStatus = bookCopyStatus;
    }

    public BookCopy(BookTitle bookTitle, BookCopyStatus bookCopyStatus) {
        this.bookTitle = bookTitle;
        this.bookCopyStatus = bookCopyStatus;
    }
}
