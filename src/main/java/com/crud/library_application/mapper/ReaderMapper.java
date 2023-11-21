package com.crud.library_application.mapper;

import com.crud.library_application.domain.Reader;
import com.crud.library_application.dto.ReaderDto;
import org.springframework.stereotype.Service;

@Service
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getName(),
                readerDto.getSurname(),
                readerDto.getAccountCreationDate()
        );
    }
}
