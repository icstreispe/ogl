package ro.ics.ogl;

import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.c.ControlledScene;
import ro.ics.ogl.primitives.Obiect;

import java.util.List;


public class ObiectScene extends ControlledScene {

	public String getWindowTitle() {
		return "Obiect";
	}

	private List<Obiect> obiect;

	public ObiectScene(String path, float zoom) {
		obiect = FisierObj.readFile(path, zoom);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);

		for (Obiect o:obiect) {
			o.init(drawable);
		}
	}



	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);

		for (Obiect o:obiect) {
			o.display(drawable);
		}
	}
}