package ua.com.alevel.controller;

import ua.com.alevel.entity.Footballer;
import ua.com.alevel.service.FootballerCrudService;
import ua.com.alevel.service.impl.FootballerCrudServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FootballerController {

    private FootballerCrudService footballerCrudService = new FootballerCrudServiceImpl();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String position = "";
        while ((position) != null) {
            menu();
            position = bufferedReader.readLine();
            crud(position, bufferedReader);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create footballer, please enter 1");
        System.out.println("If you want find footballer, please enter 2");
        System.out.println("If you want update footballer, please enter 3");
        System.out.println("If you want delete footballer, please enter 4");
        System.out.println("If you want close app, please enter 5");
    }

    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        String id = "";
        switch (position) {
            case "1" -> create(bufferedReader);
            case "2" -> findAll();
            case "3" -> {
                System.out.println("Write id if you want to update");
                id = String.valueOf(bufferedReader.readLine());
                footballerCrudService.update(id);
            }
            case "4" -> {
                System.out.println("Write id if you want to delete");
                id = String.valueOf(bufferedReader.readLine());
                footballerCrudService.delete(id);
            }
            case "5" -> System.exit(0);

        }
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String n = reader.readLine();
        System.out.println("Please enter player number");
        String pn = reader.readLine();
        System.out.println("Please enter age");
        int age = Integer.parseInt(reader.readLine());
        Footballer footballer = new Footballer();
        if (0 <= age && age <= 150) {
            footballer.setAge(age);
        } else {
            System.out.println("");
            footballer.setAge(0);
        }
        if (!(n.equalsIgnoreCase(""))) {
            footballer.setName(n);
        } else {
            footballer.setName("");
            System.out.println("");
        }
        footballer.setPlayerNumber(pn);
        footballerCrudService.create(footballer);
    }

    private void findAll() {
        Footballer[] footballers = footballerCrudService.findAll();
        for (int i = 0; i < footballers.length; i++) {
            Footballer footballer = footballers[i];
            if (footballer != null) {
                System.out.println("Name = " + footballer.getName());
                System.out.println("Player Number = " + footballer.getPlayerNumber());
                System.out.println("Age = " + footballer.getAge());
                System.out.println();
            }
        }
    }
}