package main.service;

import java.util.Arrays;
import java.util.Collections;

import main.model.*;
import main.util.*;

public class JuegoService {
    Queue<Jugador> jugadores;
    Stack<Carta> mazo;
    Carta[] cartaMesa;

    public JuegoService() {

    }

    public void prepararJuego(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
        jugadores = new ResizableQueue<Jugador>();
        jugadores.offer(j1);
        jugadores.offer(j2);
        jugadores.offer(j3);
        jugadores.offer(j4);
        mazo = crearYMezclarMazo();
        cartaMesa = new Carta[4];
    }

    public void jugarRonda() {
        int cantidad = jugadores.size();

        Jugador[] jugadoresRonda = new Jugador[cantidad];

        for (int i = 0; i < cantidad; i++) {
            Jugador jugadorActual = jugadores.poll();
            Carta cartaSacada = mazo.pop();

            //Guardo temporalmente a los jugadores para que el indice del jugador coincida con el de las cartas
            jugadoresRonda[i] = jugadorActual;
            cartaMesa[i] = cartaSacada;
            jugadores.offer(jugadorActual);
        }

        repartirCartas(jugadoresRonda);
    }

    public Stack<Carta> crearYMezclarMazo() {
        Carta[] cartas = generarMazo();
        Collections.shuffle(Arrays.asList(cartas));

        Stack<Carta> mazo = new Stack<Carta>(52);

        for (int i = 0; i < cartas.length; i++) {
            mazo.push(cartas[i]);
        }
        return mazo;
    }

    private Carta[] generarMazo() {
        Carta[] cartas = new Carta[52];
        Palo[] palos = Palo.values();
        int indice = 0;

        for (Palo palo : palos) {
            for (int i = 1; i <= 13; i++) {
                cartas[indice] = new Carta(palo, i);
                indice++;
            }
        }
        return cartas;
    }

    public int mayorValor(Carta[] cartas) {

        int mayor = cartas[0].getValor();

        for (int i = 1; i < cartas.length; i++) {

            if (cartas[i].getValor() > mayor) {
                mayor = cartas[i].getValor();
            }
        }

        return mayor;
    }

    public int cantidadMayor(Carta[] cartas, int mayor) {

        int cantidad = 0;

        for (int i = 0; i < cartas.length; i++) {

            if (cartas[i].getValor() == mayor) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int ganadorRonda() {

        int mayor = mayorValor(cartaMesa);

        int cantidadMayor = cantidadMayor(cartaMesa, mayor);

        if (cantidadMayor > 1) {
            return -1;
        }

        for (int i = 0; i < cartaMesa.length; i++) {

            if (cartaMesa[i].getValor() == mayor) {
                return i;
            }
        }

        return -1;
    }


    //Defino para quien van las cartas segun el resultado de la ronda
    public void repartirCartas(Jugador[] jugadoresRonda) {

        int ganador = ganadorRonda();

        if (ganador == -1) {

            System.out.println("Más de un jugador tiene la carta con mayor valor numerico, por lo tanto, esta ronda queda en empate");

            for (int i = 0; i < cartaMesa.length; i++) {
                jugadoresRonda[i].agregarCarta(cartaMesa[i]);
            }

        } else {

            System.out.println("Ganó la ronda: " + jugadoresRonda[ganador].getNombre()+ " " + jugadoresRonda[ganador].getApellido());

            for (int i = 0; i < cartaMesa.length; i++) {
                jugadoresRonda[ganador].agregarCarta(cartaMesa[i]);
            }
        }

    }

}
