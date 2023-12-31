package Ej2;

public class Cliente implements Runnable {
    private Monitor monitor;
    private int nPetiicones;
    public Cliente(Monitor monitor){
        this.monitor = monitor;
        nPetiicones = 10;
    }
    @Override
    public void run() {
        for(int i = 0; i<nPetiicones; i++){
            Refrescos r = monitor.recibirRefresco();
            System.out.println(Thread.currentThread().getName()+": "+r);
            int random = 1000+(int)(Math.random()*1000);
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
