package ro.ics.ogl.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X13 on 17.01.2017.
 */
public class Sfera3 implements Figure {

    private static final int MAX = 2;
    List<float[]> pts;
    private float r;
    private List<int[]> faces;

    public Sfera3(float r) {
        this.r = r;
        pts = new ArrayList<>();
        pts.add(new float[]{r, 0, 0});      //0   x
        pts.add(new float[]{-r, 0, 0});     //1  -x
        pts.add(new float[]{0, r, 0});      //2   y
        pts.add(new float[]{0, -r, 0});     //3  -y
        pts.add(new float[]{0, 0, r});      //4   z
        pts.add(new float[]{0, 0, -r});     //5  -z

        faces = new ArrayList<>();
        fac(MAX, new int[]{0, 2, 4});
        fac(MAX, new int[]{1, 4, 2});
        fac(MAX, new int[]{0, 5, 2});
        fac(MAX, new int[]{1, 2, 5});

        fac(MAX, new int[]{0, 4, 3});
        fac(MAX, new int[]{1, 3, 4});
        fac(MAX, new int[]{0, 3, 5});
        fac(MAX, new int[]{1, 5, 3});
        System.out.format("%d:%d%n", pts.size(), faces.size());
    }

    public static float[] rezult(float[] p1, float[] p2, float[] p3) {
        return new float[]{p1[0] + p2[0] + p3[0], p1[1] + p2[1] + p3[1], p1[2] + p2[2] + p3[2]};
    }

    public static float dist(float[] p) {
        return (float) Math.pow(Math.pow(p[0], 2) + Math.pow(p[1], 2) + Math.pow(p[2], 2), 0.5d);
    }

    public static float[] normAbs(float[] p) {
        float d = dist(p);
        return new float[]{ Math.abs(p[0] / d), Math.abs(p[1] / d), Math.abs(p[2] / d)};
    }

    public static float[] rot(int a1, double a, float[] p) {
        int a0 = (a1 + 2) % 3;
        int a2 = (a1 + 1) % 3;
        float x = (float) (p[a0] * Math.cos(a) - p[a2] * Math.sin(a));
        float y = (float) (p[a0] * Math.sin(a) + p[a2] * Math.cos(a));
        float[] rez = new float[3];
        rez[a0] = x;
        rez[a1] = p[a1];
        rez[a2] = y;
        return rez;
    }

    public void fac(int n, int[] ip) {
        if (n == 0) {
            faces.add(ip);
            return;
        }

        float p1[] = pts.get(ip[0]);
        float p2[] = pts.get(ip[1]);
        float p3[] = pts.get(ip[2]);

        float d = dist(rezult(p1, p2, p3));
        d = r / d;

        float[] c = {(p1[0] + p2[0] + p3[0]) * d, (p1[1] + p2[1] + p3[1]) * d, (p1[2] + p2[2] + p3[2]) * d};
        int idx = pts.size();
        pts.add(c);

        //System.out.format();

        fac(n - 1, new int[]{ip[0], ip[1], idx});
        fac(n - 1, new int[]{ip[0], idx, ip[2]});
        fac(n - 1, new int[]{idx, ip[1], ip[2]});
    }

    public void rot(double a) {
        for (int i = 0; i < pts.size(); i++) {
            float[] pt = rot(1, a, pts.get(i));
            pts.set(i, pt);
        }
    }


    public List<int[]> getFaces() {
        return faces;
    }

    public float[] getPt(int i) {
        return pts.get(i);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    public void display (GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();


        for (int i = 0; i < faces.size(); i++) {

            gl.glBegin(GL2.GL_TRIANGLES);
            float[] a = getPt(faces.get(i)[0]);
            float[] b = getPt(faces.get(i)[1]);
            float[] c = getPt(faces.get(i)[2]);

            System.out.format("%.2f:%.2f:%.2f%n", a[0], a[1], a[2]);
            //gl.glColor3fv(rez, 0);  //TODO de ce
            gl.glNormal3fv(a, 0);
            gl.glVertex3fv(a, 0);
            gl.glNormal3fv(b, 0);
            gl.glVertex3fv(b, 0);
            gl.glNormal3fv(c, 0);
            gl.glVertex3fv(c, 0);
            gl.glEnd();
        }

    }
}
