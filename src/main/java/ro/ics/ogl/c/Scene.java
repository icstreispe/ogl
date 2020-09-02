package ro.ics.ogl.c;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES1.GL_LIGHT_MODEL_AMBIENT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;

/**
 * Created by mihais on 03.01.2016.
 */
public class Scene extends JFrame implements GLEventListener{
    private static final int REFRESH_FPS = 25;    // Display refresh frames per second

    final static int WINDOW_WIDTH = 10280;  // Width of the drawable
    final static int WINDOW_HEIGHT = 1024; // Height of the drawable

    final FPSAnimator animator;  // Used to drive display()
    protected GLU glu = new GLU();             // For the GL Utility
    protected GLUT glut = new GLUT();
    private GLCapabilities caps;
    protected GLCanvas canvas;


    public String getWindowTitle (){
        return "3D Scene";
    }

    public FPSAnimator getAnimator() {
        return animator;
    }

    // Constructor
    public Scene() {

        final JFrame self = this;
        caps = new GLCapabilities(null);

        canvas = new GLCanvas(caps);
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        canvas.addGLEventListener(this);


        // Run the animation loop using the fixed-rate Frame-per-second animator,
        // which calls back display() at this fixed-rate (FPS).
        animator = new FPSAnimator(canvas, REFRESH_FPS, true);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Use a dedicate thread to run the stop() to ensure that the
                // animator stops before program exits.
                new Thread() {
                    @Override
                    public void run() {
                        getAnimator().stop(); // stop the animator loop
                        self.dispose();
                        System.exit(0);
                    }
                }.start();
            }
        });

/*
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                animator.stop();
                self.dispose();
                System.exit(0);
            }
        });
*/

        //this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(true);
        this.setUndecorated(true);      //full screen mode
        this.setExtendedState(Frame.MAXIMIZED_BOTH);  // full screen mode
        this.setTitle(getWindowTitle());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void start () {
        this.setVisible(true);
        getAnimator().start(); // start the animation loop
        canvas.requestFocus();
        canvas.requestFocusInWindow();
    }



    // Implement methods defined in GLEventListener
    @Override
    public void init(GLAutoDrawable drawable) {
        // Get the OpenGL graphics context
        GL2 gl = drawable.getGL().getGL2();
        // GL Utilities

        // blends colors nicely, and smoothes out lighting.
        //gl.glShadeModel(gl.GL_SMOOTH);  //GLLightingFunc

        // Set background color in RGBA. Alpha: 0 (transparent) 1 (opaque)
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);     // set background (clear) color

        // Setup the depth buffer and enable the depth testing
        gl.glClearDepth(1.0f);          // clear z-buffer to the farthest
        gl.glDepthFunc(GL_LESS);//EQUAL);   // the type of depth test to do
        gl.glEnable(GL_DEPTH_TEST);  // enables depth testing

        /*
        gl.glEnable(GL_COLOR_MATERIAL);   //?? asta strica luminarea
        gl.glColorMaterial (GL_FRONT_AND_BACK, GL_DIFFUSE);
        gl.glColorMaterial (GL_FRONT_AND_BACK, GL_SPECULAR);
        gl.glColorMaterial (GL_FRONT_AND_BACK, GL_EMISSION);
        gl.glColorMaterial (GL_FRONT_AND_BACK, GL_AMBIENT);
        */


        gl.glHint(gl.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  // best perspective correction
    }


    @Override
    public void display(GLAutoDrawable drawable) {

        // Get the OpenGL graphics context
        GL2 gl = drawable.getGL().getGL2();

        // Clear the color and the depth buffers
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);


        // Cull (ascunde) backfacing polygons
        //gl.glCullFace(GL_BACK);
        //gl.glEnable(GL_CULL_FACE);


        //light(gl);

        // Enable blending (pt transparenta
        //gl.glEnable(GL_BLEND);
        //gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

/*
        //LIGHT1
        gl.glEnable(GL_LIGHT1);
        lightpos = new float[] {-10f, -10f, -10f, 0.f};
        gl.glLightfv(GL_LIGHT1, GL_POSITION, lightpos, 0);
        float cyan[] = {0.f, .8f, .8f, 1.f};
        gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, cyan, 0);
        gl.glMaterialfv(GL_FRONT, GL_SPECULAR, cyan, 0);
*/

        // ----- Render the rotated object-----
        //gl.glLoadIdentity();                // reset the current model-view matrix
        //gl.glTranslatef(0.0f, 0.0f, -7.0f); // translate right and into the screen

    }

    private static final float SHINE_ALL_DIRECTIONS = -1;

    void light (GL2 gl) {


        gl.glLightModelfv( GL_LIGHT_MODEL_AMBIENT, new float[]{ .5f, .5f, .5f, 1f}, 0 );

        //create a light
        float lightpos[] = {15f, 15f, 0f, SHINE_ALL_DIRECTIONS};
        gl.glLightfv(GL_LIGHT0, GL_POSITION, lightpos, 1);

        gl.glLightfv(GL_LIGHT0, GL_SPECULAR, new float[]{1.f, 1.f, 1.f, 1f}, 0);
        gl.glLightfv(GL_LIGHT0, GL_AMBIENT, new float []{0f, 0f, 0f, 1f}, 0);
        gl.glLightfv( GL_LIGHT0, GL_DIFFUSE, new float[]{ 1.f, 1.f, 1.f, }, 0 );

        float lmodel_ambient[] = { 0.4f, 0.4f, 0.4f, 1.0f };
        float local_view[] = { 0.0f };
        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient, 0);
        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view, 0);

        // Enable lighting
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);


        //gl.glMaterialf(GL_);
        //gl.glMaterial ( GL_FRONT_AND_BACK, GL_EMISSION, ...colours... ) ;
    }



    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // Get the OpenGL graphics context
        GL2 gl = drawable.getGL().getGL2();

        height = (height == 0) ? 1 : height; // prevent divide by zero
        float aspect = (float)width / height;

        // Set the current view port to cover full screen
        gl.glViewport(0, 0, width, height);

        // Set up the projection matrix - choose perspective view
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity(); // reset


        // Angle of view (fovy) is 45 degrees (in the up y-direction). Based on this
        // canvas's aspect ratio. Clipping z-near is 0.1f and z-near is 100.0f.
        glu.gluPerspective(45.0f, aspect, 0.01f, 200.0f); // fovy, aspect, zNear, zFar

        /*
        final float fh = 1f;
        final float fw = fh * aspect;
        gl.glFrustumf(-fw, fw, -fh, fh, 1.0f, 10.0f);		//!!!!! e diferit
*/

        /*
         if (w <= h) gl.glOrtho(-2.5, 2.5, -2.5 * (float) h / (float) w,
                2.5 * (float) h / (float) w, -10.0, 10.0);
        else gl.glOrtho(-2.5 * (float) w / (float) h, 2.5 * (float) w / (float) h,
                -2.5, 2.5, -10.0, 10.0);
         */

        // Enable the model-view transform
        gl.glMatrixMode(gl.GL_MODELVIEW);   //GLMatrixFunc
        gl.glLoadIdentity(); // reset
    }



	/*
	//ceva ciudat aici pentru ca se pare ca taie anumite bucati....
	@Override
	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
		GL2 gl = gLDrawable.getGL().getGL2();
		final float aspect = (float) width / (float) height;
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		final float fh = 1f;
		final float fw = fh * aspect;
		gl.glFrustumf(-fw, fw, -fh, fh, 1.0f, 10.0f);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	*/



    @Override
    public void dispose(GLAutoDrawable gLDrawable) {
    }

}
