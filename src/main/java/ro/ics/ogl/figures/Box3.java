package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;

import static com.jogamp.opengl.GL.GL_FRONT;
import static com.jogamp.opengl.GL.GL_LINES;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;

/**
 * Created by X13 on 01.01.2017.
 */
public class Box3 implements Figure {


    GLAutoDrawable drawable;

    @Override
    public void init(GLAutoDrawable drawable) {
        this.drawable = drawable;
    }



    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        // Draw a coordinate axis
        gl.glColor3d(0., 1., 1.);

        gl.glBegin(GL_LINES);
        gl.glVertex3d(0., 0., 0.);
        gl.glVertex3d(12., 0., 0.);

        gl.glVertex3d(0., 0., 0.);
        gl.glVertex3d(0., 12., 0.);

        gl.glVertex3d(0., 0., 0.);
        gl.glVertex3d(0., 0., 12.);
        gl.glEnd();

//
// INSERT DRAWING CODE HERE
//

        float CYAN[] = {0.f, .8f, .8f, 1.f};

        double RED[] = {0.8, 0.0, 0.0};
        Box(5., 5, 5, RED, CYAN);

        gl.glFlush();

    }


    //
//        Name : Quad()
// Description : Inline function for drawing
// a quadralateral.
//

    //n = normal vector of this face
    void Quad(double []v1, double []v2, double [] v3, double [] v4, double [] n){
        final GL2 gl = drawable.getGL().getGL2();

        gl.glBegin(GL_QUADS);
        gl.glNormal3dv(n, 0);
        gl.glVertex3dv(v1, 0);
        gl.glVertex3dv(v2, 0);
        gl.glVertex3dv(v3, 0);
        gl.glVertex3dv(v4, 0);
        gl.glEnd();
    }

//
//        Name : CChildView::Box()
// Description : Draw an arbitrary size box. p_x,
//              p_y, and p_z are the height of
//              the box. We'll use this
//               as a common primitive.
//      Origin : The back corner is at 0, 0, 0, and
//               the box is entirely in the
//               positive octant.
//

    void Box(double p_x, double p_y,
             double p_z, double []p_color, float [] material)
    {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, material, 0);

        double a[] = {0., 0., p_z};

        double b[] = {p_x, 0., p_z};
        double c[] = {p_x, p_y, p_z};
        double f[] = {p_x, 0., 0.};
        double g[] = {p_x, p_y, 0.};

        double d[] = {0., p_y, p_z};
        double e[] = {0., 0., 0.};
        double h[] = {0., p_y, 0.};

        // I'm going to mess with the colors a bit so
        // the faces will be visible in solid shading
        gl.glColor3d(p_color[0], p_color[1], p_color[2]);
        Quad(a, b, c, d, new double []{0 ,0, 1}); // Front

        gl.glColor3d(p_color[0] * 0.95, p_color[1] * 0.95, p_color[2] * 0.95);
        Quad(c, b, f, g, new double []{1 ,0, 0}); // Right

        gl.glColor3d(p_color[0] * 0.85, p_color[1] * 0.85, p_color[2] * 0.85);
        Quad(h, g, f, e, new double []{0 ,0, -1}); // Back

        gl.glColor3d(p_color[0] * 0.90, p_color[1] * 0.90, p_color[2] * 0.90);
        Quad(d, h, e, a, new double []{-1 ,0, 0}); // Left

        gl.glColor3d(p_color[0] * 0.92, p_color[1] * 0.92, p_color[2] * 0.92);
        Quad(d, c, g, h, new double []{0 ,1, 0}); // Top

        gl.glColor3d(p_color[0] * 0.80, p_color[1] * 0.80, p_color[2] * 0.80);
        Quad(e, f, b, a, new double []{0 ,-1, 0}); // Bottom
    }
}
