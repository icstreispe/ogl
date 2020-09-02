package j2d;

import java.awt.*;
import java.util.concurrent.RecursiveAction;

/**
 * Created by X13 on 18.02.2017.
 */
public class FractalTask extends RecursiveAction {

    private static final int maxIterations = 255; // increasing this will give you a more detailed fractal
    private static final double MAX_COLOR = 255; // Change as appropriate for your display.
    //private static final double epsilon = 0.001f; // The step size across the X and Y axis
    //private static final double sThreshold = 0.2;
    private static final double sThreshold = 32;       //limita de pixeli per task

    private static final double XI = -5, YI = -5, XF = 5, YF = 5;
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 1080;


    private int minX, maxX, minY, maxY;
    private Graphics g;

    public FractalTask(int minX, int maxX, int minY, int maxY, Graphics g) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.g = g;
        System.out.println ("(" + minX + "," + maxX + "," + minY + "," + maxY  +")");
    }

    private void computeDirectly() {

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                double x = (XF-XI) * i / WIDTH  + XI;
                double y = (YF-YI) * j / HEIGHT + YI;

                int iterations = 0;
                Complex c = new Complex(x, y);
                Complex z = new Complex(0, 0);
                while (z.abs() < 3 && iterations < maxIterations) {
                    z = z.mul(z).add(c);
                    iterations++;
                }

                plot(g, i, j,  (int)(MAX_COLOR / iterations)); // depending on the number of iterations, color a pixel.
            }
        }
    }

/*
    private void computeDirectly() {
        for (double x = minX; x < maxX; x += epsilon) {
            for (double y = minY; y < maxY; y += epsilon) {
                int iterations = 0;
                Complex c = new Complex(x, y);
                Complex z = new Complex(0, 0);
                while (z.abs() < 2 && iterations < maxIterations) {
                    z = z.mul(z).add(c);
                    iterations++;
                }

                plot(g, x, y, MAX_COLOR / iterations); // depending on the number of iterations, color a pixel.
            }
        }
        }

        private void plot(Graphics g, double x, double y, int i) {
        //System.out.print(i);
        g.setColor(new Color(i, 0, 0));
        //-2, 2
        int xr = (int) (WIDTH * (x + 2) / 4);
        int yr = (int) (HEIGHT * (y + 2) / 4);
        g.drawLine(xr, yr, xr, yr);
    }
*/




    protected void compute() {

        if (maxX - minX <= sThreshold) {
            computeDirectly();
            return;
        }

        //System.out.println ("invoking 4 more");
        int middleX = (minX + maxX) / 2;
        int middleY = (minY + maxY) / 2;
        invokeAll(
                new FractalTask(minX, middleX, minY, middleY, g),
                new FractalTask(middleX+1, maxX, minY, middleY, g),

                new FractalTask(minX, middleX, middleY+1, maxY, g),
                new FractalTask(middleX+1, maxX, middleY+1, maxY, g)
        );

    }



    private void plot(Graphics g, int xr, int yr, int i) {
        synchronized (g) {  //g tr accesata secvential, altfel iese haos
            //System.out.print(i);
            g.setColor(new Color(0, i, 0));
            //-2, 2
            g.drawLine(xr, yr, xr, yr);
        }
    }
}
