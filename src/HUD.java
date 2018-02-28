import java.awt.*;

public class HUD {

    public static float HEALTH = 100;
    private float greenVal = 255;

    private int score = 0;
    private int level = 1;


    public void tick(){

        HEALTH = Game.clamp(HEALTH,0,100);
        greenVal = Game.clamp(greenVal,0,255);
        greenVal = HEALTH*2;


        score++;


    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15,15,200,32);
        g.setColor(new Color(150, (int)greenVal,0));
        g.fillRect(15,15,(int)HEALTH *2,32);
        g.setColor(Color.white);
        g.drawRect(15,15,200,32);

        g.setColor(Color.black);
        g.drawString("SCORE: " + score,15,66);
        g.drawString("LEVEL " + level,15,86);

        //prints to screen if health == 0
        if (HEALTH == 0){
            g.setColor(Color.black);
            g.drawString("DEAD!", Game.WIDTH/2, Game.HEIGHT/2);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
