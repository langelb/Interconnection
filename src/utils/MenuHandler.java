package utils;

import model.logic.Modelo;
import view.View;

import java.util.Scanner;

public class MenuHandler {
    private View view;
    private Modelo modelo;

    public MenuHandler(View view, Modelo modelo) {
        this.view = view;
        this.modelo = modelo;
    }

    public void displayMenu() {
        Scanner lector = new Scanner(System.in).useDelimiter("\\n");
        boolean fin = false;
        while (!fin) {
            view.printMenu();
            int option = lector.nextInt();
            switch (option) {
                case 1 -> {
                    view.printMessage("Loading data...");
                    modelo = new Modelo(1);
                    view.printModelo(modelo);
                }
                case 7 -> {
                    view.printMessage("Goodbye!");
                    lector.close();
                    fin = true;
                }
                default -> view.printMessage("Invalid option!");
            }
        }
    }
}
