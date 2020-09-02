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
public class Romburi1 implements Figure {


    private Point p1 = new Point (-2.0f, 0.0f, 0.0f);
    private Point p2 = new Point (0.0f, 1.0f, 0.0f);
    private Point p3 = new Point (2.0f, 0.0f, 0.0f);
    private Point p4 = new Point (0.0f, -1.0f, 0.0f);
    private Point p5 = new Point (0.0f, 0.0f, 1.0f);
    private Point p6 = new Point (0.0f, 0.0f, -1.0f);

    private Quad q1 = new  Quad (p1, p2, p3, p4, Color.RED);
    private Quad q2 = new  Quad (p1, p5, p3, p6, Color.GREEN);
    private Quad q3 = new  Quad (p2, p5, p4, p6, Color.BLUE);

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();

        q1.display(drawable);
        q2.display(drawable);
        q3.display(drawable);
    }

}
