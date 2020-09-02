package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;
import ro.ics.ogl.primitives.Quad;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;

/**
 * Created by X13 on 31.12.2016.
 */
public class Cub implements Figure {

    private static final int DOWN = 0;
    private static final int UP = 5;

    private static final int LEFT = 1;
    private static final int RIGHT = 4;

    private static final int FRONT = 3;
    private static final int BACK = 2;

    private static final int PTS = 2;   //2 pts / side
    public Point center;            //centrul cubului
    public float r;                 //raza
    public Point[][][] vertex;

    private Quad[] faces ;

    public Cub(Point center, float r) {
        this.center = center;
        vertex = new Point[PTS][][];
        for (int i = 0; i < PTS; i++){
            vertex[i] = new Point[PTS][];
            for (int j = 0; j < PTS; j++){
                vertex[i][j] = new Point[PTS];
                for (int k = 0; k < PTS; k++){
                    Point p  = new Point();
                    p.x = center.x + r * (- 0.5f + i );
                    p.y = center.y + r * (- 0.5f + j);
                    p.z = center.z + r * (- 0.5f + k);
                    vertex[i][j][k] = p;
                }
            }
        }




        faces = new Quad[] {
                new Quad(vertex[0][0][0], vertex[1][0][0], vertex[1][0][1], vertex[0][0][1], Color.BLACK),   //down
                new Quad(vertex[0][0][0], vertex[0][0][1], vertex[0][1][1], vertex[0][1][0], Color.BLACK),   //left ok

                new Quad(vertex[0][0][0], vertex[0][1][0], vertex[1][1][0], vertex[1][0][0], Color.BLACK),   //back ok
                new Quad(vertex[0][0][1], vertex[1][0][1], vertex[1][1][1], vertex[0][1][1], Color.BLACK),   //front ok

                new Quad(vertex[1][0][0], vertex[1][1][0], vertex[1][1][1], vertex[1][0][1], Color.BLACK),   //right ok
                new Quad(vertex[0][1][0], vertex[0][1][1], vertex[1][1][1], vertex[1][1][0], Color.BLACK),   //up
        };
    }

    public void setDown(Color color) {
        faces[DOWN].setColor(color);
    }

    public void setUp(Color color) {
        faces[UP].setColor(color);
    }

    public void setFront(Color color) {
        faces[FRONT].setColor(color);
    }

    public void setBack(Color color) {
        faces[BACK].setColor(color);
    }

    public void setLeft(Color color) {
        faces[LEFT].setColor(color);
    }

    public void setRight(Color color) {
        faces[RIGHT].setColor(color);
    }


    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        faces[0].display(drawable);
        faces[1].display(drawable);
        faces[2].display(drawable);
        faces[3].display(drawable);
        faces[4].display(drawable);
        faces[5].display(drawable);
    }

}
