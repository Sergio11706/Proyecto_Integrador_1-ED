package main.service;

import java.util.Arrays;
import java.util.Collections;

import main.model.*;
import main.util.*;

public class JuegoService {
    Queue<Jugador> jugadores;
    Stack<Carta> mazo;
    Carta[] cartaMesa;
    Jugador[] jugadoresRonda;

    public JuegoService() {

    }

    // Agrega jugadores a la cola y prepara el mazo
    public void prepararJuego(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
        jugadores = new CountedQueue<Jugador>(4);
        jugadores.offer(j1);
        jugadores.offer(j2);
        jugadores.offer(j3);
        jugadores.offer(j4);
        mazo = crearYMezclarMazo();
        cartaMesa = new Carta[4];
    }

    // Juega una ronda: cada jugador saca una carta y se guarda en la mesa
    public int jugarRonda() {
        int cantidad = jugadores.size();
        jugadoresRonda = new Jugador[cantidad];

        for (int i = 0; i < cantidad; i++) {
            Jugador jugadorActual = jugadores.poll();
            Carta cartaSacada = mazo.pop();

            jugadoresRonda[i] = jugadorActual;
            cartaMesa[i] = cartaSacada;
            jugadores.offer(jugadorActual);
        }

        return repartirCartas(jugadoresRonda);
    }

    // Devuelve una cola de ganadores de la partida según el mayor puntaje    
    public Queue<Jugador> ganadorPartida() {
        if (jugadores == null || jugadores.isEmpty()) {
            return null;
        }

        int mayorPuntaje = -1;
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugadorActual = jugadores.poll();
            int puntaje = jugadorActual.calcularPuntaje();

            if (puntaje > mayorPuntaje) {
                mayorPuntaje = puntaje;
            }
            jugadores.offer(jugadorActual);
        }

        Queue<Jugador> ganadores = new CountedQueue<Jugador>(4);
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugadorActual = jugadores.poll();

            if (jugadorActual.calcularPuntaje() == mayorPuntaje) {
                ganadores.offer(jugadorActual);
            }
            jugadores.offer(jugadorActual);
        }

        return ganadores;
    }

    // Crea y mezcla una pila para simular el mazo de cartas
    public Stack<Carta> crearYMezclarMazo() {
        Carta[] cartas = generarMazo();
        Collections.shuffle(Arrays.asList(cartas));

        Stack<Carta> mazo = new Stack<Carta>(52);

        for (int i = 0; i < cartas.length; i++) {
            mazo.push(cartas[i]);
        }
        return mazo;
    }

    // Inicializa una pila y agrega 52 objetos de tipo Carta
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

    // Devuelve el mayor valor entre las cartas dadas
    public int mayorValor(Carta[] cartas) {
        int mayor = cartas[0].getValor();

        for (int i = 1; i < cartas.length; i++) {
            if (cartas[i].getValor() > mayor) {
                mayor = cartas[i].getValor();
            }
        }

        return mayor;
    }

    // Cuenta cuántas cartas tienen el valor mayor
    public int cantidadMayor(Carta[] cartas, int mayor) {
        int cantidad = 0;

        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i].getValor() == mayor) {
                cantidad++;
            }
        }

        return cantidad;
    }

    // Determina el ganador de la ronda retornando el índice del ganador o -1 si hay empate
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

    // Reparte las cartas de la mesa al ganador o a todos en caso de empate
    public int repartirCartas(Jugador[] jugadoresRonda) {
        int ganador = ganadorRonda();

        if (ganador == -1) {
            for (int i = 0; i < cartaMesa.length; i++) {
                jugadoresRonda[i].agregarCarta(cartaMesa[i]);
            }
        } else {
            for (int i = 0; i < cartaMesa.length; i++) {
                jugadoresRonda[ganador].agregarCarta(cartaMesa[i]);
            }
        }

        return ganador;
    }

    // Verifica si aún quedan cartas en el mazo
    public boolean hayCartasEnMazo() {
        return !mazo.isEmpty();
    }

    // Devuelve los jugadores de la última ronda
    public Jugador[] getJugadoresRonda() {
        return jugadoresRonda;
    }

    // Devuelve las cartas jugadas en la mesa
    public Carta[] getCartaMesa() {
        return cartaMesa;
    }

}