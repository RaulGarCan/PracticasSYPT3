package Ej3;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Producto extends Observable {
    private List<Observer> clientes = new ArrayList<>();
    private double precio;
    public double getState(){
        return precio;
    }
    public void setState(double precio){
        this.precio = precio;
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
