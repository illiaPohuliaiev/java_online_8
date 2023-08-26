package ua.com.alevel;

import java.util.*;

public class Service
            {
    public static String number1(String s)
            // благодаря этому мы решаем первую задачу
            {
        int res = 0;
        for (int i = 0; i < s.length(); i++)
            {
            if (Character.isDigit(s.charAt(i)))
            {
                res += Character.getNumericValue(s.charAt(i));
            }
            }
        return String.valueOf(res);
            }

    public static void number2(String s)
            // благодаря этому мы решаем вторую задачу
            {
        Map<Character, Integer> charCountMap = new TreeMap<>();
        for (char c : s.toCharArray())
            {
            if (Character.isLetter(c))
            {
                c = Character.toLowerCase(c);
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }
            }
        int counter = 1;
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet())
            {
            System.out.printf(counter + "." + " " + entry.getKey() + " - " + entry.getValue() + " ");
            counter++;
            }
            }
    public static void number3(Integer lesson)
            {
                // благодаря этому мы узнаем когда с кайфом можно пойти домой после уроков
        int lessonsWithShortBreaks = (lesson) / 2;
        int lessonsWithLongBreaks = (lesson - 1) / 2;
        int totalMinutes = lesson * 45 + lessonsWithShortBreaks * 5 + lessonsWithLongBreaks * 15 + 9 * 60;
        int endHour = totalMinutes / 60;
        int endMinute = totalMinutes % 60;
        System.out.println("Время окончания урока: " + endHour + " " + endMinute);
            }
            }