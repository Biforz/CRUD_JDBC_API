package service;

import model.Label;
import repository.LabelRepository;
import repository.jdbc.JdbcLabelRepositoryImpl;

import java.util.List;

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

    public void addLabel(Label label) {
        labelRepository.add(label);
    }

    public void updateLabel(Long id, Label label) {
        labelRepository.update(id, label);
    }
}
