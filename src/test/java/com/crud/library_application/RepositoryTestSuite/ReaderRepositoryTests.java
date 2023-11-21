package com.crud.library_application.RepositoryTestSuite;

import com.crud.library_application.domain.Reader;
import com.crud.library_application.repository.ReaderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReaderRepositoryTests {

    @Autowired
    private ReaderRepository readerRepository;

    Reader reader;

    @BeforeEach
    void setUp() {
        reader = new Reader("John", "Smith", LocalDate.of(2021, 11, 12));
        readerRepository.save(reader);
    }

    @Test
    void testFindAllBy() {

        //When
        List<Reader> readerList = readerRepository.findAll();

        //Then
        assertNotNull(readerList);
        assertEquals(1, readerList.size());

        //CleanUp
        Long id = readerList.get(0).getId();
        readerRepository.deleteById(id);
    }

    @Test
    void testFindById() {

        //When
        Reader foundReader = readerRepository.findById(reader.getId()).orElse(null);
        assertNotNull(foundReader);

        //Then
        assertEquals(reader.getId(), foundReader.getId());
        assertEquals(reader.getName(), foundReader.getName());
        assertEquals(reader.getAccountCreationDate(), foundReader.getAccountCreationDate());

        //CleanUp
        Long id = foundReader.getId();
        readerRepository.deleteById(id);
    }

    @Test
    void testCreate() {

        //Then
        Long id = reader.getId();
        Optional<Reader> readerOptional = readerRepository.findById(id);
        assertTrue(readerOptional.isPresent());

        //CleanUp
        Long id2 = readerOptional.get().getId();
        readerRepository.deleteById(id2);
    }

    @Test
    void testUpdate() {

        //When
        reader.setName("Joanna");
        readerRepository.save(reader);
        Reader updatedReader = readerRepository.findById(reader.getId()).orElse(null);
        assertNotNull(updatedReader);

        //Then
        assertEquals("Joanna", updatedReader.getName());

        //CleanUp
        Long id = updatedReader.getId();
        readerRepository.deleteById(id);
    }

    @Test
    void testDelete() {

        //When
        Long id = reader.getId();
        readerRepository.deleteById(id);

        //Then
        Reader reader1 = readerRepository.findById(id).orElse(null);
        assertNull(reader1);
    }

    @Test
    void testExistsByNameAndSurname() {

        //When
        boolean isExisting = readerRepository.existsByNameAndSurname(reader.getName(), reader.getSurname());

        //Then
        assertTrue(isExisting);

        //CleanUp
        Long id = reader.getId();
        readerRepository.deleteById(id);
    }
}
