package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;

/**
 * Created by X13 on 01.01.2017.
 */
public class PoligonSimetric implements Figure {

    private int sides ;
    private float radius;
    private Point[] pts;
    private Color color;

    public PoligonSimetric(int sides, float radius){
        this.sides = sides;
        this.radius = radius;
        float alfa = (float) 2 * (float) Math.PI / (float) sides;
        computeFigure(alfa);
    }


    public PoligonSimetric(Color color, Point ...points){
        this.pts = points;
        this.color = color;
    }


    private void computeFigure(float alfa) {
        pts = new Point[sides];
        for (int i = 0; i < sides; i++) {
            pts[i] = new Point(radius * (float) Math.sin(alfa* i), radius * (float) Math.cos(sides * i), 0);
        }
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    public void display (GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3fv(color.toArray(), 0);
        for (int i = 0; i < pts.length; i++) {
            pts[i].display(drawable);
        }
        gl.glEnd();
    }



}
