package ro.ics.ogl.figures;

import ro.ics.ogl.Figure;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.primitives.Color;
import ro.ics.ogl.primitives.Point;

/**
 * Self-contained example (within a single class only to keep it simple) 
 * displaying a rotating quad
 */
public class Rubik implements Figure {
 



	private Cub[][][] cubes;	//elements in the scene
	private static final int SIDE = 3;	//3 cubes / side



	public Rubik () {

		cubes = new Cub[SIDE][][];
		for (int i = 0; i < SIDE; i++) {
			cubes[i] = new Cub[SIDE][];

			for (int j = 0; j < SIDE; j++){
				cubes[i][j] = new Cub[SIDE];
				for (int k = 0; k < SIDE; k++){
					Cub cub = new Cub(new Point(i, j, k), 0.95f);

					if (i == 0){
						cub.setLeft(Color.WHITE);
					} else if (i == SIDE - 1){
						cub.setRight(Color.YELLOW);
					}

					if (j == 0){
						cub.setDown(Color.RED);
					} else if (j == SIDE - 1){
						cub.setUp(Color.BLUE);
					}

					if (k == 0){
						cub.setFront(Color.GREEN);
					} else if (k == SIDE - 1){
						cub.setBack(Color.ORANGE);
					}

					cubes[i][j][k] = cub;
				}
			}
		}
	}

	@Override
	public void init(GLAutoDrawable drawable) {

	}

	@Override
	public void display(GLAutoDrawable drawable) {

		final GL2 gl = drawable.getGL().getGL2();
		//super.display(drawable);

		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				for (int k = 0; k < SIDE; k++) {
					if (cubes[i][j][k] != null) {
						cubes[i][j][k].display(drawable);
					}
				}
			}
		}
	}



}