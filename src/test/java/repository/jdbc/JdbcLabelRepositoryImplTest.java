package repository.jdbc;

import model.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.LabelRepository;
import service.LabelService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class JdbcLabelRepositoryImplTest {
    private final LabelRepository labelRepository = Mockito.mock(LabelRepository.class);
    private final LabelService labelService = new LabelService(labelRepository);
    private Label label;

    @BeforeEach
    void setUp() {
        label = Label.builder()
                .id(1L)
                .name("Name")
                .build();
    }

    @Test
    void getAll() {
        List<Label> labels = new ArrayList<>();
        labels.add(label);

        when(labelService.getAllLabels()).thenReturn(labels);

        List<Label> labelActualList = labelService.getAllLabels();

        assertNotNull(labelActualList);
        assertEquals(labels, labelActualList);
    }

    @Test
    void getById() {
        when(labelService.getLabelById(1L)).thenReturn(label);

        Label labelActual = labelService.getLabelById(1L);

        assertNotNull(labelActual);
        assertEquals(labelActual.getId(), label.getId());
        assertEquals(labelActual.getName(), label.getName());
    }

    @Test
    void create() {
        when(labelService.addLabel(label)).thenReturn(label);

        Label labelActual = labelService.addLabel(label);

        assertNotNull(labelActual);
        assertEquals(labelActual.getId(), label.getId());
        assertEquals(labelActual.getName(), label.getName());
    }

    @Test
    void update() {
        when(labelService.updateLabel(1L, label)).thenReturn(label);

        Label labelActual = labelService.updateLabel(1L, label);


        assertNotNull(labelActual);
        assertEquals(label, labelService.updateLabel(1L, label));
        assertEquals(labelActual.getId(), label.getId());
        assertEquals(labelActual.getName(), label.getName());
    }

    @Test
    void deleteById() {
    }
}
