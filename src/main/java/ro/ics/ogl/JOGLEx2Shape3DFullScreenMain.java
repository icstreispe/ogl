package ro.ics.ogl;

import ro.ics.ogl.c.Scene;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * Created by mihais on 03.01.2016.
 */

public class JOGLEx2Shape3DFullScreenMain extends JFrame implements KeyListener {
    private static final String WINDOW_TITLE = "3D Shapes in Full Screen Mode";
    private static int windowWidth  = 640;  // size in non-full-screen mode
    private static int windowHeight = 480;

    private Scene joglMain;
    private GraphicsDevice device;
    private boolean fullScreen = false; // full-screen or windowed mode

    // Constructor
    public JOGLEx2Shape3DFullScreenMain() {
        joglMain = new Sfera();
        this.getContentPane().add(joglMain);

        // Get the default graphic device and try full screen mode
        device = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
        if (device.isFullScreenSupported()) { // Go for full-screen mode
            this.setUndecorated(true);         // Don't show title and border
            this.setResizable(false);
            //this.setIgnoreRepaint(true);     // Ignore OS re-paint request
            device.setFullScreenWindow(this);
            fullScreen = true;
        } else {      // Windowed mode
            this.setSize(windowWidth, windowWidth);
            this.setResizable(true);
            fullScreen = false;
        }

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Use a dedicate thread to run the stop() to ensure that the
                // animator stops before program exits.
                new Thread() {
                    @Override
                    public void run() {
                        joglMain.getAnimator().stop(); // stop the animator loop
                        System.exit(0);
                    }
                }.start();
            }
        });

        // Enable keyboard input
        this.addKeyListener(this);
        this.setFocusable(true);  // To receive key event
        this.requestFocus();

        this.setTitle(WINDOW_TITLE);
        this.setVisible(true);
        joglMain.getAnimator().start(); // start the animation loop
    }

    public static void main(String[] args) {
        new JOGLEx2Shape3DFullScreenMain();
    }

    // ------ Implement methods declared in KeyListener ------
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            // F1 to toggle between full-screen and windowed modes
            case KeyEvent.VK_F1:
                if (!fullScreen) {  // Saved the current size for restoration
                    Dimension screenSize = this.getSize();
                    windowWidth  = (int)screenSize.getWidth();
                    windowHeight = (int)screenSize.getHeight();
                }
                fullScreen = !fullScreen;
                this.setVisible(false); // Hide the display
                if (this.isDisplayable())
                    this.dispose();      // For changing the decoration
                if (fullScreen) {
                    if (device.isFullScreenSupported()) {
                        this.setUndecorated(true);
                        this.setResizable(false);
                        device.setFullScreenWindow(this);
                    }
                } else {
                    this.setUndecorated(false);  // Put the title and border back
                    device.setFullScreenWindow(null); // Windowed mode
                    this.setSize(windowWidth, windowHeight);
                    this.setResizable(true);
                }
                this.setVisible(true);  // Show it
                break;

            // ESC to quit
            case KeyEvent.VK_ESCAPE:
                // Use a dedicate thread to run the stop() to ensure that the
                // animator stops before program exits.
                new Thread() {
                    @Override
                    public void run() {
                        joglMain.getAnimator().stop(); // stop the animator loop
                        System.exit(0);
                    }
                }.start();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
