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

public class Fractal implements KeyListener {

    public static final int MAXY = 1080;
    private static Color[] COLORS = new Color[]{
            Color.red, Color.blue, Color.green, Color.white, Color.black,
            Color.yellow, Color.gray, Color.cyan, Color.pink, Color.lightGray,
            Color.magenta, Color.orange, Color.darkGray};

    Frame mainFrame;
    GraphicsDevice device;
    private boolean stop = false;
    BufferStrategy bufferStrategy;

    public Fractal(int numBuffers) {

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

            mainFrame.createBufferStrategy(numBuffers);
            mainFrame.addKeyListener(this);

            bufferStrategy = mainFrame.getBufferStrategy();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DisplayMode getBestDisplayMode(GraphicsDevice device) {
        DisplayMode selected = null;
        DisplayMode[] modes = device.getDisplayModes();
        for (int i = 0; i < modes.length; i++) {
            //System.out.println(modes[i].getHeight() + ":" + modes[i].getWidth() + ":" + modes[i].getBitDepth() + ":" + modes[i].getRefreshRate());
            if (selected == null
                    || selected.getRefreshRate() < modes[i].getRefreshRate()
                    || selected.getHeight() < modes[i].getHeight()
                    || selected.getBitDepth() < modes[i].getBitDepth()
                    || selected.getWidth() < modes[i].getWidth()) {

                selected = modes[i];
            }
        }
        System.out.println("choosen: " + selected.getHeight() + ":" + selected.getWidth() + ":" + selected.getBitDepth() + ":" + selected.getRefreshRate());
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
            int numBuffers = 2;
            if (args != null && args.length > 0) {
                numBuffers = Integer.parseInt(args[0]);
                if (numBuffers < 2 || numBuffers > COLORS.length) {
                    System.err.println("Must specify between 2 and "
                            + COLORS.length + " buffers");
                    System.exit(1);
                }
            }

            Fractal test = new Fractal(numBuffers);
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

                    //drawZ(bounds, g);
                    MandelBrot();

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

    private void drawZ(Rectangle bounds, Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, bounds.width, bounds.height);
        g.setColor(Color.white);


        Vector vi = new Vector();
        Vector vf = new Vector();

        vi.x = 300;
        vi.y = 300;

        vf.x = 900;
        vf.y = 900;
        vi.alfa = Math.atan((vf.y-vi.y)/(vf.x-vi.x));
        vf.alfa = 0;
        trace(g, 2, vi, vf, Color.white);
    }

    private int x, y;
    private float angle;

    private void trace(Graphics g, int level, Vector p0, Vector p1, Color c) {

        if (level == 0){
            g.setColor(c);
            g.drawLine((int)p0.x, MAXY-(int)p0.y, (int)p1.x, MAXY -(int)p1.y);
            return ;
        }

        double lx = (p1.x-p0.x)/3;
        double ly = (p1.y-p0.y)/3;
        double lz = Math.sqrt(lx*lx+ly*ly);
        Vector p2 = p0.translate(lz);//x, ly);
        Vector p3 = p2.rotate(alfa).translate(lz);
        Vector p4 = p3.rotate(-2*alfa).translate(lz);

        System.out.println("p0: " + p0.print());
        System.out.println("p2: " + p2.print());
        System.out.println("p3: " + p3.print());
        System.out.println("p4: " + p4.print());
        System.out.println("------------------------------------");

        trace(g, level-1, p0, p2, Color.white);
        trace(g, level-1, p2, p3, Color.red);
        trace(g, level-1, p3, p4, Color.blue);
        trace(g, level-1, p4, p1, Color.green);
    }

    double alfa = Math.PI/3;

    ForkJoinPool pool = new ForkJoinPool();


    public void MandelBrot() {
        Graphics g = bufferStrategy.getDrawGraphics();
        FractalTask f = new FractalTask(0, FractalTask.WIDTH, 0, FractalTask.HEIGHT, g);
        pool.invoke(f);
    }



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