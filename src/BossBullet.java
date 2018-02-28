import java.awt.*;
import java.util.Random;

public class BossBullet extends GameObject {

    private Handler handler;
    private Random r = new Random();

    public BossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = r.nextInt(5 - -5) + (-5);
        velY = 5;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        //this makes the object bounce off
//        if (y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        if (y >= Game.HEIGHT){
            handler.removeObject(this);
        }


        //change life to change the length of trail effect
        //this spawns bullets
        handler.addObject(new Trail((int)x ,(int)y ,ID.Trail,Color.BLUE,16,16,0.09f,handler));
    }

    @Override
    public void render(Graphics g) {


        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,16,16);
    }
}

