package main.model;

public class Carta {
    private String palo; 
    private int valor;
    private boolean disponible;

    public Carta(String palo, int valor) {
         if (valor < 1 || valor > 13) {
            throw new IllegalArgumentException(
                "El valor debe estar entre 1 y 13"
            );
        }
        this.palo = palo;
        this.valor = valor;
        this.disponible = true; 
    }

    public String getPalo() {
        return palo;
    }

    public String setPalo(String palo) {
        this.palo = palo;
        return palo;
    }

    public int getValor() {
        return valor;
    }

    public int setValor(int valor) {
         if (valor < 1 || valor > 13) {
            throw new IllegalArgumentException(
                "El valor debe estar entre 1 y 13"
            );}
        else {
            this.valor = valor;
            return valor;   
        }
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String toString() {
            return "Carta:" + valor + " de " + palo;
    }
}
