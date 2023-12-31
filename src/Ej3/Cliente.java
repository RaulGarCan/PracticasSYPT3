package Ej3;

public class Cliente extends Observer {
    public Cliente(Producto producto){
        this.producto = producto;
        this.producto.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Precio del Producto actualizado a: "+producto.getState());
    }
}
