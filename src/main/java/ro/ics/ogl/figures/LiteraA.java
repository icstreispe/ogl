package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;
import ro.ics.ogl.Figure;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;
import ro.ics.ogl.primitives.Quad;

import java.awt.*;

/**
 * Created by X13 on 01.01.2017.
 */
public class LiteraA implements Figure {


    Point p1 = new Point (-1.f,0.f, 0.0f);
    Point p2 = new Point (-0.5f, 2.f, 0.0f);
    Point p3 = new Point (0.5f, 2.f, 0.0f);
    Point p4 = new Point (1f, 0.f, 0.0f);
    Point p5 = new Point (.5f, 0.f, 0.0f);

    Point p6 = new Point (.3f, .8f, 0.0f);
    Point p7 = new Point (-0.3f, .8f, 0.0f);

    Point p8 = new Point (-.5f, 0.f, 0.0f);

    Point p9 = new Point (-.25f, 1.1f, 0.0f);
    Point p10 = new Point (0.25f, 1.1f, 0.0f);

    Point p11 = new Point (0.125f, 1.75f, 0.0f);
    Point p12 = new Point (-0.125f, 1.75f, 0.0f);


    //GLuint indices[] = {0,2,1,
    // 2,1,3,
    // 1,3,7,
    // 4,3,7,
    // 4,7,5,
    // 7,6,5};

    Quad q1 = new Quad(p1, p8, p7, p9, Color.BLUE);
    Quad q2 = new Quad(p5, p6, p10, p4, Color.BLUE);
    Quad q3 = new Quad(p6, p10, p9, p7, Color.BLUE);

    Quad q4 = new Quad(p1, p9, p12, p2, Color.BLUE);
    Quad q5 = new Quad(p4, p10, p11, p3, Color.BLUE);

    Quad q6 = new Quad(p2, p3, p11, p12, Color.BLUE);


    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        //gl.glBegin(GL2.GL_TRIANGLES);
        q1.display(drawable);
        q2.display(drawable);
        //t1.display(drawable);

        q3.display(drawable);
        q4.display(drawable);
        q5.display(drawable);
        q6.display(drawable);

        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 12));
        textRenderer.beginRendering(900, 700);
        textRenderer.setColor(java.awt.Color.YELLOW);
        textRenderer.setSmoothing(true);

        //DPoint pt = new DPoint(200, 200);
        textRenderer.draw("Hello world!!", 200, 200);
        textRenderer.endRendering();


        //gl.glEnd();

    }
}
