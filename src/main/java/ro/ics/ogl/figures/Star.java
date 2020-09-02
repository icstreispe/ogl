package ro.ics.ogl.figures;

import ro.ics.ogl.Figure;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;
import ro.ics.ogl.primitives.Triangle;

/**
 * Self-contained example (within a single class only to keep it simple) 
 * displaying a rotating quad
 */
public class Star implements Figure {
 

	private Point [] p1 = new Point[MAX];
	private Point [] p2 = new Point[MAX];
	private final static Point CENTER = new Point(0.0f, 0.0f, 1.0f);
	private Triangle[] faces1 = new Triangle[MAX];
	private Triangle [] faces2 = new Triangle[MAX];

	private static final int MAX = 5;
	private static final float R = 3;
	private static final float r = 1;
	private static final float ALFA = (float)2 * (float)Math.PI / (float)MAX;


	public Star (){
		for (int i = 0; i < MAX; i++) {
			p1[i] = new Point (R * (float) Math.sin(ALFA * i), R * (float) Math.cos(ALFA * i), 0);
			p2[i] = new Point (r * (float) Math.sin(ALFA * i + ALFA / 2), r * (float) Math.cos(ALFA * i + ALFA / 2), 0);
		}

		for (int i = 0; i < MAX; i++) {
			faces1[i] = new Triangle(p1[i], p2[i], CENTER, Color.ORANGE);
			faces2[i] = new Triangle(p2[i], p1[(i+1)% MAX], CENTER, Color.YELLOW);
		}
	}


	@Override
	public void init(GLAutoDrawable drawable) {
	}

	//build from triangles
	@Override
	public void display(GLAutoDrawable drawable) {

		final GL2 gl = drawable.getGL().getGL2();

		gl.glBegin(GL2.GL_TRIANGLES);

		for (int i = 0; i < MAX; i++) {
			faces1[i].display(drawable);
			faces2[i].display(drawable);
		}

		gl.glEnd();
	}

}