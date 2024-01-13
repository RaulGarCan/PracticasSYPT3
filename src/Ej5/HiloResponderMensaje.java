package Ej5;

public class HiloResponderMensaje implements Runnable {
    private Chat chat;

    public HiloResponderMensaje(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void run() {
        for(int i=1; i<=10; i++){
            chat.responderMensaje("Respuesta "+i);
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
