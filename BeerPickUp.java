import javafx.application.Platform;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BeerPickUp extends GameObject {

    private int width = 32;
    private int height = 32;
    private BufferedImage beer_image;

    private HUD hud;
    private BufferedImage sprite_sheet = null;
    private SpriteSheet ss;
//    private Player player;


    Handler handler;

    Player player;


    public BeerPickUp(float posX, float posY, ID id, Handler handler,SpriteSheet ss,HUD hud, Player player) {
        super(posX, posY, id);
        this.ss = ss;
        this.handler = handler;
        this.hud = hud;
        this.player = player;


        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/animales2.png");//load spritesheet

        ss = new SpriteSheet(sprite_sheet);


        beer_image = ss.grabImage(10,1,32,32);



        velY = 5;
    }

    @Override
    public void tick() {
        posY += velY;
        posX += velX;



        collision();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(beer_image,(int)posX,(int)posY,null);

//        //use this to see collision box
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.pink);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)posX,(int)posY,width,height);
    }

    public void collision(){
        //pick up beer, slows movement of fish
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player){
                if (getBounds().intersects(tempObject.getBounds())){
                    Player.drinkBeer();//sets player drunk level up
                    hud.setDrunkLevel(hud.getDrunkLevel()+1);// sets hud drunk level up(this is visual)
                    HUD.HEALTH+=10;

                    handler.removeObject(this);

                }

            }
        }
    }


}
