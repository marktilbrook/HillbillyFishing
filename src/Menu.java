import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Menu extends MouseAdapter{

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    private BufferedImage sprite_sheet = null;
    private SpriteSheet ss;


    public Menu(Game game, Handler handler,HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;

        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/animales2.png");//load spritesheet

        ss = new SpriteSheet(sprite_sheet);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (game.gameState == Game.STATE.Menu){

            //play button
            if (isMouseOver(mouseX,mouseY,105,Game.HEIGHT/2,225,64)){
                game.gameState = Game.STATE.Game;
                //add player
                handler.addObject(new Player(Game.WIDTH/2,635,ID.Player,handler,game,ss));

                //add first fish
                handler.addObject(new SmallFish(r.nextInt(Game.WIDTH - 60),0,ID.SmallFish,handler,ss,hud));
                handler.addObject(new SmallFish(r.nextInt(Game.WIDTH - 60),-150,ID.SmallFish,handler,ss,hud));
            }

            //help button
            if (isMouseOver(mouseX,mouseY,105,Game.HEIGHT/2+200,225,64)){
                System.exit(1);
            }


            //quit button
            if (isMouseOver(mouseX,mouseY,105,Game.HEIGHT/2+100,225,64)){
                game.gameState = Game.STATE.Help;
            }


        }

        //back button for help
        if (game.gameState == Game.STATE.Help){
            if (isMouseOver(mouseX,mouseY,25,10,90,44)){
                game.gameState = Game.STATE.Menu;
            }
        }





    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height){
        if (mouseX > x && mouseX < x + width){
            if (mouseY > y && mouseY < y + height){
                return  true;
            }else return false;
        }else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if (game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("consolas",1,30);

            g.setFont(fnt);
            g.setColor(Color.black);
            g.drawString("Catch Fish",Game.WIDTH/2-125,50);

            g.setFont(fnt2);
            g.setColor(Color.black);
            g.drawString("Play",180,415);
            g.drawRect(105,Game.HEIGHT/2,225,64);

            g.drawString("Help",180,515);
            g.drawRect(105,Game.HEIGHT/2+100,225,64);

            g.drawString("Quit",180,615);
            g.drawRect(105,Game.HEIGHT/2+200,225,64);

        }else if(game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("consolas",1,25);

            g.setFont(fnt);
            g.setColor(Color.black);
            g.drawString("Help",Game.WIDTH/2-55,50);

            g.setFont(fnt2);
            g.drawString("Back",40,40);
            g.drawRect(25,10,90,44);

            g.drawString("Use WASD to move Player",75,150);
            g.drawString("Click to shoot",75,200);
            g.drawString("Space to deploy net",75,250);
            g.drawString("1. Catch Fish",75,300);
            g.drawString("2. Avoid the Snakes",75,350);
        }

    }
}
