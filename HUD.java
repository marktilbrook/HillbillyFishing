import java.awt.*;

public class HUD {

    public static float HEALTH = 100;
    private float greenVal = 255;

    private int  smallFish = 0;
    private int  bigFish = 0;
    private int fishCaught = 0;
    private int snakesKilled = 0;
    private int drunkLevel = 0;
    private int ammo = 11;

    private int time = 0;
    private int level = 1;



    //todo add money fish

    public void tick(){
        HEALTH = Game.clamp(HEALTH,0,100);
        greenVal = Game.clamp(greenVal,0,255);
        greenVal = HEALTH*2;
        fishCaught = smallFish + bigFish;
        time++;



    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15,15,200,32);
        g.setColor(new Color(150, (int)greenVal,0));
        g.fillRect(15,15,(int)HEALTH *2,32);
        g.setColor(Color.white);
        g.drawRect(15,15,200,32);

        g.setColor(Color.RED);
        g.drawString("LEVEL: " + level,Game.WIDTH/2 + 80,30);
        g.drawString("Time: " + time ,Game.WIDTH/2 + 80,50);
        g.setColor(Color.black);
        g.drawString("Small Fish: " + smallFish,15,66);
        g.drawString("Big Fish: " + bigFish,15,86);
        g.drawString("Fish Caught: " + fishCaught ,15,106);
        g.drawString("Snakes Killed: " + snakesKilled ,15,126);
        g.drawString("Drunk level: " + drunkLevel ,15,146);

        g.drawString("AMMO: " + ammo,15, 400);

    }

    public void increaseLevel(){
        level++;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public int getSmallFish() {
        return smallFish;
    }

    public void setSmallFish(int smallFish) {
        this.smallFish = smallFish;
    }

    public int getBigFish() {
        return bigFish;
    }

    public void setBigFish(int bigFish) {
        this.bigFish = bigFish;
    }

    public int getDrunkLevel() {
        return drunkLevel;
    }

    public void setDrunkLevel(int drunkLevel) {
        this.drunkLevel = drunkLevel;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getSnakesKilled() {
        return snakesKilled;
    }

    public void setSnakesKilled(int snakesKilled) {
        this.snakesKilled = snakesKilled;
    }
}
