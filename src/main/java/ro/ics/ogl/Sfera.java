package ro.ics.ogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.c.RotatingScene;
import ro.ics.ogl.planets.Planet;

/**
 * This program demonstrates the use of some of the gluQuadric* routines.
 * Quadric objects are created with some quadric properties and the callback
 * routine to handle errors. Note that the cylinder has no top or bottom and the
 * circle has a hole in it.
 *
 * @author Kiet Le (java port)
 */

public class Sfera extends RotatingScene {


    Planet planet = new Planet();

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();


        float light_position[] =
                {-3.0f, 3.0f, 3.0f, 0.0f};


        float mat_ambient[] =
                {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] =
                {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_shininess[] =
                {1.0f};

        float model_ambient[] =
                {1.0f, 1.0f, 1.0f, 10.0f};


        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        //gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, mat_ambient, 0);
        //gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialfv(gl.GL_FRONT, gl.GL_SHININESS, mat_shininess, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, light_position, 0);
        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, model_ambient, 0);

        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_LIGHT0);

        gl.glEnable(gl.GL_DEPTH_TEST);


/*


     // Create 4 display lists, each with a different quadric object. Different
     // drawing styles and surface normal specifications are demonstrated.

        startList = gl.glGenLists(1);
        GLUquadric qobj = glu.gluNewQuadric();

     // glu.gluQuadricCallback(qobj, GLU.GLU_ERROR, errorCallback); <br>
     // Quadric call backs have yet been implemented in JOGL.
     // But this program still work.


        glu.gluQuadricDrawStyle(qobj, GLU.GLU_SILHOUETTE); // flat shaded
        glu.gluQuadricNormals(qobj, GLU.GLU_FLAT);
        gl.glNewList(startList + 1, gl.GL_COMPILE);
        //glu.gluCylinder(qobj, 1, 0.1, 2.0, 18, 10);
        //glu.gluDisk(qobj, 1, 2, 18, 10);
        //glu.gluSphere(qobj, 1, 18, 18);
        //glut.glutSolidTorus(1, 2, 18, 18);
        glut.glutWireDodecahedron();
        gl.glEndList();
*/


        planet.init(gl, glu);

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        //gl.glPushMatrix();

        gl.glEnable(gl.GL_LIGHTING);
        gl.glShadeModel(gl.GL_SMOOTH);
/*
        gl.glTranslatef(0.0f, -2.0f, 0.0f);
        //gl.glCallList(startList);


        gl.glShadeModel(gl.GL_FLAT);
        gl.glTranslatef(0.0f, 2.0f, 0.0f);

        gl.glPushMatrix();
        gl.glRotatef(300.0f, 1.0f, 0.0f, 0.0f);
        gl.glCallList(startList + 1);
        gl.glPopMatrix();
        */
/*
        gl.glDisable(gl.GL_LIGHTING);
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glTranslatef(2.0f, -2.0f, 0.0f);
        gl.glCallList(startList + 2);

        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glTranslatef(0.0f, 2.0f, 0.0f);
        gl.glCallList(startList + 3);

*/
        //gl.glPopMatrix();

        super.display(drawable);
        gl.glCallList(planet.getId());

        gl.glFlush();

    }


}