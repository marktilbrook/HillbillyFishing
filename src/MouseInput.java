import java.awt.*;
import java.awt.event.*;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;

public class MouseInput implements MouseMotionListener {

    private Handler handler;
    private int mPosX;
    private int mPosY;

    public MouseInput(Handler mouseHandler) {
        this.handler = mouseHandler;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        mPosX = e.getX();
        mPosY = e.getY();
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mPosX = e.getX();
        mPosY = e.getY();

        System.out.println(mPosX + ", " + mPosY);

        for (int i = 0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player){
                tempObject.setX(mPosX);
                tempObject.setY(mPosY);
            }
        }
        e.consume();
    }
}
