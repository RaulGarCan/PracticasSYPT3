package Ej5;

public class HiloAddMensajes implements Runnable {
    private Chat chat;

    public HiloAddMensajes(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void run() {
        for(int i=1; i<=10; i++){
            chat.addMensaje("Mensaje "+i);
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
