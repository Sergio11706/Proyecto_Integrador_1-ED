package main.model;
import main.util.Stack;

public class Jugador {
    private String nombre;
    private String apellido;
    private int edad;
    private Stack<Carta> cartasGanadas;
    private int cantCartasGanadas;

    public Jugador(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.cartasGanadas = new Stack<Carta>(); 
        this.cantCartasGanadas = 0;
    }
    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public String setNombre(String nombre) {
        this.nombre = nombre;
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String setApellido(String apellido) {
        this.apellido = apellido;
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public int setEdad(int edad) {
        this.edad = edad;
        return edad;
    }

    public Stack<Carta> getCartasGanadas() {
        return cartasGanadas;
    }

    public void setCartasGanadas(Stack<Carta> cartasGanadas) {
        this.cartasGanadas = cartasGanadas;
    }

    public int getCantCartasGanadas() {
        return cantCartasGanadas;
    }
    public void setCantCartasGanadas(int cantCartasGanadas) {
        this.cantCartasGanadas = cantCartasGanadas;
    }

    public void agregarCarta(Carta carta) {
        cartasGanadas.push(carta);
        cantCartasGanadas++;
    }

    public int calcularPuntaje() {
        int puntajeTotal = 0;
        Object[] cartas = cartasGanadas.toArray();

        for (Object objeto : cartas) {
            Carta carta = (Carta) objeto;
            puntajeTotal += carta.getValor();
        }

        return puntajeTotal;
    }

    public String toString() {
        return nombre + " " + apellido + " (Puntaje: " + calcularPuntaje() + ")";
    }
    
}