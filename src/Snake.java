import java.awt.*;
import java.awt.image.BufferedImage;

public class Snake extends GameObject{

    private int width = 32;
    private int height = 32;
    private boolean isDead = false;
    private HUD hud;
    private BufferedImage sprite_sheet = null;
    private SpriteSheet ss;

    private BufferedImage[] badfish_image = new BufferedImage[4];
    Animation anim;


    Handler handler;



    public Snake(float posX, float posY, ID id, Handler handler, SpriteSheet ss, HUD hud) {
        super(posX, posY, id);
        this.ss = ss;
        this.handler = handler;
        this.hud = hud;

        isDead = false;


        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/animales2.png");//load spritesheet

        ss = new SpriteSheet(sprite_sheet);
        badfish_image[0] = ss.grabImage(4,5,32,32);
        badfish_image[1] = ss.grabImage(5,5,32,32);
        badfish_image[2] = ss.grabImage(6,5,32,32);
        badfish_image[3] = ss.grabImage(11,1,32,32);
        anim = new Animation(3,badfish_image[0],badfish_image[1],badfish_image[2]);
        velY = 4;
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
            g.drawImage(badfish_image[3],(int)posX,(int)posY,null);
        }


//        //use this to see collision box
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.pink);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)posX,(int)posY,width,height-10);
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (!isDead) {
                if (tempObject.getId() == ID.Player) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        HUD.HEALTH -= 25;
                        System.out.println("DAMAGE!");


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
            System.out.println("Snake Killed!");
            //todo maybe add reward
            //todo add random movement after death
            isDead = true;
            hud.setSnakesKilled(hud.getSnakesKilled() + 1);
        }
    }
}
