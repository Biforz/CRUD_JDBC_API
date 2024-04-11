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

    public void startMenuLabel() {
        System.out.print("Доступные действия: " +
                "\n1 - Показать всех Labels\n2 - Найти Label по id\n3 - Добавить новый Label в таблицу" +
                "\n4 - Редактировать Label\n5 - Удалить Label по id" +
                "\nВведите ваш выбор: ");
        int inputNumber = scanner.nextInt();
        scanner.nextLine();

        switch (inputNumber) {
            case 1:
                findAllLabel();
                break;
            case 2:
                findLabelById();
                break;
            case 3:
                addNewLabel();
                break;
            case 4:
                updateLabel();
                break;
            case 5:
                deleteLabelById();
                break;
            default:
                System.out.println("Неверный ввод");
        }
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
