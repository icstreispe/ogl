package ro.ics.ogl;

import ro.ics.ogl.c.ControlledScene;
import com.jogamp.opengl.*;
import ro.ics.ogl.figures.*;

/**
 * Self-contained example (within a single class only to keep it simple) 
 * displaying a rotating quad
 */
public class FigureScene extends ControlledScene {


	public String getWindowTitle() {
		return "Jogl Quad drawing";
	}

	//Figure f = new Romburi1();
	//Figure f = new Romburi2();
	//Figure f = new Romburi3();
	//Figure f = new PoligonSimetric(5, 2);
	//Figure f = new LiteraN();
	//Figure f = new LiteraA();
	//Figure f = new Prysm();
	Figure f = new Casa();
	//Figure f = new Box3();
	//Figure f = new Sphere(3d);
	//Figure f = new Sfera3(1);
	//Figure f = new Star();
	//Figure f = new Cilindru();
	//Figure f = new Rubik();
	//Cub f = new Cub(new Vector(0, 0, 5, RED), 2);
	//Figure f = new Square();




	//Figure d = new Ellipse(0f, 0f, 3f, 1f, 100);

	@Override
	public void init(GLAutoDrawable drawable) {
		f.init(drawable);

	}


	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);

		final GL2 gl = drawable.getGL().getGL2();


		// Draw A Quad
		//VBO vbo = createVBO(gl, vertexList, texelList);
		//renderVBO(gl, vbo);


		//gl.glTranslatef(1.0f, 0.0f, 0.0f);

		//d1.display(drawable);
		//d2.display(drawable);
		//d3.display(drawable);
		//d4.display(drawable);
		//d7.display(drawable);
		//d9.display(drawable);
		f.display(drawable);
	}
}