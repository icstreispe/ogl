package ro.ics.ogl.c;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

/**
 * Created by mihais on 03.01.2016.
 */
public class OLight extends Scene {

    protected GLU glu = new GLU();             // For the GL Utility

    float ax, ay, az;       /* angles for animation */
    GLUquadric quadObj; /* used in drawscene */
    static float lmodel_twoside[] =
            {GL2.GL_TRUE};
    static float lmodel_oneside[] =
            {GL2.GL_FALSE};

    /*
    public void init (){


        quadObj = gluNewQuadric();  // this will be used in drawScene

        //glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
        //glutCreateWindow("Two-sided lighting");

        ax = 10.0;
        ay = -10.0;
        az = 0.0;

        initLightAndMaterial();

        glutDisplayFunc(drawScene);
        glutReshapeFunc(resize);
        glutCreateMenu(menu);

        glutAddMenuEntry("Motion", 3);
        glutAddMenuEntry("Two-sided lighting", 1);
        glutAddMenuEntry("One-sided lighting", 2);
        glutAttachMenu(GLUT_RIGHT_BUTTON);
    }


@Override
public void display(GLAutoDrawable drawable) {


    // Get the OpenGL graphics context
    GL2 gl = drawable.getGL().getGL2();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);

    gl.glPushMatrix();
    gl.gluQuadricDrawStyle(quadObj, gl.GLU_FILL);
    gl.glColor3f(1.0f, 1.0f, 0.0f);
    gl.glRotatef(ax, 1.0f, 0.0f, 0.0f);
    gl.glRotatef(-ay, 0.0f, 1.0f, 0.0f);

    GLUquadric cylinder = new GLUquadricImpl();
    cylinder.setDrawStyle(GLU.GLU_FILL);
    cylinder.setNormals(GLU.GLU_SMOOTH);
    cylinder.draw(baseRadius, topRadius, height, slices, stacks);

    gl.gluCylinder(quadObj, 2.0, 5.0, 10.0, 20, 8);  // draw a cone

    gl.glPopMatrix();

    glu.SwapBuffers();
    }

    void
    setMatrix( GL2 gl)
    {
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-15.0, 15.0, -15.0, 15.0, -10.0, 10.0);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    int count = 0;

    void
    animation()
    {
        ax += 5.0;
        ay -= 2.0;
        az += 5.0;
        if (ax >= 360)
            ax = 0.0f;
        if (ay <= -360)
            ay = 0.0f;
        if (az >= 360)
            az = 0.0f;
        drawScene();
        count++;
        if (count >= 60)
            glutIdleFunc(NULL);
    }

    /*
    // ARGSUSED1
    void
    keyboard(unsigned char c, int x, int y)
    {
        switch (c) {
            case 27:
                exit(0);
                break;
            default:
                break;
        }
    }

    void
    menu(int choice)
    {
        switch (choice) {
            case 3:
                count = 0;
                glutIdleFunc(animation);
                break;
            case 2:
                glLightModelfv(GL_LIGHT_MODEL_TWO_SIDE, lmodel_oneside);
                glutSetWindowTitle("One-sided lighting");
                glutPostRedisplay();
                break;
            case 1:
                glLightModelfv(GL_LIGHT_MODEL_TWO_SIDE, lmodel_twoside);
                glutSetWindowTitle("Two-sided lighting");
                glutPostRedisplay();
                break;
        }
    }

    void
    resize(int w, int h)
    {
        glViewport(0, 0, w, h);
        setMatrix();
    }


    void
    initLightAndMaterial(GLAutoDrawable drawable) {


        // Get the OpenGL graphics context
        GL2 gl = drawable.getGL().getGL2();


        float ambientColor[] =
                {0.1f, 0.1f, 0.1f, 1.0f};
        float diffuseColor[] =
                {0.5f, 1.0f, 1.0f, 1.0f};
        float position[] =
                {90.0f, 90.0f, 150.0f, 0.0f};

        float front_mat_shininess[] =
                {60.0f};
        float front_mat_specular[] =
                {0.2f, 0.2f, 0.2f, 1.0f};
        float front_mat_diffuse[] =
                {0.5f, 0.5f, 0.28f, 1.0f};
        float back_mat_shininess[] =
                {60.0f};
        float back_mat_specular[] =
                {0.5f, 0.5f, 0.2f, 1.0f};
        float back_mat_diffuse[] =
                {1.0f, 0.9f, 0.2f, 1.0f};

        float lmodel_ambient[] =
                {1.0f, 1.0f, 1.0f, 1.0f};

        setMatrix(gl);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glDepthFunc(gl.GL_LEQUAL);

        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, ambientColor);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, diffuseColor);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, position);

        gl.glMaterialfv(gl.GL_FRONT, gl.GL_SHININESS, front_mat_shininess);
        gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, front_mat_specular);
        gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, front_mat_diffuse);
        gl.glMaterialfv(gl.GL_BACK, gl.GL_SHININESS, back_mat_shininess);
        gl.glMaterialfv(gl.GL_BACK, gl.GL_SPECULAR, back_mat_specular);
        gl.glMaterialfv(gl.GL_BACK, gl.GL_DIFFUSE, back_mat_diffuse);

        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
        gl.glLightModelfv(gl.GL_LIGHT_MODEL_TWO_SIDE, lmodel_twoside);

        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_LIGHT0);
        gl.glShadeModel(gl.GL_SMOOTH);
    }
*/
}
