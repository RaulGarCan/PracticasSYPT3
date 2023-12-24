package Ej1;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        for(int i = 0; i<5; i++) {
            try {
                Socket cliente = new Socket("localhost", 6666);
                System.out.println("Conexión establecida");
                InputStream in = cliente.getInputStream();
                BufferedReader leer = new BufferedReader(new InputStreamReader(in));
                System.out.println(leer.readLine());
                cliente.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
