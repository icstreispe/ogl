package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;
import ro.ics.ogl.primitives.Point;
import ro.ics.ogl.primitives.Triangle;

/**
 * Created by X13 on 01.01.2017.
 */
public class LiteraN implements Figure {


    Point p1 = new Point (0.f,0.f, 0.0f);
    Point p2 = new Point (0.f, 2.f, 0.0f);
    Point p3 = new Point (0.5f, 2.f, 0.0f);
    Point p4 = new Point (1.5f, 1.f, 0.0f);
    Point p5 = new Point (1.5f, 2.f, 0.0f);

    Point p6 = new Point (2.f, 2.f, 0.0f);
    Point p7 = new Point (2.0f, 0.f, 0.0f);
    Point p8 = new Point (1.5f, 0.f, 0.0f);

    Point p9 = new Point (.5f, 1.f, 0.0f);
    Point p10 = new Point (.5f, 0.0f, 0.0f);



    Triangle t1 = new Triangle(p1, p9, p10);
    Triangle t2 = new Triangle(p1, p9, p2);
    Triangle t3 = new Triangle(p2, p9, p3);
    Triangle t4 = new Triangle(p4, p3, p9);
    Triangle t5 = new Triangle(p4, p9, p8);
    Triangle t6 = new Triangle(p7, p4, p8);

    Triangle t7 = new Triangle(p7, p4, p6);
    Triangle t8 = new Triangle(p6, p4, p5);

    @Override
    public void init(GLAutoDrawable drawable) {
    }



    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        //gl.glBegin(GL2.GL_TRIANGLES);
        t1.display(drawable);
        t2.display(drawable);
        //t1.display(drawable);

        t3.display(drawable);
        t4.display(drawable);
        t5.display(drawable);
        t6.display(drawable);
        t7.display(drawable);
        t8.display(drawable);

        //gl.glEnd();

    }
}
