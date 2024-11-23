package model.logic;

import model.data_structures.*;
import utils.DistanceCalculator;

public class Modelo {
    
    private int tamano;

    public Modelo() {
        // Sin inicialización adicional
    }

    public Modelo(int tamanoInicial) {
        this.tamano = tamanoInicial;
    }

    public float calculateDistance(double lon1, double lat1, double lon2, double lat2) {
        return (float) DistanceCalculator.calculateDistance(lon1, lat1, lon2, lat2);
    }

     // Método para devolver el tamaño actual
    public int darTamano() {
        return tamano;
    }
}
