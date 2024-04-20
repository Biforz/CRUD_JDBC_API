package view;

import controller.WriterController;
import model.Writer;

import java.util.Scanner;

public class WriterView {
    private final Scanner scanner;
    private final WriterController writerController;

    public WriterView() {
        this.scanner = new Scanner(System.in);
        this.writerController = new WriterController();
    }

    public void findAllWriter() {
        for (Writer writer : writerController.getAllWriter()) {
            System.out.println(writer);
        }
    }

    public void findWriterById() {
        System.out.print("Введите id для поиска: ");
        long inputNumber = scanner.nextLong();
        System.out.println(writerController.getWriterById(inputNumber));
    }

    public void addWriter() {
        Writer writer = new Writer();
        System.out.print("Введите firstName: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите lastName: ");
        String lastName = scanner.nextLine();
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writerController.addNewWriter(writer);
    }

    public void updateWriter() {
        Writer writer = new Writer();
        System.out.println("Введите id для редактирования: ");
        long inputNumber = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Введите firstName: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите lastName: ");
        String lastName = scanner.nextLine();
        writer.setId(inputNumber);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writerController.updateWriter(inputNumber, writer);
    }

    public void deleteWriter() {
        System.out.print("Введите id для удаления: ");
        long inputNumber = scanner.nextLong();
        writerController.deleteWriterById(inputNumber);
    }
}
