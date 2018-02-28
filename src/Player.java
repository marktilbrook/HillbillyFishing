import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Handler handler;


    public Player(float x, float y, ID id,Handler handler) {
        super(x,y,id);

        this.handler = handler;





    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x,0,Game.WIDTH - 48);
        y = Game.clamp(y,0,Game.HEIGHT - 68);

        collision();
    }

    private void collision(){
        //loops until it finds enemy
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            //if Player intersects with enemy, health is reduced
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
                    || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossEnemy)
                if (getBounds().intersects(tempObject.getBounds())){
                    System.out.println("COLLISION!");
                    HUD.HEALTH -= 2;
                }
        }
    }

    @Override
    public void render(Graphics g) {

        //use this to see collision box
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.green);
//        g2d.draw(getBounds());


        g.setColor(Color.black);
        g.fillRect((int)x,(int)y,32,32);
    }
}
