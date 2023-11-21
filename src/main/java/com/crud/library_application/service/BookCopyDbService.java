package com.crud.library_application.service;

import com.crud.library_application.domain.BookCopy;
import com.crud.library_application.dto.BookCopyDto;
import com.crud.library_application.exception.BookCopyNotFoundException;
import com.crud.library_application.exception.BookTitleNotFoundException;
import com.crud.library_application.mapper.BookCopyMapper;
import com.crud.library_application.repository.BookCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCopyDbService {

    private final BookCopyMapper bookCopyMapper;
    private final BookCopyRepository bookCopyRepository;

    public void createBookCopy(BookCopyDto bookCopyDto) throws BookTitleNotFoundException {
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        bookCopyRepository.save(bookCopy);
     }

    public BookCopyDto updateBookCopyStatus(BookCopyDto bookCopyDto)
            throws BookCopyNotFoundException, BookTitleNotFoundException {
        bookCopyDto.setBookTitleId(bookCopyRepository.findById(bookCopyDto.getId())
                .orElseThrow(BookCopyNotFoundException::new).getBookTitle().getId());
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        BookCopy updatedBookCopy = bookCopyRepository.save(bookCopy);
        return bookCopyMapper.mapToBookCopyDto(updatedBookCopy);
    }
}
