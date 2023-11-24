package ua.com.alevel;

import java.util.Scanner;

public class Main extends FileHelp {
    public void main() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("File Help МЕНЮ:");
            System.out.println("1. Список файлів та папок");
            System.out.println("2. Створити файли та папки");
            System.out.println("3. Видалити файли та папки");
            System.out.println("4. Перемістити папку в папку");
            System.out.println("5. Перемістити файл в папку");
            System.out.println("6. Шукати файл в папці");
            System.out.println("7. Шукати текст в файлі");
            System.out.println("8. Перейти до папки");
            System.out.println("9. Повернутися назад");
            System.out.println("10. Вийти з програми");
            System.out.print("Напишіть цифру: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listFilesAndFolders();
                    break;
                case 2:
                    createFileOrFolder(scanner);
                    break;
                case 3:
                    deleteFileOrFolder(scanner);
                    break;
                case 4:
                    moveFileOrFolder(scanner);
                    break;
                case 5:
                    moveFileToFolder(scanner);
                    break;
                case 6:
                    searchFileOrFolder(scanner);
                    break;
                case 7:
                    searchTextInFiles(scanner);
                    break;
                case 8:
                    navigateToFolder(scanner);
                    break;
                case 9:
                    navigateBack();
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("ERROR. СПРОБУЙТЕ ЩЕ РАЗ.");
            }
        }

        System.out.println("File Helper закрит.");
    }
}