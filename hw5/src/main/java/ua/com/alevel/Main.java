package ua.com.alevel;
import java.util.*;
import static ua.com.alevel.MatList.getUserInputInt;
import static ua.com.alevel.MatList.getUserInputIntArray;


public class Main {

    public static void main(String[] args) {
        MatList<Integer> matList = new MatList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Варіанти дій:");
            System.out.println("1. Додає елемент");
            System.out.println("2. Додає n елементів");
            System.out.println("3. Об`днує з іншими MatList");
            System.out.println("4. Об`днує з іншими MatList, залишаючи тільки ті елементи, які є в усіх колекціях");
            System.out.println("5. Сортує колекцію від найбільшого");
            System.out.println("6. Сортує колекцію від найбільшого тільки ті елементи, які лежать між firstIndex та lastIndex");
            System.out.println("7. Сортує колекцію від найбільшого починаючи з value");
            System.out.println("8. Сортує колекцію від найменшого");
            System.out.println("9. Сортує в порядку зростання в діапазоні");
            System.out.println("10. Сортує в порядку зростання елемента");
            System.out.println("11. Отримати елемент за індексом");
            System.out.println("12. Отримати максимальний елемент");
            System.out.println("13. Отримати мінімальний елемент");
            System.out.println("14. Віддає середнє значення");
            System.out.println("15. Віддає медіану");
            System.out.println("16. Перетворити в масив");
            System.out.println("17. Перетворити масив у діапазоні");
            System.out.println("18. Вирізає з firstIndex по lastIndex1");
            System.out.println("19. Очистка колекций");
            System.out.println("20. Очистка номерів");
            System.out.println("0. Закрити додаток");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    System.out.println("Напишить елемент:");
                    int element = getUserInputInt(scanner);
                    matList.add(element);
                    break;
                case 2:
                    System.out.println("Введіть кілька елементів, розділених пробілами:");
                    Integer[] intNumbers = getUserInputIntArray(scanner);
                    matList.add(intNumbers);
                    break;
                case 3:
                    System.out.println("Введіть елементи для об’єднання, розділивши їх пробілами:");
                    String input3 = scanner.nextLine();
                    String[] numbers3 = input3.split(" ");
                    Integer[] intNumbers3 = new Integer[numbers3.length];
                    for (int i = 0; i < numbers3.length; i++) {
                        intNumbers3[i] = Integer.parseInt(numbers3[i]);
                    }
                    MatList<Integer> otherList = new MatList<>(intNumbers3);
                    matList.join(otherList);
                    break;
                case 4:
                    System.out.println("Введіть елементи для перетину через пробіл:");
                    String input4 = scanner.nextLine();
                    String[] numbers4 = input4.split(" ");
                    Integer[] intNumbers4 = new Integer[numbers4.length];
                    for (int i = 0; i < numbers4.length; i++) {
                        intNumbers4[i] = Integer.parseInt(numbers4[i]);
                    }
                    MatList<Integer> intersectList = new MatList<>(intNumbers4);
                    matList.intersection(intersectList);
                    break;
                case 5:
                    matList.sortDesc();
                    matList.print();
                    break;
                case 6:
                    System.out.println("Введіть перший індекс:");
                    int firstIndex = scanner.nextInt();
                    System.out.println("Введіть другий індекс:");
                    int lastIndex = scanner.nextInt();
                    matList.sortDesc(firstIndex, lastIndex);
                    matList.print();
                    break;
                case 7:
                    System.out.println("Введіть елемент, з якого потрібно почати сортування:");
                    int startValue = scanner.nextInt();
                    matList.sortDesc(startValue);
                    matList.print();
                    break;
                case 8:
                    matList.sortAsc();
                    matList.print();
                    break;
                case 9:
                    System.out.println("Введіть перший індекс");
                    int ascFirstIndex = scanner.nextInt();
                    System.out.println("Введіть другий індекс:");
                    int ascLastIndex = scanner.nextInt();
                    matList.sortAsc(ascFirstIndex, ascLastIndex);
                    matList.print();
                    break;
                case 10:
                    System.out.println("Введіть елемент, з якого потрібно почати сортування:");
                    int ascStartValue = scanner.nextInt();
                    matList.sortAsc(ascStartValue);
                    matList.print();
                    break;
                case 11:
                    System.out.println("Введіть індекс:");
                    int index = scanner.nextInt();
                    Number num = matList.get(index);
                    System.out.println("Індекс елемента " + index + ": " + num);
                    break;
                case 12:
                    Number max = matList.getMax();
                    System.out.println("Максимальний елемент: " + max);
                    break;
                case 13:
                    Number min = matList.getMin();
                    System.out.println("Mінімальний елемент: " + min);
                    break;
                case 14:
                    double average = matList.getAverage();
                    System.out.println("Середнє значення: " + average);
                    break;
                case 15:
                    double median = matList.getMedian();
                    System.out.println("Медіана: " + median);
                    break;
                case 16:
                    Number[] array = matList.toArray();
                    System.out.println("Масив: " + Arrays.toString(array));
                    break;
                case 17:
                    System.out.println("Введіть перший індекс:");
                    int arrFirstIndex = scanner.nextInt();
                    System.out.println("Введіть другий індекс:");
                    int arrLastIndex = scanner.nextInt();
                    Number[] arrayInRange = matList.toArray(arrFirstIndex, arrLastIndex);
                    System.out.println("Масив у вказаному діпазоні: " + Arrays.toString(arrayInRange));
                    break;
                case 18:
                    System.out.println("Введіть перший індекс сабліста:");
                    int sublistFirstIndex = scanner.nextInt();
                    System.out.println("Введіть другий індекс сабліста:");
                    int sublistLastIndex = scanner.nextInt();
                    MatList<Integer> cutList = matList.cut(sublistFirstIndex, sublistLastIndex);
                    cutList.print();
                    break;
                case 19:
                    matList.clear();
                    System.out.println("Колекція видалена.");
                    break;
                case 20:
                    System.out.println("Введіть числа, які потрібно очистити;");
                    String numbersToClear = scanner.nextLine();
                    String[] clearNumbers = numbersToClear.split(" ");
                    Integer[] intClearNumbers = new Integer[clearNumbers.length];
                    for (int i = 0; i < clearNumbers.length; i++) {
                        intClearNumbers[i] = Integer.parseInt(clearNumbers[i]);
                    }
                    matList.clear(intClearNumbers);
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}