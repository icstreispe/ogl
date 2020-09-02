package ro.ics.ogl;

import com.jogamp.opengl.GLAutoDrawable;

/**
 * Created by X13 on 02.01.2017.
 */
public interface Figure {

    void init(GLAutoDrawable drawable);

    void display(GLAutoDrawable drawable);
}
