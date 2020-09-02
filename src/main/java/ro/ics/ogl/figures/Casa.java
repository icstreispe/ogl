package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;
import ro.ics.ogl.primitives.Quad;
import ro.ics.ogl.primitives.Triangle;

/**
 * Created by X13 on 01.01.2017.
 */
public class Casa implements Figure {


    Point p1 = new Point (2.0f, 0.0f, 0.0f);    //sus
    Point p2 = new Point (-2.0f, 0.0f, 0.0f);   //sus
    Point p0 = new Point (0.0f, 2.0f, 0.0f);    //varf

    Point p4 = new Point (-2.0f, -2.0f, 0.0f);  //jos
    Point p5 = new Point (2.0f, -2.0f, 0.0f);   //jos

    Point a1 = new Point (2.0f, 0.0f, 2.0f);
    Point a2 = new Point (-2.0f, 0.0f, 2.0f);
    Point a0 = new Point (0.0f, 2.0f, 2.0f);

    Point a4 = new Point (-2.0f, -2.0f, 2.0f);
    Point a5 = new Point (2.0f, -2.0f, 2.0f);


    Triangle t1 = new Triangle (p1, p2, p0, Color.YELLOW);
    Triangle t2 = new Triangle (a1, a2, a0, Color.YELLOW);
    //Triangle t3 = new Triangle (p3, p14, p15, Color.WHITE);

    Quad q1 = new Quad (p1, p2, p4, p5, Color.BLUE);
    Quad q2 = new Quad (p2, p0, a0, a2, Color.DARK_GREEN);
    Quad q3 = new Quad (p1, p0, a0, a1, Color.DARK_GREEN);

    Quad q4 = new Quad (p4, p5, a5, a4, Color.MAROON);
    Quad q5 = new Quad (a1, a2, a4, a5, Color.BLUE);

    Quad q6 = new Quad (a1, a5, p5, p1, Color.RED);
    Quad q7 = new Quad (a2, a4, p4, p2, Color.RED);

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        t1.display(drawable);
        t2.display(drawable);
        //t1.display(drawable);

        q1.display(drawable);
        q2.display(drawable);
        q3.display(drawable);
        q4.display(drawable);
        q5.display(drawable);
        q6.display(drawable);
        q7.display(drawable);


        //t3.display(drawable);

    }
}
