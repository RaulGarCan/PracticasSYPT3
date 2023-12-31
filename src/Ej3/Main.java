package Ej3;

public class Main {
    public static void main(String[] args) {
        Producto producto = new Producto();

        Cliente cliente = new Cliente(producto);

        producto.setState(3.75);
    }
}
