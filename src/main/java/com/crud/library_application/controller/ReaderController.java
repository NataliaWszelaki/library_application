package com.crud.library_application.controller;

import com.crud.library_application.dto.ReaderDto;
import com.crud.library_application.exception.ReaderAlreadyExistsException;
import com.crud.library_application.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderDbService readerDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createReader(@RequestBody ReaderDto readerDto) throws ReaderAlreadyExistsException {
        readerDbService.createReader(readerDto);
        return new ResponseEntity<>("The Reader has been added.", HttpStatus.CREATED);
    }
}
