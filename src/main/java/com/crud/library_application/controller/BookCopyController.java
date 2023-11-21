package com.crud.library_application.controller;

import com.crud.library_application.dto.BookCopyDto;
import com.crud.library_application.exception.BookCopyNotFoundException;
import com.crud.library_application.exception.BookTitleNotFoundException;
import com.crud.library_application.service.BookCopyDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book-copies")
@RequiredArgsConstructor
public class BookCopyController {

    private final BookCopyDbService bookCopyDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBookCopy(@RequestBody BookCopyDto bookCopyDto) throws BookTitleNotFoundException {
        bookCopyDbService.createBookCopy(bookCopyDto);
        return new ResponseEntity<>("The Book Copy has been created", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookCopyDto> updateBookCopyStatus(@RequestBody BookCopyDto bookCopyDto)
            throws BookCopyNotFoundException, BookTitleNotFoundException {
        return ResponseEntity.ok(bookCopyDbService.updateBookCopyStatus(bookCopyDto));
    }
}
