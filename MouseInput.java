import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private HUD hud;


    public MouseInput(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;


    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player){
                    if (hud.getAmmo()>=1) {
                        handler.addObject(new Bullet(tempObject.getPosX(), tempObject.getPosY(), ID.Bullet, handler, mouseX, mouseY));
                        hud.setAmmo(hud.getAmmo()-1);
                    }



            }
        }
    }
}
