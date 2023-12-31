package Ej4;

public class Main {
    public static void main(String[] args) {
        Producto producto = new Producto(2.25);

        Cliente cliente = new Cliente(producto);

        System.out.println("Precio inicial del Producto: "+producto.getState()+"€");

        Thread hilo1 = new Thread(new ModificarProducto(producto));
        Thread hilo2 = new Thread(new ModificarProducto(producto));
        Thread hilo3 = new Thread(new ModificarProducto(producto));

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
