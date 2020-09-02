package jogamp;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import com.sun.opengl.util.GLUT;
*/
import javax.swing.JFrame;



/**
 * This program demonstrates the use of some of the gluQuadric* routines.
 * Quadric objects are created with some quadric properties and the callback
 * routine to handle errors. Note that the cylinder has no top or bottom and the
 * circle has a hole in it.
 *
 * @author Kiet Le (java port)
 */

public class Quadric
        extends JFrame
        implements GLEventListener
        , KeyListener
{
    private GLCapabilities caps;
    private GLCanvas canvas;
    private GLU glu;
    private GLUT glut;

    private int startList;


    public Quadric()
    {
        super("quadric");

        caps = new GLCapabilities(null);
        canvas = new GLCanvas(caps);
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);

        add(canvas);
    }

    public void run()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        canvas.requestFocusInWindow();
    }

    public static void main(String[] args)
    {
        new Quadric().run();
    }

    public void init(GLAutoDrawable drawable)
    {
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();
        glut = new GLUT();

        GLUquadric qobj;
        float mat_ambient[] =
                { 0.5f, 0.5f, 0.5f, 1.0f };
        float mat_specular[] =
                { 1.0f, 1.0f, 1.0f, 1.0f };
        float mat_shininess[] =
                { 50.0f };
        float light_position[] =
                { 1.0f, 1.0f, 1.0f, 0.0f };
        float model_ambient[] =
                { 0.5f, 0.5f, 0.5f, 1.0f };

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialfv(gl.GL_FRONT, gl.GL_SHININESS, mat_shininess, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, light_position, 0);
        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, model_ambient, 0);

        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_LIGHT0);
        gl.glEnable(gl.GL_DEPTH_TEST);

    /*
     * Create 4 display lists, each with a different quadric object. Different
     * drawing styles and surface normal specifications are demonstrated.
     */
        startList = gl.glGenLists(4);
        qobj = glu.gluNewQuadric();

    /*
     * glu.gluQuadricCallback(qobj, GLU.GLU_ERROR, errorCallback); <br>
     * Quadric call backs have yet been implemented in JOGL.
     * But this program still work.
     */
        glu.gluQuadricDrawStyle(qobj, GLU.GLU_FILL); /* smooth shaded */
        glu.gluQuadricNormals(qobj, GLU.GLU_SMOOTH);
        gl.glNewList(startList, gl.GL_COMPILE);
        glu.gluSphere(qobj, 0.75, 15, 10);
        gl.glEndList();


        glu.gluQuadricDrawStyle(qobj, GLU.GLU_LINE); /* all polygons wireframe */
        glu.gluQuadricNormals(qobj, GLU.GLU_NONE);
        gl.glNewList(startList + 2, gl.GL_COMPILE);
        glu.gluDisk(qobj, 0.25, 1.0, 20, 4);
        gl.glEndList();

        glu.gluQuadricDrawStyle(qobj, GLU.GLU_SILHOUETTE); /* boundary only */
        glu.gluQuadricNormals(qobj, GLU.GLU_NONE);
        gl.glNewList(startList + 3, gl.GL_COMPILE);
        glu.gluPartialDisk(qobj, 0.0, 1.0, 20, 4, 0.0, 225.0);
        gl.glEndList();

    }

    public void display(GLAutoDrawable drawable)
    {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glPushMatrix();

        gl.glEnable(gl.GL_LIGHTING);
        gl.glShadeModel(gl.GL_SMOOTH);
        gl.glTranslatef(-1.0f, -1.0f, 0.0f);
        gl.glCallList(startList);

        gl.glShadeModel(gl.GL_FLAT);
        gl.glTranslatef(0.0f, 2.0f, 0.0f);
        gl.glPushMatrix();
        gl.glRotatef(300.0f, 1.0f, 0.0f, 0.0f);
        gl.glCallList(startList + 1);
        gl.glPopMatrix();

        gl.glDisable(gl.GL_LIGHTING);
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glTranslatef(2.0f, -2.0f, 0.0f);
        gl.glCallList(startList + 2);

        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glTranslatef(0.0f, 2.0f, 0.0f);
        gl.glCallList(startList + 3);

        gl.glPopMatrix();
        gl.glFlush();

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
    {
        GL2 gl = drawable.getGL().getGL2();

        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) gl.glOrtho(-2.5, 2.5, -2.5 * (float) h / (float) w,
                2.5 * (float) h / (float) w, -10.0, 10.0);
        else gl.glOrtho(-2.5 * (float) w / (float) h, 2.5 * (float) w / (float) h,
                -2.5, 2.5, -10.0, 10.0);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
                               boolean deviceChanged)
    {
    }

    public void keyTyped(KeyEvent key)
    {
    }

    public void keyPressed(KeyEvent key)
    {
        switch (key.getKeyChar()) {

            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

            default:
                System.out.println("nothin pressed.");
                break;
        }
    }

    public void keyReleased(KeyEvent key)
    {
    }

    @Override
    public void dispose(GLAutoDrawable gLDrawable) {
    }

}