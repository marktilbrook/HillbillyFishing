import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;



public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12*9;

    private boolean running = false;
    private Thread thread;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Random r;


    public enum STATE{
        Menu,
        Help,
        Game
    };

    public STATE gameSTATE = STATE.Menu;


    //class constructor(initialize in here)
    public Game(){

        handler = new Handler();

        menu = new Menu(this, handler);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
//        this.addMouseMotionListener(new MouseInput(handler));


        new Window(WIDTH,HEIGHT,"Game!!!",this);

        hud = new HUD();
        spawner = new Spawn(handler,hud);
        r = new Random();

        if (gameSTATE == STATE.Game) {

            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));//player
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT),
                    ID.BasicEnemy, handler));//first enemy
        }


    }

    //starts thread
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //stops thread
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){

        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        //game loop
        while (running){

            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running){
                render();
                frames++;
            }
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();

        if (gameSTATE == STATE.Game){
            hud.tick();
            spawner.tick();
        }else if (gameSTATE == STATE.Menu){
            menu.tick();
        }

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        if (gameSTATE == STATE.Game) {
            hud.render(g);
        }else if (gameSTATE == STATE.Menu || gameSTATE == STATE.Help){
            menu.render(g);
        }


        g.dispose();
        bs.show();
    }


    public static float clamp(float var, float min, float max){
        if (var >= max){
            return var = max;
        }else if (var <= min){
            return var = min;
        }else{
            return var;
        }
    }









    public static void main(String[] args) {
        new Game();
    }
}
