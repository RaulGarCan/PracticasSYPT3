package Ej1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int contador = 0;
        ServerSocket server;
        try {
            server = new ServerSocket(6666);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                Socket conexion = server.accept();
                System.out.println("Conexión establecida");
                contador++;
                OutputStream out = conexion.getOutputStream();
                BufferedWriter escribir = new BufferedWriter(new OutputStreamWriter(out));
                escribir.write("Cliente nº "+contador);
                escribir.flush();
                escribir.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
