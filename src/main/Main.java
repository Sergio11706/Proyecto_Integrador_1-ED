package main;

import main.model.*;
import main.service.*;
import main.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n***** BIENVENIDO AL JUEGO DE CARTAS *****\n");

        //Inicilizacion de jugadores
        System.out.println("-JUGADORES-");
        Jugador jugador1 = new Jugador("Juan", "Pérez", 25);
        Jugador jugador2 = new Jugador("María", "Gómez", 30);
        Jugador jugador3 = new Jugador("Carlos", "López", 28);
        Jugador jugador4 = new Jugador("Ana", "Martínez", 22);

        System.out.println(jugador1.toString());
        System.out.println(jugador2.toString());
        System.out.println(jugador3.toString());
        System.out.println(jugador4.toString());

        //Comienzo del juego
        JuegoService juegoService = new JuegoService();

        juegoService.prepararJuego(jugador1, jugador2, jugador3, jugador4);

        System.out.println("\n------------------------------");
        //Jugar rondas hasta que se acaben las cartas en el mazo
        int nroDeRonda = 1;
        while (juegoService.hayCartasEnMazo()) {
            System.out.println("\n=== RONDA " + nroDeRonda + " ===");
            nroDeRonda++;

            int ganador = juegoService.jugarRonda();

            Jugador[] jugadoresRonda = juegoService.getJugadoresRonda();
            Carta[] cartasMesa = juegoService.getCartaMesa();

            for (int i = 0; i < jugadoresRonda.length; i++) {
                System.out.println(" - " + jugadoresRonda[i].getNombre() + " sacó la carta " + cartasMesa[i].toString());
            }

            if (ganador == -1) {
                System.out.println("\n¡Más de un jugador tiene la carta con mayor valor numerico, por lo tanto, esta ronda queda en EMPATE!");
            } else {
                System.out.println("\n¡¡" + jugadoresRonda[ganador].getNombre() + " ganó la ronda!!");
            }
        }

        System.out.println("\n------------------------------\n");
        //Mostrar las cartas que ganó cada jugador y su puntaje total
        System.out.println("*** MOMENTO DE CONTAR LAS CARTAS ***\n");
        Jugador[] jugadores = {jugador1, jugador2, jugador3, jugador4};
        for (Jugador jugador : jugadores) {
            Object[] cartas = jugador.getCartasGanadas().toArray();
            System.out.println("Cartas de " + jugador.getNombre() + ": ");
            System.out.print("[ ");
            for (int i = 0; i < cartas.length; i++) {
                Carta carta = (Carta) cartas[i];
                System.out.print(carta.getValor() + " ");
            }
            System.out.println("]");
            System.out.println("Puntaje total" + ": " + jugador.calcularPuntaje() + " pts\n");
        }

        System.out.println("\n-----------------------------------------------");
        //Determinar el ganador de la partida
        Queue<Jugador> ganadores = juegoService.ganadorPartida();
        if (ganadores.size() == 1) {
            Jugador ganador = ganadores.peek();
            System.out.println("¡¡" + ganador.getNombre() + " " + ganador.getApellido() + " GANO la partida con " + ganador.calcularPuntaje()+" puntos!!");
            System.out.println("¡¡FELICITACIONES!!");
        } else {
            int puntajeEmpate = ganadores.peek().calcularPuntaje();
            System.out.println("¡La paritda finalizó con un EMPATE!");
            System.out.print("Empataron ");
            while(!ganadores.isEmpty()) {
                Jugador jugador = ganadores.poll();
                System.out.print(jugador.getNombre() + " ");
            }
            System.out.println("con " + puntajeEmpate + " pts");
        }
        System.out.println("-----------------------------------------------\n");

        System.out.println("\n***** FIN DEL JUEGO *****\n");
    }

}