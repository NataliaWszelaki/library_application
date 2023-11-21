package com.crud.library_application.mapper;

import com.crud.library_application.domain.BookCopy;
import com.crud.library_application.dto.BookCopyDto;
import com.crud.library_application.exception.BookTitleNotFoundException;
import com.crud.library_application.repository.BookTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCopyMapper {

    private final BookTitleRepository bookTitleRepository;

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) throws BookTitleNotFoundException {
        return new BookCopy(
                bookCopyDto.getId(),
                bookTitleRepository.findById(bookCopyDto.getBookTitleId()).orElseThrow(BookTitleNotFoundException::new),
                bookCopyDto.getBookCopyStatus()
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getBookTitle().getId(),
                bookCopy.getBookCopyStatus()
        );
    }
}
