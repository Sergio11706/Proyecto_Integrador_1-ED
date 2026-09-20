package main.service;

public class CompararCartas {
    public int mayorValor(int[] cartas) {

        int mayor = cartas[0];

        for (int i = 1; i < cartas.length; i++) {
            if (cartas[i] > mayor) {
                mayor = cartas[i];
            }
        }

        return mayor;
    }

    public int cantidadMayor(int[] cartas, int mayor) {
        int cantidad = 0;

        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i] == mayor) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int ganadorRonda(int[] cartas) {

        int mayor = mayorValor(cartas);

        int cantidadMayor = cantidadMayor(cartas, mayor);

        if (cantidadMayor > 1) {
            return -1;
        }

        for (int i = 0; i < cartas.length; i++) {

            if (cartas[i] == mayor) {
                return i;
            }
        }

        return -1;
    }
}
