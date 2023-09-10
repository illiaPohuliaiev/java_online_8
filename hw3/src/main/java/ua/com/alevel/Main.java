package ua.com.alevel;

import ua.com.alevel.controller.FootballerController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FootballerController footballerController = new FootballerController();
        footballerController.start();
    }
}