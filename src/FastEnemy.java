import java.awt.*;

public class FastEnemy extends GameObject {

    private Handler handler;

    public FastEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 2;
        velY = 8;
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
        if (y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        //change life to change the length of trail effect
        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.ORANGE,16,16,0.1f,handler));
    }

    @Override
    public void render(Graphics g) {


        g.setColor(Color.ORANGE);
        g.fillRect((int)x,(int)y,16,16);
    }
}
