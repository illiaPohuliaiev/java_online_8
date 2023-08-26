package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
          {
    public static void main(String[] args) throws IOException
          {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // на 1st лепим 1w4tt!7 и получаем 12
        System.out.println("Please enter line for 1st task");
        String nums = bufferedReader.readLine();
        System.out.println(Service.number1(nums));

        // на 2st также лепим 1w4tt!7 и получаем t - 2 2. w -1
        System.out.println("Please enter line for 2nd task");
        String letters = bufferedReader.readLine();

        Service.number2(letters);
        System.out.println();

        // на 3st лепим 3 и получаем 11 35
        System.out.println("Please enter number of lessons for 3d task");
        String lesson = bufferedReader.readLine();
        Service.number3(Integer.valueOf(lesson));
         }
         }