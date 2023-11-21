package com.crud.library_application.service;

import com.crud.library_application.domain.BookCopyStatus;
import com.crud.library_application.domain.BookTitle;
import com.crud.library_application.dto.BookTitleDto;
import com.crud.library_application.exception.BookTitleAlreadyExistsException;
import com.crud.library_application.exception.BookTitleNotFoundException;
import com.crud.library_application.mapper.BookTitleMapper;
import com.crud.library_application.repository.BookTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BookTitleDbService {

    private final BookTitleRepository bookTitleRepository;
    private final BookTitleMapper bookTitleMapper;

    public void createBookTitle(BookTitleDto bookTitleDto) throws BookTitleAlreadyExistsException {
        BookTitle bookTitle = bookTitleMapper.mapToBookTitle(bookTitleDto);
        boolean isExisting = bookTitleRepository.existsByTitleAndAuthorNameAndBookPublicationYear(
                bookTitle.getTitle(), bookTitle.getAuthorName(), bookTitle.getBookPublicationYear());
        if(!isExisting) {
            bookTitleRepository.save(bookTitle);
        } else {
            throw new BookTitleAlreadyExistsException();
        }
    }

    public int searchForAvailableBookCopies(BookTitleDto bookTitleDto) throws BookTitleNotFoundException {
        BookTitle bookTitle = bookTitleMapper.mapToBookTitle(bookTitleDto);
        Optional<BookTitle> optionalBookTitle = bookTitleRepository.findByTitleAndAuthorNameAndBookPublicationYear(
                bookTitle.getTitle(), bookTitle.getAuthorName(), bookTitle.getBookPublicationYear());
        if(optionalBookTitle.isPresent()) {
            return IntStream.range(0, optionalBookTitle.get().getBookCopyList().size())
                    .filter(n -> optionalBookTitle.get().getBookCopyList().get(n).getBookCopyStatus().equals(BookCopyStatus.AVAILABLE))
                    .map(n -> 1)
                    .sum();
        } else {
            throw new BookTitleNotFoundException();
        }
    }
}
