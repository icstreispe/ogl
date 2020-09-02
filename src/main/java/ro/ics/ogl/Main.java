package ro.ics.ogl;

import ro.ics.ogl.c.Scene;

/**
 * Created by mihais on 03.01.2016.
 *
 * TODO cleanup tot codul duplicat si redus la noul stil
 TODO toate figurile sa extinda Figure (redenumit la Figure/Piece?)
 TODO cum sa punem acele figuri la orice coordonata vrem?
 TODO de adaugat zoom in/out
 *
 * TODO: move to VBOs
 * http://gamedev.stackexchange.com/questions/34108/opengl-vbo-or-glbegin-glend
 *
 * The following commonly used functions are all deprecated:

 glBegin/glEnd
 glVertex*
 glNormal*
 glTextCoord*
 glTranslate*
 glRotate*
 glScale*
 glLoadIdenity
 glModelViewMatrix
 */
public class Main {



    public static void main(String[] args) {
        final Scene frame = new ObiectScene("/tiger2/tiger.obj", 1);
        //final Scene frame = new ObiectScene("/tiger1/Tiger.obj", 100);
        //final Scene frame = new ObiectScene("/avent/Avent.obj", 1);
        //final Scene frame = new ObiectScene("/house/house.obj", 1);
        //final Scene frame = new material();
        //final Scene frame = new FigureScene();
        //final Scene frame = new Cube2();
        //final Scene frame = new Pyramid();
        //final Scene frame = new Sfera();
        //final Scene frame = new Sfera3();
        //frame.setContentPane(joglMain);
        frame.start();
    }
}
