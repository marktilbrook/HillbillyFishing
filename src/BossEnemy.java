import java.awt.*;
import java.util.Random;


public class BossEnemy extends GameObject {

    private Handler handler;
    private Random r = new Random();

    private int timer = 85;
    private int timer2 = 50;

    public BossEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;


    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,94,94);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        if (timer <= 0){
            velY = 0;
        }else{
            timer--;
        }

        if (timer <= 0 ){
            timer2 --;
        }

        if (timer2 <= 0){
            if (velX == 0) velX = 2;
            //this increases speed of boss overtime
            if(velX > 0){
                velX += 0.005f;
            }else if (velX < 0){
                velX -= 0.005f;
            }

            velX = Game.clamp(velX,-10,10);// clamps the boss speed


            int spawn = r.nextInt(12);//increasing this increases the spawn rate
            if (spawn == 0) handler.addObject(new BossBullet((int)x +48, (int)y +48, ID.BasicEnemy, handler));
        }


        //this makes the object bounce off
//        if (y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 94) velX *= -1;

        //change life to change the length of trail effect
        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.red,94,94,1,handler));
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.RED);
        g.fillRect((int)x,(int)y,94,94);

    }
}

