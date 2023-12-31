package Ej4;

public class ModificarProducto implements Runnable {
    private Producto producto;
    public ModificarProducto(Producto producto){
        this.producto = producto;
    }
    @Override
    public void run() {
        for(int i=0; i<5; i++){
            double random1 = 1+(int)(Math.random()*10);
            double random2 = 1+(int)(Math.random()*4);
            producto.aumentarPrecio(random1/random2);
            int random3 = 1000+(int)(Math.random()*3000);
            try {
                Thread.sleep(random3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
