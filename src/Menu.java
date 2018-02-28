

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        //play button
        if (isMouseOver(mouseX,mouseY,25,100,225,50)){
            game.gameSTATE = Game.STATE.Game;
            handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));//player
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT),
                    ID.BasicEnemy, handler));//first enemy
        }

        //help button
        if (isMouseOver(mouseX,mouseY,25,175,225,50)){
            game.gameSTATE = Game.STATE.Help;
        }
        //back button for help page
        if (isMouseOver(mouseX,mouseY,25,330,225,50)){
            game.gameSTATE = Game.STATE.Menu;
        }


        //exit button
        if (isMouseOver(mouseX,mouseY,25,250,225,50)){
            System.exit(1);
        }
    }


    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height){
        if (mouseX > x && mouseX < x+width){
            if (mouseY > y && mouseY < y+height){
                return true;
            }else{
                return false;
            }
        }else return false;
    }

    public void tick(){

    }
    public void render(Graphics g){

        if (game.gameSTATE == Game.STATE.Menu){

            Font font = new Font("Arial", 1, 50);
            Font font2 = new Font("Arial",1,30);

            g.setFont(font);
            g.setColor(Color.pink);
            g.drawString("MENU",60,70);

            g.setFont(font2);
            g.drawRect(25,100,225,50);
            g.drawString("Play",95,135);


            g.drawRect(25,175,225,50);
            g.drawString("Help",95,210);

            g.drawRect(25,250,225,50);
            g.drawString("Quit",95,285);
        }else if (game.gameSTATE == Game.STATE.Help){

            Font font = new Font("Arial", 1, 50);
            Font font2 = new Font("Arial",1,30);

            g.setFont(font);
            g.setColor(Color.pink);
            g.drawString("HELP",60,70);

            g.setFont(font2);
            g.drawString("Controls: WASD",50,150);
            g.drawString("ESC to exit game",50,200);

            g.drawRect(25,330,225,50);
            g.drawString("Back",85,365);
        }



    }
}
