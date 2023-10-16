package ua.com.alevel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

class MatList<E extends Number> {
    private static final int INITIAL_CAPACITY = 10;
    private E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MatList() {
        data = (E[]) new Number[INITIAL_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public MatList(E[]... numbers) {
        this();
        for (E[] arr : numbers) {
            if (arr != null) {
                ensureCapacity(size + arr.length);
                System.arraycopy(arr, 0, data, size, arr.length);
                size += arr.length;
            }
        }
    }

    public void add(E n) {
        ensureCapacity(size + 1);
        data[size++] = n;
    }

    @SafeVarargs
    public final void add(E... n) {
        ensureCapacity(size + n.length);
        System.arraycopy(n, 0, data, size, n.length);
        size += n.length;
    }

    public void join(MatList... ml) {
        for (MatList<E> list : ml) {
            for (E element : list.data) {
                add(element);
            }
        }
    }

    public void intersection(MatList... ml) {
        if (ml.length > 0) {
            for (int i = 0; i < size; i++) {
                E element = data[i];
                boolean inAllLists = true;
                for (MatList list : ml) {
                    if (!list.contains(element)) {
                        inAllLists = false;
                        break;
                    }
                }
                if (!inAllLists) {
                    remove(i);
                    i--;
                }
            }
        }
    }

    public void sortDesc() {
        Arrays.sort(data, 0, size, (a, b) -> b.intValue() - a.intValue());
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && lastIndex < size) {
            Arrays.sort(data, firstIndex, lastIndex + 1, (a, b) -> b.intValue() - a.intValue());
        }
    }

    public void sortDesc(E value) {
        int startIndex = indexOf(value);
        if (startIndex != -1) {
            sortDesc(startIndex, size - 1);
        }
    }

    public void sortAsc() {
        Arrays.sort(data, 0, size, Comparator.comparingInt(Number::intValue));
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && lastIndex < size) {
            Arrays.sort(data, firstIndex, lastIndex + 1, Comparator.comparingInt(Number::intValue));
        }
    }

    public void sortAsc(E value) {
        int startIndex = indexOf(value);
        if (startIndex != -1) {
            sortAsc(startIndex, size - 1);
        }
    }

    public E get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        }
        return null;
    }

    public Number getMax() {
        if (size > 0) {
            E max = data[0];
            for (int i = 1; i < size; i++) {
                if (data[i].doubleValue() > max.doubleValue()) {
                    max = data[i];
                }
            }
            return max;
        }
        return null;
    }

    public Number getMin() {
        if (size > 0) {
            E min = data[0];
            for (int i = 1; i < size; i++) {
                if (data[i].doubleValue() < min.doubleValue()) {
                    min = data[i];
                }
            }
            return min;
        }
        return null;
    }

    public double getAverage() {
        if (size > 0) {
            double sum = 0;
            for (int i = 0; i < size; i++) {
                sum += data[i].doubleValue();
            }
            return sum / size;
        }
        return 0;
    }

    public double getMedian() {
        if (size > 0) {
            E[] sortedData = Arrays.copyOf(data, size);
            Arrays.sort(sortedData, Comparator.comparingDouble(Number::doubleValue));

            if (size % 2 == 0) {
                int middle = size / 2;
                return (sortedData[middle - 1].doubleValue() + sortedData[middle].doubleValue()) / 2;
            } else {
                return sortedData[size / 2].doubleValue();
            }
        }
        return 0;
    }

    public E[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public E[] toArray(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && lastIndex < size) {
            return Arrays.copyOfRange(data, firstIndex, lastIndex + 1);
        }
        return null;
    }

    public MatList<E> cut(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && lastIndex < size && firstIndex <= lastIndex) {
            MatList<E> cutList = new MatList<>();
            cutList.add(Arrays.copyOfRange(data, firstIndex, lastIndex + 1));
            return cutList;
        }
        return null;
    }

    public void clear() {
        size = 0;
    }

    public void clear(E[] numbers) {
        for (E number : numbers) {
            remove(number);
        }
    }

    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    public void remove(int index) {
        if (index >= 0 && index < size) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            size--;
        }
    }

    public void remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2, minCapacity);
            data = Arrays.copyOf(data, newCapacity);
        }
    }


    public void print() {
        for (E element : data) {
            System.out.println(element);
        }
    }

    static int getUserInputInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR. TRY ONE MORE.");
                scanner.next();
            }
        }
    }

    static Integer[] getUserInputIntArray(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] numbers = input.split(" ");
                Integer[] intNumbers = new Integer[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    intNumbers[i] = Integer.parseInt(numbers[i]);
                }
                return intNumbers;
            } catch (NumberFormatException e) {
                System.out.println("ERROR. TRY ONE MORE.");
            }
        }
    }
}