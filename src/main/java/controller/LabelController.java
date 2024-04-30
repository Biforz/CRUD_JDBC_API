package controller;

import model.Label;
import service.LabelService;

import java.util.List;

public class LabelController {
    private final LabelService labelService;

    public LabelController() {
        this.labelService = new LabelService();
    }

    public List<Label> getAllLabels() {
        return labelService.getAllLabels();
    }

    public Label getLabelById(Long id) {
        return labelService.getLabelById(id);
    }

    public void deleteLabelDyId(Long id) {
        labelService.deleteLabelDyId(id);
    }

    public void addLabel(Label label) {
        labelService.addLabel(label);
    }

    public void updateLabel(Long id, Label label) {
        labelService.updateLabel(id, label);
    }
}
