package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;

/**
 * exemplu de tesselare sfera din poligoane
 */
public class Sphere implements Figure {

    private double a[] = {1, 0, 0};
    private double b[] = {0, 0, -1};
    private double c[] = {-1, 0, 0};
    private double d[] = {0, 0, 1};
    private double e[] = {0, 1, 0};
    private double f[] = {0, -1, 0};

    private static final int recurse = 5;
    private double p_radius;


    private static final long max = (long)Math.pow(8, recurse+1);

    private static final float color = 1f/recurse;

    public Sphere(double p_radius) {
        this.p_radius = p_radius;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();


        float white[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float cyan[] = {0.f, .8f, .8f, 1.f};

        gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, cyan, 0);
        gl.glMaterialfv(GL_FRONT, GL_SPECULAR, white, 0);

        float shininess[] = {90};
        gl.glMaterialfv(GL_FRONT, GL_SHININESS, shininess, 0);



        //gl.glShadeModel(GL_FLAT);//fara asta se face calculul gradientului de shade si in triunghiuri

        //gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE); //pt wireframe

        SphereFace(recurse, d, a, e, drawable);
        SphereFace(recurse, a, b, e, drawable);
        SphereFace(recurse, b, c, e, drawable);
        SphereFace(recurse, c, d, e, drawable);
        SphereFace(recurse, a, d, f, drawable);
        SphereFace(recurse, b, a, f, drawable);
        SphereFace(recurse, c, b, f, drawable);
        SphereFace(recurse, d, c, f, drawable);
    }


    void SphereFace(int p_recurse, double[] a,
                    double[] b, double[] c, GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        if(p_recurse > 1) {
            // Compute vectors halfway between the passed vectors
            double d[] = {a[0] + b[0], a[1] + b[1], a[2] + b[2]};
            double e[] = {b[0] + c[0], b[1] + c[1], b[2] + c[2]};
            double f[] = {c[0] + a[0], c[1] + a[1], c[2] + a[2]};

            Normalize3(d);
            Normalize3(e);
            Normalize3(f);

            SphereFace(p_recurse-1, a, d, f, drawable);
            SphereFace(p_recurse-1, d, b, e, drawable);
            SphereFace(p_recurse-1, f, e, c, drawable);
            SphereFace(p_recurse-1, f, d, e, drawable);

            return ;
        }


        float [] rez = {(float)(a[0]+b[0]+c[0]),(float)(a[1]+b[1]+c[1]), (float)(a[2]+b[2]+c[2])};
        Normalize3(rez);
        rez = new float[]{.66f-rez[0], 0.66f-rez[1], .66f-rez[2]};

        gl.glBegin(GL2.GL_TRIANGLES);
        //gl.glColor3fv(rez, 0);  //TODO de ce
        gl.glNormal3dv(a, 0);
        gl.glVertex3d(a[0] * p_radius, a[1] * p_radius, a[2] * p_radius);
        gl.glNormal3dv(b, 0);
        gl.glVertex3d(b[0] * p_radius, b[1] * p_radius, b[2] * p_radius);
        gl.glNormal3dv(c, 0);
        gl.glVertex3d(c[0] * p_radius, c[1] * p_radius, c[2] * p_radius);
        gl.glEnd();
    }

    void Normalize3(double []v)
    {
        double len = Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        v[0] /= len;
        v[1] /= len;
        v[2] /= len;
    }
    void Normalize3(float []v)
    {
        float len = (float)Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        v[0] /= len;
        v[1] /= len;
        v[2] /= len;
    }
}
