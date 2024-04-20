package view;

import controller.LabelController;
import model.Label;

import java.util.List;
import java.util.Scanner;

public class LabelView {
    private final Scanner scanner;
    private final LabelController labelController;

    public LabelView() {
        this.scanner = new Scanner(System.in);
        this.labelController = new LabelController();
    }

    public void findAllLabel() {
        List<Label> labels = labelController.getAllLabels();
        for (Label label : labels) {
            System.out.println(label);
        }
    }

    public void findLabelById() {
        System.out.print("Введите id для поиска: ");
        long id = scanner.nextLong();
        System.out.println(labelController.getLabelById(id));
    }

    public void addNewLabel() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        Label label = new Label();
        label.setName(name);
        labelController.addLabel(label);
    }

    public void updateLabel() {
        System.out.print("Введите id для редактирования: ");
        long id = scanner.nextLong();
        System.out.print("Введите имя: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Label label = new Label();
        label.setName(name);
        labelController.updateLabel(id, label);
    }

    public void deleteLabelById() {
        System.out.print("Введите id для удаления: ");
        long id = scanner.nextLong();
        labelController.deleteLabelDyId(id);
    }
}
