package repository.jdbc;

import model.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.WriterRepository;
import service.WriterService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class JdbcWriterRepositoryImplTest {
    private final WriterRepository writerRepository = Mockito.mock(WriterRepository.class);
    private final WriterService writerService = new WriterService(writerRepository);
    private Writer writer;

    @BeforeEach
    void setUp() {
        writer = Writer.builder()
                .id(1L)
                .firstName("First")
                .lastName("Last")
                .build();
    }

    @Test
    void getAll() {
        List<Writer> writers = new ArrayList<>();
        writers.add(writer);

        when(writerService.getAllWriter()).thenReturn(writers);

        List<Writer> postActualList = writerService.getAllWriter();

        assertNotNull(postActualList);
        assertEquals(writers, postActualList);
    }

    @Test
    void getById() {
        when(writerService.getWriterById(1L)).thenReturn(writer);

        Writer writerActual = writerService.getWriterById(1L);

        assertNotNull(writerActual);
        assertEquals(writer, writerActual);
        assertEquals(writerActual.getId(), writer.getId());
        assertEquals(writerActual.getFirstName(), writer.getFirstName());
        assertEquals(writerActual.getLastName(), writer.getLastName());
    }

    @Test
    void create() {
        when(writerService.addNewWriter(writer)).thenReturn(writer);

        Writer writerActual = writerService.addNewWriter(writer);

        assertNotNull(writerActual);
        assertEquals(writer, writerActual);
        assertEquals(writerActual.getId(), writer.getId());
        assertEquals(writerActual.getFirstName(), writer.getFirstName());
        assertEquals(writerActual.getLastName(), writer.getLastName());
    }

    @Test
    void update() {
        when(writerService.updateWriter(1L, writer)).thenReturn(writer);

        Writer writerActual = writerService.updateWriter(1L, writer);

        assertNotNull(writerActual);
        assertEquals(writer, writerActual);
        assertEquals(writerActual.getId(), writer.getId());
        assertEquals(writerActual.getFirstName(), writer.getFirstName());
        assertEquals(writerActual.getLastName(), writer.getLastName());
    }

    @Test
    void deleteById() {
    }
}
