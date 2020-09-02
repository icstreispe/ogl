package ro.ics.ogl.figures;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;
import ro.ics.ogl.primitives.Quad;

import static com.jogamp.opengl.GL.GL_CULL_FACE;

/**
 * Created by X13 on 31.12.2016.
 */
public class Square implements Figure {



    private static final int PTS = 4;   //2 pts / side
    public Point center;            //centrul cubului
    public float r;                 //raza
    public Point[] vertex;

    private Quad[] faces ;

    public Square() {
        this.center = center;
        vertex = new Point[PTS];
        vertex[0] = new Point(0, 0, 0);
        vertex[1]= new Point(1, 0, 0);
        vertex[2]= new Point(1, 1, 0);
        vertex[3]= new Point(0, 1, 0);




        faces = new Quad[] {
                new Quad(vertex[0], vertex[1], vertex[2], vertex[3], Color.RED),   //side
                new Quad(vertex[0], vertex[3], vertex[2], vertex[1], Color.BLUE),   //reverse side
        };
    }


    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glEnable(GL_CULL_FACE);
        gl.glCullFace(GL.GL_FRONT); //numai fata e colorata, spatele nu
        faces[0].display(drawable);
        faces[1].display(drawable);
    }

}
