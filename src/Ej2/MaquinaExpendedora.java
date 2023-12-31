package Ej2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MaquinaExpendedora {
    private ArrayList<Refrescos> refrescos;
    private ServerSocket server;
    private Socket conexion;
    public static boolean estaEncendido = true;
    public MaquinaExpendedora(){
        refrescos = new ArrayList<>();
        int random = 10+(int)(Math.random()*20);
        rellenarMaquina(random);
        try {
            server = new ServerSocket(5555);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void rellenarMaquina(int stock){
        for(int i = 0; i<stock; i++){
            int random = (int)(Math.random()* Refrescos.values().length);
            refrescos.add(Refrescos.values()[random]);
        }
    }
    public synchronized void peticion(){
        try {
            conexion = server.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Refrescos r = cogerRefresco();
        enviarRefresco(r);
    }
    private Refrescos cogerRefresco(){
        int posicion = (int)(Math.random()*refrescos.size());
        Refrescos r = refrescos.get(posicion);
        refrescos.remove(posicion);
        System.out.println("Refrescos Restantes: "+refrescos);
        return r;
    }
    public void enviarRefresco(Refrescos r){
        try {
            OutputStream out = conexion.getOutputStream();
            ObjectOutputStream escribir = new ObjectOutputStream(out);
            escribir.writeObject(r);
            escribir.flush();
            escribir.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void apagarServer(){
        System.out.println("Apagando servidor...");
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MaquinaExpendedora maquinaExpendedora = new MaquinaExpendedora();
        System.out.println("Encendido: "+estaEncendido);
        while (!maquinaExpendedora.refrescos.isEmpty()){
            maquinaExpendedora.peticion();
        }
        estaEncendido = false;
        System.out.println("Encendido: "+estaEncendido);
        System.out.println("No queda stock disponible");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        maquinaExpendedora.apagarServer();
    }
}
