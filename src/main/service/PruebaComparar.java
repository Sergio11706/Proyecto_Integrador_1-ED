package main.service;

//Solo para probar el comparador, luego se elimina

public class PruebaComparar {
    public static void main(String[] args) {

        CompararCartas comparador = new CompararCartas();

        // Ejemplo de cartas de los jugadores 1, 2, 3, 4 después de mezclar y repartir
        // Prueba en donde no se repiten valores, osea, no hay empate
        int[] cartasRonda1 = { 8, 12, 5, 10 };

        System.out.println("Ronda 1:");
        int mayor = comparador.mayorValor(cartasRonda1);
        int cantidadMayor = comparador.cantidadMayor(cartasRonda1, mayor);
        int ganador = comparador.ganadorRonda(cartasRonda1);

        System.out.println("Mayor valor numerico: " + mayor);
        System.out.println("Cantidad de cartas con ese valor: " + cantidadMayor);
        if (ganador == -1) {
            System.out.println("Más de un jugador tiene la carta con mayor valor, por lo tanto, esta ronda queda en empate");
        } else {
            System.out.println("Ganador de la ronda: " + "Jugador: " + (ganador + 1));
        }


        //ronda en donde si hay empate

        int[] cartasRonda2 = { 12,8,12,5 };

        System.out.println("Ronda 2:");
        int mayor2 = comparador.mayorValor(cartasRonda2);
        int cantidadMayor2 = comparador.cantidadMayor(cartasRonda2, mayor2);
        int ganador2 = comparador.ganadorRonda(cartasRonda2);

        System.out.println("Mayor valor numerico: " + mayor2);
        System.out.println("Cantidad de cartas con ese valor: " + cantidadMayor2);
        if (ganador2 == -1) {
            System.out.println("Más de un jugador tiene la carta con mayor valor, por lo tanto, esta ronda queda en empate");
        } else {
            System.out.println("Ganador de la ronda: " + "Jugador: " + (ganador2 + 1));
        }
        
    }
}
