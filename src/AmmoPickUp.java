import javafx.application.Platform;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoPickUp extends GameObject {

    private int width = 32;
    private int height = 32;
    private BufferedImage ammo_image;

    private HUD hud;
    private BufferedImage sprite_sheet = null;
    private SpriteSheet ss;


    Handler handler;

    Player player;


    public AmmoPickUp(float posX, float posY, ID id, Handler handler, SpriteSheet ss, HUD hud, Player player) {
        super(posX, posY, id);
        this.ss = ss;
        this.handler = handler;
        this.hud = hud;
        this.player = player;


        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/animales2.png");//load spritesheet

        ss = new SpriteSheet(sprite_sheet);


        ammo_image = ss.grabImage(7, 8, 32, 32);


        velY = 4;
    }

    @Override
    public void tick() {
        posY += velY;
        posX += velX;


        collision();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(ammo_image, (int) posX, (int) posY, null);

//        //use this to see collision box
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.pink);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) posX, (int) posY, width, height);
    }

    public void collision() {
        //pick up beer, slows movement of fish
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hud.setAmmo(hud.getAmmo()+3);
                    hud.setNets(hud.getNets()+1);

                    handler.removeObject(this);

                }

            }
            //crate destroyed if hit by bullet
            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {

                    handler.removeObject(this);

                }

            }
        }
    }

}

