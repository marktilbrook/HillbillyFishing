import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;
    private SpriteSheet ss;
    private HUD hud;

    public KeyInput(Handler handler, HUD hud) {
        this.hud = hud;
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //movement
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_A) handler.setLeft(true);
                if (key == KeyEvent.VK_D) handler.setRight(true);

                //deploy net
                if (key == KeyEvent.VK_SPACE){
                    if (hud.getNets()>=1) {
                        handler.addObject(new Net(tempObject.getPosX(), tempObject.getPosY(), ID.Net, handler, hud));
                        hud.setNets(hud.getNets() - 1);
                    }
                }


            }
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_A) handler.setLeft(false);
                if (key == KeyEvent.VK_D) handler.setRight(false);
                //same here

            }
        }
    }
}
