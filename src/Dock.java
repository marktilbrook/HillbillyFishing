import java.awt.*;
import java.awt.image.BufferedImage;

public class Dock extends GameObject{

    //todo make wooden dock
    //todo figure out how to render many docks put together




    private BufferedImage dockImage = null;

    private BufferedImage sprite_sheet = null;
    private SpriteSheet ss;


    Handler handler;


    public Dock(float posX, float posY, ID id, Handler handler, SpriteSheet ss) {
        super(posX, posY, id);
        this.handler = handler;
        this.ss = ss;


        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/animales2.png");//load spritesheet

        ss = new SpriteSheet(sprite_sheet);
        dockImage = ss.grabImage(1,5,96,96);
    }

    @Override
    public void tick() {
        posY += velY;
        posX += velX;

        collision();
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(dockImage,(int)posX,(int)posY,null);

//        use this to see collision box
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.pink);
        g2d.draw(getBounds());
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.SmallFish) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                }
            }

            if (tempObject.getId() == ID.BigFish) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                }
            }
            if (tempObject.getId() == ID.Snake) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                }
            }
            if (tempObject.getId() == ID.BeerPickUp) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                }
            }

        }

    }



    public Rectangle getBounds() {
        return new Rectangle((int)posX,(int)posY+40,96,20);
    }
}
