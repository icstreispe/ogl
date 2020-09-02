package ro.ics.ogl.c;

import com.jogamp.opengl.*;
import ro.ics.ogl.primitives.Point;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by mihais on 03.01.2016.
 */
public class ControlledScene extends Scene implements MouseWheelListener, KeyListener, MouseMotionListener, MouseListener {

    public static final double MAX_ANGLE = 89d;  //1d;
    //private float [] speed = {-1.0f, 0f, 0f};   // rotational speed
    private float[] angle = {0, 0, 0};       // rotational angleY in degree for cube
    private double angleY = 0, angleX = 0;
    private Integer mouseX, mouseY;
    //private double [] position = {0.0f, -3.0f, 0.0f};
    private double dist = 2 * 15d;
    private Point CENTER = new Point(0f, 0f, 0f);   //observed point
    private float rAngle = 0.0f;
    private float cameraAngle = (float) Math.PI / 4;


    public ControlledScene() {
        super();
        // mouse listener to detect scrollwheel events
        addMouseWheelListener(this);
        canvas.addKeyListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseListener(this);
    }


    @Override
    public void display(GLAutoDrawable drawable) {
        super.display(drawable);

        // Get the OpenGL graphics context
        GL2 gl = drawable.getGL().getGL2();

        gl.glLoadIdentity();                // reset the current model-view matrix
        //gl.glTranslatef(0.0f, 0.0f, -4.0f); // translate right and into the screen

        //gl.glRotatef(angle[0], 1.0f, 0.0f, 0.0f); // rotate about the x axis
        //gl.glRotatef(angleX, 0.0f, 0.0f, 1.0f); // rotate about the z axis

        //angleY = 90;
        double angleYrad = angleY * Math.PI / 180;
        double angleXrad = angleX * Math.PI / 180;
        //z-dist*Math.cos(angle)
        //CENTER.y+dist*Math.sin(angle)
        glu.gluLookAt( CENTER.x + dist * Math.cos(angleXrad) * Math.cos(angleYrad),
                CENTER.y + dist * Math.sin(angleYrad),
                CENTER.z - dist * Math.cos(angleYrad) * Math.sin(angleXrad), CENTER.x, CENTER.y, CENTER.z, 0, 1, 0);
        //gl.glTranslated(r*sin(angleY), r*cos(angleY), 0);   //y+r*sin(angleY),  z-r*cos(angleY));
        //gl.glRotatef((float)-angleY*180, 1.0f, 0.0f, 0.0f); // rotate about the z axis
        //gl.glRotatef((float)angleX*180, 0.0f, 0.0f, 1.0f); // rotate about the z axis
        //System.out.println ("angleY: " + angleYrad);

        //gl.glLoadIdentity();                // reset the current model-view matrix
/*
        x = r * Math.cos(rAngle);
        z = r * Math.sin(rAngle) - 1 * r;
        //gl.glTranslated(x, Math.tan(cameraAngle) * z + y,  z / Math.cos(cameraAngle));
        gl.glTranslated(x, Math.cos(cameraAngle)* y,  Math.tan(cameraAngle) *  y + z);
        //gl.glTranslated(x, y,  z);
        //gl.glRotatef(180, 0.0f, 0.0f, 0.0f);

        rAngle += 0.05f;
        System.out.println (rAngle);
        */

//tg = height / z;


        //angle[0] += speed[0];
        //angle[1] += speed[1];
        //angle[2] += speed[2];
    }

    private double rotateT = 0;




    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println(e.getWheelRotation() + ":" + e.getScrollType() + ":" + e.getScrollAmount() + ":" + e.getUnitsToScroll());
        //position[2] += e.getWheelRotation();
        //MouseWheelEvent.WHEEL_UNIT_SCROLL;
        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            dist += e.getUnitsToScroll();
        }
    }


    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {

            case KeyEvent.VK_RIGHT:
                //speed [1]++;
                break;
            case KeyEvent.VK_LEFT:
                //speed [1]--;
                break;
            case KeyEvent.VK_UP:
                //speed [0]++;
                break;
            case KeyEvent.VK_DOWN:
                //speed [0]--;
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println(mouseX + ":" + mouseY);
                System.exit(0);
                break;

            default:
                System.out.println("key pressed." + key.getKeyCode());
                break;
        }
    }

    public void keyReleased(KeyEvent key) {
    }


    @Override
    public void mouseDragged(MouseEvent e) {

        if ( SwingUtilities.isLeftMouseButton(e)) {
            System.out.print("L/");
            angleX += ((double) (mouseX - e.getX())) / 90d;
            angleY += ((double) (e.getY() - mouseY)) / 90d;// % 180;

            //limit and reset
            if (angleY >= MAX_ANGLE) {
                angleY = MAX_ANGLE; //PRECISION
                mouseY = e.getY();
            } else if (angleY <= -MAX_ANGLE) {
                angleY = -MAX_ANGLE;
                mouseY = e.getY();
            }
        } else if ( SwingUtilities.isRightMouseButton(e)) {

            System.out.print("R/");
            //TODO de regandit sa se adapteze la schimbarea semnelor (trage in jos sa insemne mereu ac lucru dpdv user)
            CENTER.x += ((double) (mouseX - e.getX())) / 1000d;
            CENTER.z += ((double) (mouseY - e.getY())) / 1000d;
        }
    }

    private double limit(double v, double l1, double l2) {
        if (v > l1) {
            return l1;
        } else if (v < l2) {
            return l2;
        }
        return v;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}