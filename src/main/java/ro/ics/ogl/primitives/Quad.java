package ro.ics.ogl.primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

/**
 * Created by X13 on 31.12.2016.
 */
public class Quad extends Polygon{

    public Point p1, p2, p3, p4;
    public Point tp1, tp2, tp3, tp4;


    public Quad(){
    }

    public Quad(Point p1, Point p2, Point p3, Point p4){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.points = new Point[] {p1, p2, p3, p4};
    }

    public Quad(Point p1, Point p2, Point p3, Point p4, Color color){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.points = new Point[] {p1, p2, p3, p4};
        this.color = color;
    }

    public void setTexture (Point p1, Point p2, Point p3, Point p4){
        tp1 = p1;
        tp2 = p2;
        tp3 = p3;
        tp4 = p4;
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        if (color != null) {
            gl.glColor4fv(color.toArray(), 0);
        }
        gl.glBegin(GL2.GL_QUADS);
        if (tp2 != null){
            gl.glTexCoord2f(tp2.x, tp2.y);
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
        if (tp4 != null){
            gl.glTexCoord2f(tp4.x, tp4.y);
        }
        p4.display(drawable);
        gl.glEnd();

        showSign(p1, p2, p3);
    }

    private void showSign(Point p1, Point p2, Point p3) {
        float n = (p3.y-p2.y)*(p1.y -p2.y) + (p3.z-p2.z) * (p1.z-p2.z) + (p3.x-p2.x)*(p1.x-p2.x);
        System.out.println ("normal: " + p1 + p2 + p3 +  ":" + n);

        //a · b = ax × bx + ay × by
        //a - p3-p2
        //b= p1-p2
        //a=a1i+a2j+a3ka=a1i+a2j+a3k
        // //and b=b1i+b2j+b3kb=b1i+b2j+b3k.
        //a2b3−a3b2)i−(a1b3−a3b1)j+(a1b2−a2b1)k.
        //a×b=det[ijka1a2a3b1b2b3]=(a2b3−a3b2)i−(a1b3−a3b1)j+(a1b2−a2b1)k.

    }

}
