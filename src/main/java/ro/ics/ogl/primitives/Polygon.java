package ro.ics.ogl.primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;

/**
 * Created by X13 on 31.12.2016.
 */
public class Polygon implements Figure {

    protected Point[] points;
    protected Point[] tPoints;  //TODO texturePoints

    protected Color color;


    public Polygon(){
    }

    public Polygon(Point ...p1){
        this.points = p1;
    }

    public Polygon(Color color, Point ...p1){
        this.color = color;
        this.points = p1;
    }


    @Override
    public void init(GLAutoDrawable drawable) {

    }

    @Override
    public void display (GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        if (color != null) {
            gl.glColor4fv(color.toArray(), 0);
        }
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0 ; i < points.length; i++) {
            if (tPoints != null && tPoints.length > i){
                gl.glTexCoord2f(tPoints[i].x, tPoints[i].y);
            }
            points[i].display(drawable);
        }
        gl.glEnd();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
