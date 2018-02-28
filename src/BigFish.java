import java.awt.*;
import java.awt.image.BufferedImage;

public class BigFish extends GameObject{

    private int width = 28;
    private int height = 64;
    private boolean isDead = false;

    private BufferedImage[] bigfish_image = new BufferedImage[4];
    private Animation anim;
    private HUD hud;
    private BufferedImage sprite_sheet = null;
    private SpriteSheet ss;


    Handler handler;




    public BigFish(float posX, float posY, ID id, Handler handler,SpriteSheet ss, HUD hud) {
        super(posX, posY, id);
        this.handler = handler;
        this.hud = hud;
        this.ss = ss;

        isDead = false;
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/animales2.png");//load spritesheet

        ss = new SpriteSheet(sprite_sheet);
        bigfish_image[0] = ss.grabImage(4,1,32,64);
        bigfish_image[1] = ss.grabImage(5,1,32,64);
        bigfish_image[2] = ss.grabImage(6,1,32,64);
        bigfish_image[3] = ss.grabImage(7,6,32,64);

        anim = new Animation(3,bigfish_image[0],bigfish_image[1],bigfish_image[2]);
        velY = 2;
    }

    @Override
    public void tick() {
        posY += velY;
        posX += velX;

//        velY = 5;//change this to change speed

        collision();
        anim.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        anim.drawAnimation(g,posX,posY,0);

        if (isDead){
            g.drawImage(bigfish_image[3],(int)posX,(int)posY,null);
        }

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
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //can only catch fish if its alive
            if (!isDead){
                if (tempObject.getId() == ID.Player){

                    if (getBounds().intersects(tempObject.getBounds())){
                        System.out.println("Big Fish Caught!");

                        hud.setBigFish(hud.getBigFish()+1);

                        handler.removeObject(this);
                    }

                }
            }

            //bullet collision
            if (tempObject.getId() == ID.Bullet){
                if (getBounds().intersects(tempObject.getBounds())){
                    die();
                    handler.removeObject(tempObject);

                }

            }

            if (posY > Game.HEIGHT){
                handler.removeObject(this);


            }else if (posX >= Game.WIDTH || posX < 0){
                handler.removeObject(this);

            }
        }
    }

    public void die() {
        if (!isDead) {
            System.out.println("Big Fish Killed!");
            //todo maybe add penalty for this
            //todo add random movement after death
            isDead = true;
        }

    }
}


