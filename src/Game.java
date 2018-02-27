/*
 * Copyright (c) 2018. Mark James Tilbrook
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;



public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 450;
    public static final int HEIGHT = 750;

    private boolean isRunning = false;
    private Thread thread;

    private Handler handler;
    private BufferedImage sprite_sheet = null;
    private SpriteSheet ss;

    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Player player;
    private Dock dock;
    private Dock dock1;
    private Dock dock2;
    private Dock dock3;
    private Dock dock4;




    private Random r;


    public enum STATE{
        Menu,
        Help,
        Game
    }

    public STATE gameState = STATE.Menu;


    //constructor
    public Game(){

        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this,handler,hud);

        //create docks
        dock = new Dock(0,620,ID.Dock,handler,ss);
        dock1 = new Dock(96,620,ID.Dock,handler,ss);
        dock2 = new Dock(192,620,ID.Dock,handler,ss);
        dock3 = new Dock(288,620,ID.Dock,handler,ss);
        dock4 = new Dock(384,620,ID.Dock,handler,ss);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        this.addMouseListener(new MouseInput(handler,hud));
        new Window(WIDTH,HEIGHT,"Catch Fish",this);
        start();

//        hud = new HUD();
        spawner = new Spawn(handler,hud,player);


        r = new Random();

        if (gameState == STATE.Game){

            //add player
            handler.addObject(new Player(WIDTH/2,635,ID.Player,handler,this,ss));

            //add first fish
            handler.addObject(new SmallFish(r.nextInt(Game.WIDTH - 60),0,ID.SmallFish,handler,ss,hud));
        }




    }




    //starts thread
    public void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    //stops thread
    public void stop(){
        isRunning = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run(){
        //game loop
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                //updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                //updates = 0;
            }
        }
        stop();
    }


    public void tick(){

        handler.tick();


        dock.tick();
        dock1.tick();
        dock2.tick();
        dock3.tick();
        dock4.tick();


        if (gameState == STATE.Game){

            hud.tick();
            spawner.tick();

        }else if (gameState == STATE.Menu){
            menu.tick();
        }

    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
//        Graphics2D g2d = (Graphics2D) g;
        /////////////////////////////////

        g.setColor(Color.cyan);
        g.fillRect(0,0,WIDTH,HEIGHT);

        if (gameState == STATE.Game){
            //render docks
            dock.render(g);
            dock1.render(g);
            dock2.render(g);
            dock3.render(g);
            dock4.render(g);

            handler.render(g);
            hud.render(g);


        }else if (gameState == STATE.Menu || gameState == STATE.Help){
            menu.render(g);
        }







        ////////////////////////////
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
