package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    private final Scanner scanner = new Scanner(System.in);

    public MainView() {}

    private final LabelView labelView = new LabelView();

    public void start() {
        System.out.print("Выберите сущность: \n1 - Writer\n2 - Post\n3 - Label\n0 - Выход\nВведите ваш выбор: ");
        int inputNumber = scanner.nextInt();

        try {
            boolean flag = true;
            while (flag) {
                switch (inputNumber) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        runMenuLabel();
                        break;
                    case 0:
                        flag = false;
                        break;
                    default:
                        System.out.println("Неверный ввод");
                        flag = false;
                        start();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода " + e);
            start();
        }
    }

    public void runMenuLabel() {
        labelView.startMenuLabel();
    }
}
