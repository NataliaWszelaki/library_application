package com.crud.library_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReaderAlreadyExistsException.class)
    public ResponseEntity<Object> handleReaderAlreadyExistsException (ReaderAlreadyExistsException exception) {
        return new ResponseEntity<>("The Reader with given name and surname already exists.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReaderNotFoundException.class)
    public ResponseEntity<Object> handleBookTitleNotFoundException (ReaderNotFoundException exception) {
        return new ResponseEntity<>("The Reader with given ID doesn't exists.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookTitleAlreadyExistsException.class)
    public ResponseEntity<Object> handleBookTitleAlreadyExistsException (BookTitleAlreadyExistsException exception) {
        return new ResponseEntity<>("The Book Title you are trying to add already exists.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookTitleNotFoundException.class)
    public ResponseEntity<Object> handleBookTitleNotFoundException (BookTitleNotFoundException exception) {
        return new ResponseEntity<>("The Book Title with given ID doesn't exists.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookCopyNotFoundException.class)
    public ResponseEntity<Object> handleBookCopyNotFoundException (BookCopyNotFoundException exception) {
        return new ResponseEntity<>("The Book Copy with given ID doesn't exists.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookCopyNotAvailableException.class)
    public ResponseEntity<Object> handleBookCopyNotAvailableException (BookCopyNotAvailableException exception) {
        return new ResponseEntity<>("The Book Copy with given ID is not available.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookLoanNotFoundException.class)
    public ResponseEntity<Object> handleBookLoanNotFoundException (BookLoanNotFoundException exception) {
        return new ResponseEntity<>("The Book Loan with given ID doesn't exists.", HttpStatus.NOT_FOUND);
    }
}
