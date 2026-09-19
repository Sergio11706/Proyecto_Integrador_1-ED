package main.service;

public class PruebaComparar {
    public static void main(String[] args) {
        
        CompararCartas comparador = new CompararCartas();

        // Ejemplo de cartas de los jugadores 1, 2, 3, 4 después de mezclar y repartir
        // Prueba en donde no se repiten valores, osea, no hay empate
        int[] cartasRonda1 = {8,12,5,10};

        int mayor = comparador.mayorValor(cartasRonda1);
        int cantidadMayor = comparador.cantidadMayor(cartasRonda1, mayor);

        System.out.println("Ronda 1:");
        System.out.println("Carta de mayor valor numerico: " + mayor);
        System.out.println("Cantidad de cartas con ese valor: " + cantidadMayor);
    }
}
