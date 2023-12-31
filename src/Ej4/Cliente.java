package Ej4;

import java.text.DecimalFormat;

public class Cliente extends Observer {
    public Cliente(Producto producto){
        this.producto = producto;
        this.producto.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Precio del Producto actualizado a: "+new DecimalFormat("0.00").format(producto.getState())+"€");
    }
}
