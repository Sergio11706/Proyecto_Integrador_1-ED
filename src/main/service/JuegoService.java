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
    
        for (int i = 0; i < cantidad; i++) {
            Jugador jugadorActual = jugadores.poll();
            Carta cartaSacada = mazo.pop();
        
            cartaMesa[i] = cartaSacada;
            jugadores.offer(jugadorActual);
        }
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
}
