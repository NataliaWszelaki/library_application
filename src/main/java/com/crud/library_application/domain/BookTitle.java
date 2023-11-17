package com.crud.library_application.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BOOK_TITLES")
public class BookTitle {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR_NAME")
    private String authorName;

    @Column(name = "BOOK_PUBLICATION_DATE")
    private LocalDate bookPublicationDate;

    @OneToMany(
        targetEntity = BookCopy.class,
        mappedBy = "bookTitle",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<BookCopy> bookCopyList = new ArrayList<>();

    public BookTitle(String title, String authorName, LocalDate bookPublicationDate) {
        this.title = title;
        this.authorName = authorName;
        this.bookPublicationDate = bookPublicationDate;
    }
}
