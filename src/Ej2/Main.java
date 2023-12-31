package Ej2;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        Thread cliente1 = new Thread(new Cliente(monitor));
        Thread cliente2 = new Thread(new Cliente(monitor));
        Thread cliente3 = new Thread(new Cliente(monitor));

        cliente1.start();
        cliente2.start();
        cliente3.start();
    }
}
