import java.awt.*;
import java.awt.image.BufferedImage;

public class MoneyFish extends GameObject{

    private int width = 32;
    private int height = 32;
    private boolean isDead = false;


    private BufferedImage[] moneyfish_image = new BufferedImage[4];
    private Animation anim;
    private HUD hud;
    private BufferedImage sprite_sheet = null;
    private SpriteSheet ss;


    Handler handler;


    public MoneyFish(float posX, float posY, ID id, Handler handler,SpriteSheet ss,HUD hud) {
        super(posX, posY, id);
        this.ss = ss;
        this.handler = handler;
        this.hud = hud;

        isDead = false;

        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/animales2.png");//load spritesheet

        ss = new SpriteSheet(sprite_sheet);

        moneyfish_image[0] = ss.grabImage(7,1,32,32);
        moneyfish_image[1] = ss.grabImage(8,1,32,32);
        moneyfish_image[2] = ss.grabImage(9,1,32,32);
        moneyfish_image[3] = ss.grabImage(11,1,32,32);


        anim = new Animation(3,moneyfish_image[0],moneyfish_image[1],moneyfish_image[2]);
        velY = 5;
    }

    @Override
    public void tick() {
        posY += velY;
        posX += velX;

        collision();
        anim.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        anim.drawAnimation(g,posX,posY,0);

        if (isDead){
            g.drawImage(moneyfish_image[3],(int)posX,(int)posY,null);
        }
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //can only catch fish if its alive
            if (!isDead){
                if (tempObject.getId() == ID.Player){

                    if (getBounds().intersects(tempObject.getBounds())){
                        System.out.println("Money Fish Caught!");
                        hud.setMoneyFish(hud.getMoneyFish()+1);

                        handler.removeObject(this);
                    }


                }
            }

            //bullet collision
            if (tempObject.getId() == ID.Bullet){
                if (getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(tempObject);
                    die();


                }

            }


            if (posY > Game.HEIGHT+96){
                handler.removeObject(this);


            }else if (posX >= Game.WIDTH || posX < 0){
                handler.removeObject(this);

            }
        }
    }

    public void die(){
        if (!isDead) {
            System.out.println("Money Fish Killed!");
            //todo maybe add penalty for this(penatly will be loss of money at end of level)
            //todo add random movement
            isDead = true;
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)posX,(int)posY,width,height);
    }
}
