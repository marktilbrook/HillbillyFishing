import java.awt.*;

public class Bullet extends GameObject {

    private Handler handler;

    private int speed =  30;
    private int width = 5;
    private int height = 7;

    public Bullet(float posX, float posY, ID id, Handler handler,int mouseX, int mouseY) {
        super(posX, posY, id);
        this.handler = handler;

        velX = (mouseX - posX) / speed;
        velY = (mouseY - posY) / speed;


    }

    @Override
    public void tick() {
        posX += velX;
        posY += velY;

        collision();
    }

    public void collision(){
        if (posX >= Game.WIDTH || posX < 0 ){
            handler.removeObject(this);

        }else if (posY >= Game.HEIGHT || posY < 0){
            handler.removeObject(this);

        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillOval((int)posX,(int)posY,width,height);

//        //use this to see collision box
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.pink);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)posX,(int)posY,width,height);
    }


}
