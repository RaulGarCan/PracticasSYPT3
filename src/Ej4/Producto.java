package Ej4;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Producto extends Observable {
    private List<Observer> clientes;
    private double precio;
    public Producto(double precio){
        clientes = new ArrayList<>();
        this.precio = precio;
    }
    public double getState(){
        return precio;
    }
    public void setState(double precio){
        this.precio = precio;
        notifyAllObservers();
    }
    public synchronized void aumentarPrecio(double extra){
        this.precio += extra;
        notifyAllObservers();
    }

    public void attach(Observer cliente){
        clientes.add(cliente);
    }

    public void notifyAllObservers(){
        for (Observer cliente : clientes) {
            cliente.update();
        }
    }
}
