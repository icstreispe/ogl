package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;
import ro.ics.ogl.primitives.Quad;

/**
 * Created by X13 on 01.01.2017.
 */
public class Romburi3 implements Figure {


    Point p1 = new Point(-2.0f, 0.0f, 0.0f);
    Point p2 = new Point(0.0f, 1.0f, 0.0f);
    Point p3 = new Point(2.0f, 0.0f, 0.0f);
    Point p4 = new Point(0.0f, -1.0f, 0.0f);

    Point p5 = new Point(2.0f, 0.0f, 0.0f);
    Point p6 = new Point(4.0f, 0.0f, 1.0f);
    Point p7 = new Point(6.0f, 0.0f, 0.0f);
    Point p8 = new Point(4.0f, 0.0f, -1.0f);

    Point p9 = new Point(0.0f, 0.0f, 1.0f);
    Point p10 = new Point(0.0f, 0.0f, -1.0f);

    Point p11 = new Point(-6.0f, 0.0f, 0.0f);
    Point p12 = new Point(-4.0f, 0.0f, 1.0f);
    Point p13 = new Point(-2.0f, 0.0f, 0.0f);
    Point p14 = new Point(-4.0f, 0.0f, -1.0f);


    Quad q1 = new Quad(p1, p2, p3, p4, Color.YELLOW);
    Quad q2 = new Quad(p5, p6, p7, p8, Color.RED);
    Quad q3 = new Quad(p2, p9, p4, p10, Color.DARK_GREEN);
    Quad q4 = new Quad(p11, p12, p13, p14, Color.BLUE);


    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        q1.display(drawable);
        q2.display(drawable);
        q3.display(drawable);
        q4.display(drawable);
    }
}