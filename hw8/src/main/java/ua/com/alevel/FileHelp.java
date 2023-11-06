package ua.com.alevel;
import java.io.*;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class FileHelp {
    private String currentDirectory;
    private final Stack<String> directoryHistory;

    public FileHelp() {
        String os = System.getProperty("os.name");
        String userHome = System.getProperty("user.home");
        currentDirectory = os.startsWith("Mac") ? userHome : userHome + File.separator;
        directoryHistory = new Stack<>();
        directoryHistory.push(currentDirectory);
    }
    void listFilesAndFolders() {
        try {
            Files.list(Paths.get(currentDirectory))
                    .forEach(path -> System.out.println(path.getFileName()));
        } catch (IOException e) {
            System.out.println("Помилка списку файлів і папок: " + e.getMessage());
        }
    }

    void createFileOrFolder(Scanner scanner) {
        System.out.print("Введіть назву файлу/папки: ");
        String name = scanner.nextLine();

        Path path = Paths.get(currentDirectory, name);

        try {
            if (name.contains(".")) {
                Files.createFile(path);
                System.out.println("Файл створено: " + path);
            } else {
                Files.createDirectory(path);
                System.out.println("Папка створена: " + path);
            }
        } catch (IOException e) {
            System.out.println("Помилка створення файлу/папки: " + e.getMessage());
        }
    }

    void deleteFileOrFolder(Scanner scanner) {
        System.out.print("Введіть назву файлу/папки: ");
        String name = scanner.nextLine();

        Path path = Paths.get(currentDirectory, name);

        try {
            if (Files.isDirectory(path)) {
                if (Files.list(path).findFirst().isPresent()) {
                    System.out.println("ERROR. Спробуйте ще раз.");
                    System.out.print("Ви впевнені, що бажаєте його видалити? (так ні): ");
                    String confirmation = scanner.nextLine().trim().toLowerCase();
                    if (confirmation.equals("так")) {
                        deleteDirectoryRecursively(path);
                        System.out.println("Папку та її вміст видалено: " + path);
                    } else {
                        System.out.println("Видалення скасовано.");
                    }
                } else {
                    Files.deleteIfExists(path);
                    System.out.println("Папку видалено: " + path);
                }
            } else {
                Files.deleteIfExists(path);
                System.out.println("Файл видалено: " + path);
            }
        } catch (IOException e) {
            System.out.println("ERROR. Спробуйте ще раз: " + e.getMessage());
        }
    }

    private void deleteDirectoryRecursively(Path directory) throws IOException {
        Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    void moveFileOrFolder(Scanner scanner) {
        System.out.print("Введіть вихідний шлях: ");
        String sourcePath = scanner.nextLine();
        System.out.print("Введіть шлях призначення: ");
        String destinationPath = scanner.nextLine();

        Path source = Paths.get(currentDirectory, sourcePath);
        Path destination = Paths.get(currentDirectory, destinationPath);

        try {
            if (Files.isDirectory(source) && Files.isDirectory(destination)) {
                destination = destination.resolve(source.getFileName());
                Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Папку переміщено в: " + destination);
            } else;
        } catch (IOException e) {
            System.out.println("ERROR. Спробуйте ще раз: " + e.getMessage());
        }
    }
    void moveFileToFolder(Scanner scanner) {
        System.out.print("Введіть назву файлу для переміщення: ");
        String fileName = scanner.nextLine();
        System.out.print("Введіть назву папки призначення: ");
        String destinationFolderName = scanner.nextLine();

        Path source = Paths.get(currentDirectory, fileName);
        Path destination = Paths.get(currentDirectory, destinationFolderName);

        try {
            if (Files.exists(source) && Files.isDirectory(destination)) {
                destination = destination.resolve(fileName);
                Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File moved to: " + destination);
            } else {
                System.out.println("ERROR. Спробуйте ще раз.");
            }
        } catch (IOException e) {
            System.out.println("ERROR. Спробуйте ще раз: " + e.getMessage());
        }
    }

    void searchFileOrFolder(Scanner scanner) {
        System.out.print("Введіть назву файлу/папки для пошуку: ");
        String name = scanner.nextLine();

        try {
            Files.walk(Paths.get(currentDirectory))
                    .filter(path -> path.getFileName().toString().equals(name))
                    .forEach(path -> System.out.println("Знайдено в: " + path));
        } catch (IOException e) {
            System.out.println("ERROR. Спробуйте ще раз: " + e.getMessage());
        }
    }

    void searchTextInFiles(Scanner scanner) {
        System.out.print("Введіть текст для пошуку: ");
        String searchText = scanner.nextLine();

        try {
            Files.walk(Paths.get(currentDirectory))
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try (BufferedReader reader = Files.newBufferedReader(file)) {
                            String line;
                            int lineNumber = 0;
                            while ((line = reader.readLine()) != null) {
                                lineNumber++;
                                if (line.contains(searchText)) {
                                    System.out.println("Знайдено у файлі: " + file + "  " + lineNumber);
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("ERROR. Спробуйте ще раз: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.out.println("ERROR. Спробуйте ще раз: " + e.getMessage());
        }
    }

    void navigateToFolder(Scanner scanner) {
        System.out.print("Введіть назву папки для перемішеня: ");
        String folderName = scanner.nextLine();

        Path newPath = Paths.get(currentDirectory, folderName);

        if (Files.isDirectory(newPath)) {
            directoryHistory.push(currentDirectory);
            currentDirectory = newPath.toString();
            System.out.println("Перехід до папки: " + newPath);
        } else {
            System.out.println("Папка не існує.");
        }
    }

    void navigateBack() {
        if (!directoryHistory.isEmpty()) {
            String previousDirectory = directoryHistory.pop();
            currentDirectory = previousDirectory;
            System.out.println("Повернення до: " + previousDirectory);
        } else {
            System.out.println("ERROR. Спробуйте ще раз.");
        }
    }
}
