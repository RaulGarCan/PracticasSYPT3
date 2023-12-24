package Ej2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Expendedora implements Serializable {
    private ArrayList<Refrescos> refrescos;
    private ServerSocket server;
    private Socket conexion;
    public Expendedora(){
        int random = (int)(Math.random()*20);
        rellenarMaquina(random);
    }
    public static void main(String[] args) {
        Expendedora expendedora = new Expendedora();
        expendedora.iniciar(5555);
        while (true){
            try {
                expendedora.conexion = expendedora.server.accept();

                int cantidad = Integer.parseInt(expendedora.leerMensaje());

                ArrayList<Refrescos> refrescosCliente = new ArrayList<>();
                for(int i = 0; i<cantidad; i++){
                    if(!expendedora.refrescos.isEmpty()){
                        expendedora.cogerRefresco();
                    }
                }
                expendedora.enviarObjeto(refrescosCliente);
                if(expendedora.refrescos.isEmpty()){
                    expendedora.cerrar();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    public void rellenarMaquina(int stock){
        refrescos = new ArrayList<>();
        for(int i = 0; i<stock; i++){
            int random = (int)(Math.random()*Refrescos.values().length);
            refrescos.add(Refrescos.values()[random]);
        }
    }
    public Refrescos cogerRefresco(){
        int posicion = (int)(Math.random()*refrescos.size());
        Refrescos r = refrescos.get(posicion);
        refrescos.remove(posicion);
        return r;
    }
    public void iniciar(int port){
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void cerrar(){
        System.out.println("Stock agotado, cerrando conexiones...");
        try {
            conexion.close();
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void escribirMensaje(String mensaje){
        OutputStream out;
        try {
            out = conexion.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter escribir = new BufferedWriter(new OutputStreamWriter(out));
        try {
            escribir.write(mensaje);
            escribir.flush();
            escribir.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String leerMensaje(){
        InputStream in;
        try {
            in = conexion.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader leer = new BufferedReader(new InputStreamReader(in));
        try {
            return leer.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void enviarObjeto(Serializable o){
        OutputStream out;
        try {
            out = conexion.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObjectOutputStream escribir;
        try {
            escribir = new ObjectOutputStream(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            escribir.writeObject(o);
            escribir.flush();
            escribir.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
