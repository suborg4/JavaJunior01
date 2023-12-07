package javajunior01.task01;

/**
 * Напишите программу,
 * которая использует Stream API для обработки списка чисел.
 * Программа должна вывести на экран среднее значение всех 
 * четных чисел в списке.
 */
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        numbers.add(10);

        double average = numbers.stream()
                .filter(n -> n % 2 == 0) // фильтруем  четные числа
                .mapToInt(Integer::intValue) // преобразуем в int
                .average() // вычисляем среднее 
                .orElse(0); // если пусто, вернем 0

        System.out.println("Среднее значение четных чисел: " + average);
    }
}
