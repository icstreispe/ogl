package ro.ics.ogl.c;

import com.jogamp.opengl.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by mihais on 03.01.2016.
 */
public class RotatingScene extends ControlledScene implements KeyListener {

    private float rotateT = 0.0f;

    private static final float [] green = {1.0f, 1.0f, 1.0f, 1.0f};


    public RotatingScene (){
        super();
        //canvas.addKeyListener(this);    //add key handler
    }

    public void display(GLAutoDrawable drawable) {
        super.display(drawable);


        final GL2 gl = drawable.getGL().getGL2();
/*
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
*/

        //gl.glLoadIdentity();
        //gl.glTranslatef(0.0f, 0.0f, -5.0f);


// rotate about the three axes
        //gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
        //gl.glRotatef(2*rotateT, 0.0f, 1.0f, 0.0f);
        //gl.glRotatef(3*rotateT, 0.0f, 0.0f, 1.0f);
        // increasing rotation for the next iteration
        rotateT += ANGLE;

        rotate (drawable);

    }


    private static final double ANGLE = 0.1d;

    //2P      ANGLE
    private static final double r = 4;
    private static final double R = 4;



    public void rotate(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        //gl.glLoadIdentity();

        gl.glTranslated(r * Math.sin(rotateT), 0, R * Math.cos(rotateT));
        //gl.glRotated(2*Math.PI/365, 0.0d, 1.0d, 0.0d);
    }

    public void keyTyped(KeyEvent key){
    }

    public void keyPressed(KeyEvent key){
        switch (key.getKeyCode()) {

            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

            default:
                System.out.println("key: " + key.getKeyCode());
                break;
        }
    }

    public void keyReleased(KeyEvent key){
    }


}
