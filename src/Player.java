

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class Player extends GameObject {

    private int width = 32;
    private int height = 64;
    private boolean isDead = false;
    private static int money = 0;


    private static int drunkLevel = 0;
    private static int speed = 4;

    private BufferedImage[] player_image = new BufferedImage[7];

    Animation animRight;
    Animation animLeft;

    protected Handler handler;
    protected Game game;



    public Player(float posX, float posY, ID id, Handler handler, Game game, SpriteSheet ss) {
        super(posX, posY, id);
        this.handler = handler;
        this.game = game;
        this.ss = ss;



        player_image[0] = ss.grabImage(10,3,32,64);//idle

        //right
        player_image[1] = ss.grabImage(10,5,32,64);
        player_image[2] = ss.grabImage(11,5,32,64);
        player_image[3] = ss.grabImage(12,5,32,64);

        //left
        player_image[4] = ss.grabImage(10,7,32,64);
        player_image[5] = ss.grabImage(11,7,32,64);
        player_image[6] = ss.grabImage(12,7,32,64);


        animRight = new Animation(3,player_image[1],player_image[2],player_image[3]);
        animLeft = new Animation(3,player_image[4],player_image[5],player_image[6]);

    }

    @Override
    public void tick() {

        posX +=velX;
        posY +=velY;

        if (handler.isRight()){
            velX = speed;
            animRight.runAnimation();
        }
        else if (!handler.isLeft()){
            velX = 0;
        }

        if (handler.isLeft()){
            velX = -speed;
            animLeft.runAnimation();
        }
        else if (!handler.isRight()){
            velX = 0;
        }


        collision();

    }

    @Override
    public void render(Graphics g) {
        if (velX == 0){
            g.drawImage(player_image[0],(int)posX,(int)posY,width,height,null);

        }else if (handler.isRight()){
            animRight.drawAnimation(g,posX,posY,0);
        }else if (handler.isLeft()){
            animLeft.drawAnimation(g,posX,posY,0);
        }

//        //use this to see collision box
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.pink);
//        g2d.draw(getBounds());


    }

    private void collision() {
        posX = Game.clamp(posX,0,Game.WIDTH - 48);
//        posY = Game.clamp(posY,0,Game.HEIGHT - 68);
    }



    public static void drinkBeer(){
        System.out.println("Drinking Beer!");
        drunkLevel = drunkLevel++;

        speed = speed-2;
    }




    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)posX,(int)posY,width,height);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static int getDrunkLevel() {
        return drunkLevel;
    }

    public static void setDrunkLevel(int drunkLevel) {
        Player.drunkLevel = drunkLevel;
    }


}









