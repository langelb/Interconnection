package controller;

import java.util.Scanner;
import utils.MenuHandler;
import model.logic.Modelo;
import view.View;

public class Controller {
    private Modelo modelo;
    private View view;

    public Controller() {
        view = new View();
    }

    public void run() {
        MenuHandler menu = new MenuHandler(view, modelo);
        menu.displayMenu();
    }
}