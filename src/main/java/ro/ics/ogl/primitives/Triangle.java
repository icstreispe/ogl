package ro.ics.ogl.primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

/**
 * Created by X13 on 31.12.2016.
 */
public class Triangle extends Polygon {

    private Point p1, p2, p3;
    private Point tp1, tp2, tp3;      //texture points
    private Point np1, np2, np3;    //normalele

    public Triangle(){
    }

    public Triangle(Point p1, Point p2, Point p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.points = new Point[] {p1, p2, p3};
    }

    public Triangle(Point p1, Point p2, Point p3, Color color){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = color;
        this.points = new Point[] {p1, p2, p3};
    }

    public void setTexture (Point p1, Point p2, Point p3){
        tp1 = p1;
        tp2 = p2;
        tp3 = p3;
    }

    public void setNormals (Point p1, Point p2, Point p3){
        np1 = p1;
        np2 = p2;
        np3 = p3;
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        if (color != null) {
            gl.glColor3fv(color.toArray(), 0);
        }

        gl.glBegin(GL2.GL_TRIANGLES);
        if (tp1 != null){
            gl.glTexCoord2f(tp1.x, tp1.y);
        }
        p1.display(drawable);
        if (tp2 != null){
            gl.glTexCoord2f(tp2.x, tp2.y);
        }
        p2.display(drawable);
        if (tp3 != null){
            gl.glTexCoord2f(tp3.x, tp3.y);
        }
        p3.display(drawable);
        gl.glEnd();
    }


}
