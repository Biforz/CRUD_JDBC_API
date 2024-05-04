package service;

import lombok.RequiredArgsConstructor;
import model.Label;
import repository.LabelRepository;
import repository.jdbc.JdbcLabelRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService() {
        this.labelRepository = new JdbcLabelRepositoryImpl();
    }

    public List<Label> getAllLabels() {
        return labelRepository.showAll();
    }

    public Label getLabelById(Long id) {
        return labelRepository.showById(id);
    }

    public void deleteLabelDyId(Long id) {
        labelRepository.deleteById(id);
    }

    public Label addLabel(Label label) {
        return labelRepository.add(label);
    }

    public Label updateLabel(Long id, Label label) {
        return labelRepository.update(id, label);
    }
}
