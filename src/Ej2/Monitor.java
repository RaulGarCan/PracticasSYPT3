package Ej2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Monitor {
    private Socket cliente;
    public Monitor(){
        abrirCliente();
    }
    private void abrirCliente(){
        try {
            cliente = new Socket("localhost",5555);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void cerrarCliente(){
        try {
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized Refrescos recibirRefresco(){
        if(!MaquinaExpendedora.estaEncendido){ // Nunca detecta el boolean como false
            return null;
        }
        abrirCliente();
        Refrescos r;
        try {
            InputStream in = cliente.getInputStream();
            ObjectInputStream leer = new ObjectInputStream(in);
            try {
                r = (Refrescos) leer.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cerrarCliente();
        return r;
    }
}
