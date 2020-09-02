package ro.ics.ogl.figures;

import ro.ics.ogl.Figure;
import com.jogamp.opengl.*;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;
import ro.ics.ogl.primitives.Triangle;

/**
 * Created by mihais on 03.01.2016.
 */
public class Pyramid implements Figure {


    private Point p2 = new  Point (0.0f, 1.0f, 0.0f   , Color.RED);
    private Point p3 = new  Point (-1.0f, -1.0f, 1.0f, Color.GREEN);
    private Point p1 = new  Point (1.0f, -1.0f, 1.0f, Color.BLUE);
    private Point p5 = new  Point (1.0f, -1.0f, -1.0f, Color.ORANGE);
    private Point p4 = new  Point (-1.0f, -1.0f, -1.0f, Color.YELLOW);

    private Triangle[] faces = {
            new Triangle(p2, p3, p1),   // Font-face triangle
            new Triangle(p2, p1, p5),   // Right-face triangle
            new Triangle(p2, p5, p4),   // Back-face triangle
            new Triangle(p2, p4, p3),   // Left-face triangle
    };


    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();

        //start draw
        gl.glBegin(GL.GL_TRIANGLES);

        faces[0].display(drawable);
        faces[1].display(drawable);
        faces[2].display(drawable);
        faces[3].display(drawable);

        gl.glEnd();

    }

}
