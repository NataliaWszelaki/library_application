package com.crud.library_application.controller;

import com.crud.library_application.dto.BookTitleDto;
import com.crud.library_application.exception.BookTitleAlreadyExistsException;
import com.crud.library_application.exception.BookTitleNotFoundException;
import com.crud.library_application.service.BookTitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book-titles")
@RequiredArgsConstructor
public class BookTitleController {

    private final BookTitleDbService bookTitleDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBookTitle(@RequestBody BookTitleDto bookTitleDto)
            throws BookTitleAlreadyExistsException {
        bookTitleDbService.createBookTitle(bookTitleDto);
        return new ResponseEntity<>("The Book Title has been created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<String> numberOfAvailableCopyBooksOfGivenBookTitle(@RequestBody BookTitleDto bookTitleDto)
            throws BookTitleNotFoundException {
        return new ResponseEntity<>("The number of available Book Copies of given Book Title: "
                + bookTitleDbService.searchForAvailableBookCopies(bookTitleDto), HttpStatus.OK);
    }
}
