package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;
import ro.ics.ogl.primitives.Color;

/**
 * Created by X13 on 16.01.2017.
 */
public class Ellipse implements Figure {


    float cx;
    float cy;
    float r1;
    float r2;
    int num_segments;

    public Ellipse (float cx,
            float cy,
            float r1,
            float r2,
            int num_segments){
        this.cx = cx;
        this.cy = cy;
        this.r1 = r1;
        this.r2 = r2;
        this.num_segments = num_segments;
    }


    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
    }


    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        //double theta = 2 * (float) Math.PI / (float) num_segments;
        //float c = (float) Math.cos(theta);//precalculate the sine and cosine
        //float s = (float) Math.sin(theta);

        //float x = 0;//we start at angle = 0
        //float y = 0;

        gl.glBegin(gl.GL_LINE_LOOP);
        gl.glColor3fv(Color.RED.toArray(), 0);
        for (int ii = 0; ii < num_segments; ii++) {
            double theta = 2 * ii * Math.PI / num_segments;
            float x = (float)(r1 * Math.sin(theta) + cx);
            float y = (float)(r2 * Math.cos(theta) + cy);
            gl.glVertex3f(x, y, 0);//output vertex
        }
        gl.glEnd();

    }

//v2
    public void display(GL2 gl) {

        gl.glTranslatef(cx, cy, 0.0f);
        float[] vertices = new float[num_segments * 2];
        int count = 0;
        gl.glBegin(gl.GL_LINES);
        for (int i = 0; i < num_segments; i++) {
            float alfa = 2 * i * (float) Math.PI / num_segments;
            vertices[count++] = (float) Math.sin(alfa) * 2 * r1;
            vertices[count++] = (float) Math.cos(alfa) * 2 * r2;
            gl.glVertex2f(0, 0);//output vertex
            gl.glVertex2f(vertices[count - 2], vertices[count - 1]);//output vertex
        }

        //gl.glVertexPointer(2, gl.GL_FLOAT, 0, convert(vertices));
        //gl.glDrawArrays((filled) ? gl.GL_TRIANGLE_FAN : gl.GL_LINE_LOOP, 0, segments);
        gl.glEnd();
    }


}
