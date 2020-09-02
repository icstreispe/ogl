package ro.ics.ogl.primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;

import static com.jogamp.opengl.GL.GL_LINE_LOOP;

/**
 * Created by X13 on 31.12.2016.
 */
public class Circle implements Figure {


    private static final float DEG2RAD = (float) Math.PI / 180;
    protected float r;
    protected Color color;


    public Circle(Color color, float r) {
        this.color = color;
        this.r = r;
    }

    @Override
    public void init(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glColor3fv(color.toArray(), 0);
        gl.glBegin(GL_LINE_LOOP);

        for (int i = 0; i < 360; i++) {
            float degInRad = i * DEG2RAD;
            gl.glVertex3f((float) Math.cos(degInRad) * r, 0,  (float) Math.sin(degInRad) * r);
        }

        gl.glEnd();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
