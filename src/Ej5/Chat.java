package Ej5;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Chat extends Observable {
    private List<InterfazChat> interfaces;
    private List<String> mensajes;

    public Chat() {
        this.interfaces = new ArrayList<>();
        this.mensajes = new ArrayList<>();
    }

    public List<String> getMensajes() {
        return mensajes;
    }
    public synchronized int getNMensajes(){
        return mensajes.size();
    }
    public String getLastMensaje(){
        return mensajes.getLast();
    }
    public synchronized void addMensaje(String nuevoMensaje) {
        mensajes.add(nuevoMensaje);
        notifyAllObservers();
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void responderMensaje(String nuevoMensaje){
        while (getNMensajes()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        mensajes.add(nuevoMensaje);
        notifyAllObservers();
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void attach(InterfazChat interfaz){
        interfaces.add(interfaz);
    }
    public void notifyAllObservers(){
        for (InterfazChat interfaz : interfaces){
            interfaz.update();
        }
    }
}
