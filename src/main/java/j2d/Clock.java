package j2d;


/**
 * This test takes a number up to 13 as an argument (assumes 2 by
 * default) and creates a multiple buffer strategy with the number of
 * buffers given.  This application enters full-screen mode, if available,
 * and flips back and forth between each buffer (each signified by a different
 * color).
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ForkJoinPool;

public class Clock implements KeyListener {

    public static final int MAXY = 1024;
    private static Color[] COLORS = new Color[]{
            Color.red, Color.blue, Color.green, Color.white, Color.black,
            Color.yellow, Color.gray, Color.cyan, Color.pink, Color.lightGray,
            Color.magenta, Color.orange, Color.darkGray};

    Frame mainFrame;
    GraphicsDevice device;
    private boolean stop = false;
    BufferStrategy bufferStrategy;

    public Clock(int numBuffers) {

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = env.getDefaultScreenDevice();

        try {
            GraphicsConfiguration gc = device.getDefaultConfiguration();
            mainFrame = new Frame(gc);
            mainFrame.setUndecorated(true);
            mainFrame.setIgnoreRepaint(true);
            device.setFullScreenWindow(mainFrame);
            if (device.isDisplayChangeSupported()) {
                chooseBestDisplayMode(device);
            }


            mainFrame.addKeyListener(this);
            mainFrame.createBufferStrategy(numBuffers);

            bufferStrategy = mainFrame.getBufferStrategy();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DisplayMode getBestDisplayMode(GraphicsDevice device) {
        DisplayMode selected = null;
        DisplayMode[] modes = device.getDisplayModes();
        for (int i = 0; i < modes.length; i++) {
            System.out.println(modes[i].getHeight() + ":" + modes[i].getWidth() + ":" + modes[i].getBitDepth() + ":" + modes[i].getRefreshRate());
            if (selected == null || selected.getRefreshRate() < modes[i].getRefreshRate()
                    || selected.getRefreshRate() < modes[i].getRefreshRate()
                    || selected.getHeight() < modes[i].getHeight()
                    || selected.getBitDepth() < modes[i].getBitDepth()
                    || selected.getWidth() < modes[i].getWidth()) {

                selected = modes[i];
            }
        }
        System.out.println("Selected: " + selected.getHeight() + ":" + selected.getWidth() + ":" + selected.getBitDepth() + ":" + selected.getRefreshRate());
        return selected;
    }

    public static void chooseBestDisplayMode(GraphicsDevice device) {
        DisplayMode best = getBestDisplayMode(device);
        if (best != null) {
            device.setDisplayMode(best);
        }
    }

    public static void main(String[] args) {
        try {
            int numBuffers = 4;
            if (args != null && args.length > 0) {
                numBuffers = Integer.parseInt(args[0]);
                if (numBuffers < 2 || numBuffers > COLORS.length) {
                    System.err.println("Must specify between 2 and "
                            + COLORS.length + " buffers");
                    System.exit(1);
                }
            }

            Clock test = new Clock(numBuffers);
            test.paint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void paint() {
        try {
            Rectangle bounds = mainFrame.getBounds();
            System.out.println(bounds.getHeight() + ":" + bounds.getWidth());


                Graphics g = bufferStrategy.getDrawGraphics();
                if (!bufferStrategy.contentsLost()) {

                    g.setColor(Color.blue);
                    g.drawOval(440, 412, 400, 400);

                    bufferStrategy.show();
                    g.dispose();
                }

            while (!stop) {
                synchronized (this) {
                    try{
                        this.wait();

                    }catch(InterruptedException e){
                    }
                }
            } //while (System.in.read() != 0x27);
        } finally {
            device.setFullScreenWindow(null);
        }
    }



    double alfa = Math.PI/3;



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            stop = true;
            synchronized (this){
                this.notify();
            }
        }
    }
}