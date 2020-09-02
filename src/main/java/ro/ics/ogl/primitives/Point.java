package ro.ics.ogl.primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;

/**
 * Created by X13 on 31.12.2016.
 * TODO de adaugat functii de genul: rotate, translate, add , ...
 */
public class Point implements Figure {

    public float x, y, z;

    //public float tx, ty, tz;    //for texture

    public Color color;

    public Point(){

    }

    public Point (float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point (float x, float y, float z, Color color){
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

    public float [] f (){
        return new float[]
                {x, y, z};
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        if (color != null) {
            gl.glColor3fv(color.toArray(), 0);
        }
        gl.glVertex3fv(f(), 0);
    }

    public String toString (){
        return "[" + x + "," + y + ","+ z + "]";
    }

}
