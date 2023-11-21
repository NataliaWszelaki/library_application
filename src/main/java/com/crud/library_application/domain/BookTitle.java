package com.crud.library_application.domain;

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
@Table(name = "BOOK_TITLES")
public class BookTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR_NAME")
    private String authorName;

    @Column(name = "BOOK_PUBLICATION_YEAR")
    private Integer bookPublicationYear;

    @OneToMany(
        targetEntity = BookCopy.class,
        mappedBy = "bookTitle",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<BookCopy> bookCopyList = new ArrayList<>();

    public BookTitle(String title, String authorName, Integer bookPublicationYear) {
        this.title = title;
        this.authorName = authorName;
        this.bookPublicationYear = bookPublicationYear;
    }
}
