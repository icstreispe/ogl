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
public class Romburi2 implements Figure {


    Point p11 = new Point (-2.0f, -1.0f, 0.0f);
    Point p12 = new Point (2.0f, -1.0f, 0.0f);
    Point p13 = new Point (2.0f, 1.0f, 0.0f);
    Point p14 = new Point (-2.0f, 1.0f, 0.0f);

    Point p15 = new Point (-2.0f, 0.0f, -1.0f);
    Point p16 = new Point (2.0f, 0.0f, -1.0f);
    Point p17 = new Point (2.0f, 0.0f, 1.0f);
    Point p18 = new Point (-2.0f, 0.0f, 1.0f);

    Quad q11 = new Quad (p11, p12, p13, p14, Color.YELLOW);
    Quad q12 = new Quad (p15, p16, p17, p18, Color.RED);

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        q11.display(drawable);
        q12.display(drawable);
    }
}
