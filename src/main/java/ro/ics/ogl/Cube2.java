package ro.ics.ogl;

import ro.ics.ogl.c.RotatingScene;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

/**
 * Created by mihais on 03.01.2016.
 */
public class Cube2 extends RotatingScene {

    static float v[][] = createV();
    float[] light_diffuse = {1.0f, 0.0f, 0.0f, 1.0f};  /* Red diffuseColor light. */
    float[] light_position = {1.0f, 1.0f, 1.0f, 0.0f};  /* Infinite light location. */
    float n[][] = {  /* Normals for the 6 faces of a cube. */
            {-1.0f, 0.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {1.0f, 0.0f, 0.0f},
            {0.0f, -1.0f, 0.0f}, {0.0f, 0.0f, 1.0f}, {0.0f, 0.0f, -1.0f}};
    int[][] faces = {  /* Vertex indices for the 6 faces of a cube. */
            {0, 1, 2, 3}, {3, 2, 6, 7}, {7, 6, 5, 4},
            {4, 5, 1, 0}, {5, 6, 2, 1}, {7, 4, 0, 3}};

    private static float[][] createV() {
        // Setup cube vertex data.
        float[][] v = new float[8][3];
        v[0][0] = v[1][0] = v[2][0] = v[3][0] = -1;
        v[4][0] = v[5][0] = v[6][0] = v[7][0] = 1;
        v[0][1] = v[1][1] = v[4][1] = v[5][1] = -1;
        v[2][1] = v[3][1] = v[6][1] = v[7][1] = 1;
        v[0][2] = v[3][2] = v[4][2] = v[7][2] = 1;
        v[1][2] = v[2][2] = v[5][2] = v[6][2] = -1;
        return v;
    }

    void
    drawBox(GL2 gl) {
        int i;

        for (i = 0; i < 6; i++) {
            gl.glBegin(gl.GL_QUADS);
            gl.glNormal3fv(Util.toFloatBuffer(n[i][0]));
            gl.glVertex3fv(Util.toFloatBuffer(v[faces[i][0]][0]));
            gl.glVertex3fv(Util.toFloatBuffer(v[faces[i][1]][0]));
            gl.glVertex3fv(Util.toFloatBuffer(v[faces[i][2]][0]));
            gl.glVertex3fv(Util.toFloatBuffer(v[faces[i][3]][0]));
            gl.glEnd();
        }
    }

    @Override
    public void display(GLAutoDrawable glDrawable) {
        final GL2 gl = glDrawable.getGL().getGL2();


        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        drawBox(gl);
        glDrawable.swapBuffers();
    }

    @Override
    public void init(GLAutoDrawable glDrawable) {

        glu = new GLU();

        GL2 gl = glDrawable.getGL().getGL2();



  /* Enable a single OpenGL light. */
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, Util.toFloatBuffer(light_diffuse));
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, Util.toFloatBuffer(light_position));
        gl.glEnable(gl.GL_LIGHT0);
        gl.glEnable(gl.GL_LIGHTING);

  /* Use depth buffering for hidden surface elimination. */
        gl.glEnable(gl.GL_DEPTH_TEST);

  /* Setup the view of the cube. */
        gl.glMatrixMode(gl.GL_PROJECTION);
        glu.gluPerspective( /* field of view in degree */ 40.0,
    /* aspect ratio */ 1.0,
    /* Z near */ 1.0, /* Z far */ 10.0);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        glu.gluLookAt(0.0, 0.0, 5.0,  /* eye is at (0,0,5) */
                0.0, 0.0, 0.0,      /* center is at (0,0,0) */
                0.0, 1.0, 0.);      /* up is in positive Y direction */

  /* Adjust cube position to be asthetic angle. */
        gl.glTranslatef(0.0f, 0.0f, -1.0f);
        gl.glRotatef(60, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(-20, 0.0f, 0.0f, 1.0f);
    }


/*
    public static void main(String args[])
    {



        //glutInit(&argc, argv);
        //glu.glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
        //glutCreateWindow("red 3D lighted cube");
        //glutDisplayFunc(display);
        //init();
        //glutMainLoop();


        //return 0;             // ANSI C requires main to return int.
    }
*/
}
