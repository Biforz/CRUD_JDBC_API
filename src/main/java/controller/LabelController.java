package controller;

import model.Label;
import repository.LabelRepository;
import repository.jdbc.JdbcLabelRepositoryImpl;

public class LabelController {
    private final LabelRepository labelRepository;

    public LabelController() {
        this.labelRepository = new JdbcLabelRepositoryImpl();
    }

    public void getAllLabels() {
        labelRepository.showAll();
    }

    public void getLabelById(Long id) {
        labelRepository.showById(id);
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
