package com.crud.library_application.dto;

import com.crud.library_application.domain.BookCopy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookTitleDto {

    private Long id;
    private String title;
    private String authorName;
    private Integer bookPublicationYear;
    private List<BookCopy> bookCopyList = new ArrayList<>();
}
