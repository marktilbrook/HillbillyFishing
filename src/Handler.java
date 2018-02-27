import java.util.LinkedList;
import java.awt.*;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    private boolean up = false, down = false, right = false, left = false;

    public void tick(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    //add object to list
    public void addObject(GameObject tempObject){
        object.add(tempObject);
    }

    //remove object to list
    public void removeObject(GameObject tempObject){
        object.remove(tempObject);
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
