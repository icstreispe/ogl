package ro.ics.ogl; /**
 * Created by mihais on 05.01.2016.
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAnimatorControl;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import ro.ics.ogl.primitives.Point;
import ro.ics.ogl.primitives.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * JOGL 2.0 Program Template (GLCanvas) in Full Screen Mode with ESC to quit
 * This is a "Component" which can be added into a top-level "Container".
 * It also handles the OpenGL events to render graphics.
 */
@SuppressWarnings("serial")
public class JOGL2FullScreen extends GLCanvas
        implements GLEventListener, KeyListener {
    // Define constants for top-level container
    private static final int FPS = 60; // animator's target frames per second

    /** The entry main() method to setup the top-level container and animator */
    public static void main(String[] args) {
        // Run the GUI codes in the event-dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create the OpenGL rendering canvas
                GLCanvas canvas = new JOGL2FullScreen();

                // Create a animator that drives canvas' display() at the specified FPS.
                FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

                // Create the top-level container frame
                JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
                frame.getContentPane().add(canvas);
                frame.setUndecorated(true);     // no decoration such as title bar
                frame.setExtendedState(Frame.MAXIMIZED_BOTH);  // full screen mode
                frame.setVisible(true);
                animator.start(); // start the animation loop
            }
        });
    }

    // Setup OpenGL Graphics Renderer

    private GLU glu;  // for the GL Utility

    /** Constructor to setup the GUI for this Component */
    public JOGL2FullScreen() {
        this.addGLEventListener(this);   // for handling GLEvents
        this.addKeyListener(this); // for Handling KeyEvents
        this.setFocusable(true);
        this.requestFocus();
    }

    // ------ Implement methods declared in GLEventListener ------

    /**
     * Called back immediately after the OpenGL context is initialized. Can be used
     * to perform one-time initialization. Run only once.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
        glu = new GLU();                         // get GL Utilities
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
        gl.glClearDepth(1.0f);      // set clear depth value to farthest
        gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
        gl.glDepthFunc(GL2.GL_LEQUAL);  // the type of depth test to do
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
        gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting

        // ----- Your OpenGL initialization code here -----
    }

    /**
     * Call-back handler for window re-size event. Also called when the drawable is
     * first set to visible.
     */
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

        if (height == 0) height = 1;   // prevent divide by zero
        float aspect = (float)width / height;

        // Set the view port (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
        gl.glLoadIdentity();             // reset projection matrix
        glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

        // Enable the model-view transform
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); // reset
    }



    Point p1 = new Point (0.0f, 1.0f, 0.0f);
    Point p2 = new Point (-1.0f, -1.0f, 0.0f);
    Point p3 = new Point (1.0f, -1.0f, 0.0f);

    Triangle t = new Triangle(p1, p2, p3);

    /**
     * Called back by the animator to perform rendering.
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix

        // ----- Your OpenGL rendering code here (render a white triangle for testing) -----
        gl.glTranslatef(0.0f, 0.0f, -6.0f); // translate into the screen

        t.display(drawable);
    }

    /**
     * Called back before the OpenGL context is destroyed. Release resource such as buffers.
     */
    @Override
    public void dispose(GLAutoDrawable drawable) { }

    // ------ Implement methods declared in KeyListener ------

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE: // quit
                // Use a dedicate thread to run the stop() to ensure that the
                // animator stops before program exits.
                new Thread() {
                    @Override
                    public void run() {
                        GLAnimatorControl animator = getAnimator();
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                    }
                }.start();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}