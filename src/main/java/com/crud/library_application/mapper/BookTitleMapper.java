package com.crud.library_application.mapper;

import com.crud.library_application.domain.BookTitle;
import com.crud.library_application.dto.BookTitleDto;
import org.springframework.stereotype.Service;

@Service
public class BookTitleMapper {

    public BookTitle mapToBookTitle(final BookTitleDto bookTitleDto) {
        return new BookTitle(
                bookTitleDto.getTitle(),
                bookTitleDto.getAuthorName(),
                bookTitleDto.getBookPublicationYear()
        );
    }
}
