import java.awt.*;

public abstract class GameObject {

    protected float posX;
    protected float posY;
    protected float velX = 0;
    protected float velY = 0;
    protected ID id;
    protected SpriteSheet ss;




    //constructor
    public GameObject(float posX, float posY, ID id) {
        this.posX = posX;
        this.posY = posY;
        this.id = id;
//        this.ss = ss;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds(); //used for collision


    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
