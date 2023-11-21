package com.crud.library_application.service;

import com.crud.library_application.domain.Reader;
import com.crud.library_application.dto.ReaderDto;
import com.crud.library_application.exception.ReaderAlreadyExistsException;
import com.crud.library_application.mapper.ReaderMapper;
import com.crud.library_application.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderDbService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    public void createReader(final ReaderDto readerDto) throws ReaderAlreadyExistsException {
        Reader reader = readerMapper.mapToReader(readerDto);
        boolean isExisting = readerRepository.existsByNameAndSurname(reader.getName(), reader.getSurname());
        if(!isExisting) {
            readerRepository.save(reader);
        } else {
            throw new ReaderAlreadyExistsException();
        }
    }
}
