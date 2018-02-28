/*
 * Copyright (c) 2018. Mark James Tilbrook
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class Net extends GameObject{

    private Handler handler;


    private int width = 12;
    private int height = 7;

    private BufferedImage net_image;
    private BufferedImage sprite_sheet = null;
    private SpriteSheet ss;

    private HUD hud;

    public Net(float posX, float posY, ID id, Handler handler, HUD hud) {
        super(posX, posY, id);
        this.handler = handler;
        this.hud = hud;

        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/animales2.png");//load spritesheet

        ss = new SpriteSheet(sprite_sheet);
        net_image = ss.grabImage(12,1,32,32);

        velY = -1;
    }

    @Override
    public void tick() {

        posY += velY;
        posX += velX;

        collision();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(net_image,(int)posX,(int)posY,null);

////        use this to see collision box
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.pink);
//        g2d.draw(getBounds());
    }

    public void collision(){
        //if collides with fish
            //set net velY to equal fish velY

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            // net collision with small fish
            if (tempObject.getId() == ID.SmallFish) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    System.out.println("Small Fish Caught!");
                    hud.setSmallFish(hud.getSmallFish() + 1);
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
                }

            }
            // net collision with big fish
            if (tempObject.getId() == ID.BigFish) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    System.out.println("Big Fish Caught!");
                    hud.setBigFish(hud.getBigFish() + 1);
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
                }

            }
//            // net collision with snake
//            if (tempObject.getId() == ID.Snake) {
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    HUD.HEALTH -= 25;
//                    System.out.println("DAMAGE!");
//                    handler.removeObject(this);
//                    handler.removeObject(tempObject);
//                }
//
//            }

        }


        if (posY < 0){
            System.out.println("object removed");
            handler.removeObject(this);


        }else if (posX >= Game.WIDTH || posX < 0){
            handler.removeObject(this);

        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)posX+12,(int)posY+15,width,height);
    }
}


