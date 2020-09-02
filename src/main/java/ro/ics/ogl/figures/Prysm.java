package ro.ics.ogl.figures;

import ro.ics.ogl.Figure;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;
import ro.ics.ogl.primitives.Quad;

/**
 * Created by mihais on 03.01.2016.
 */
public class Prysm implements Figure {

    private static final Point[] points = {
            new Point(-2.0f, 1.0f, 2.0f),    //0
            new Point(-1.0f, -1.0f, 1.0f),   //1
            new Point(1.0f, -1.0f, 1.0f),    //2
            new Point(2.0f, 1.0f, 2.0f),    //3
            new Point(2.0f, 1.0f, -2.0f),    //4
            new Point(1.0f, -1.0f, -1.0f),    //5
            new Point(-1.0f, -1.0f, -1.0f),    //6
            new Point(-2.0f, 1.0f, -2.0f)    //7
    };
    Quad[] q = {
            new Quad(points[0], points[1], points[2], points[3], Color.GREEN),          // Top-face
            new Quad(points[4], points[5], points[6], points[7], Color.ORANGE),         // Bottom-face
            new Quad(points[3], points[2], points[5], points[4], Color.RED),            // Front-face
            new Quad(points[7], points[6], points[1], points[0], Color.YELLOW),         // Back-face
            new Quad(points[2], points[1], points[6], points[5], Color.BLUE),           // Left-face
            new Quad(points[0], points[3], points[4], points[7], Color.MAGENTA),        // Right-face
    };

    public String getWindowTitle() {
        return "3D ro.ics.figures.Prysm";
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        q[0].display(drawable);
        q[1].display(drawable);
        q[2].display(drawable);
        q[3].display(drawable);
        q[4].display(drawable);
        q[5].display(drawable);
    }

}
