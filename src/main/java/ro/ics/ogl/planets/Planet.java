package ro.ics.ogl.planets;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.glsl.ShaderState;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import jogamp.opengl.glu.GLUquadricImpl;

import java.io.IOException;

/**
 * Created by mihais on 18.01.2016.
 */
public class Planet {

    private String textureFile = "/planets/jupiter.jpg";
    private int id;

    //"C:/Users/mihais/Downloads/earthmap1k.jpg"), true);
    //"C:/Users/mihais/Downloads/texture_earth_surface.jpg"), true);
    //"C:/Users/mihais/Downloads/texture_earth_clouds.jpg"), true);

    //"C:/Users/mihais/Downloads/earthcloudmap.jpg"), true);
    //"C:/Users/mihais/Downloads/venusmap.jpg"), true);
    //"C:/Users/mihais/Downloads/mercurymap.jpg"), true);
    //"C:/Users/mihais/Downloads/jupitermap.jpg"), true);
    //"C:/Users/mihais/Downloads/jupiter2_1k.jpg"), true);
    //"C:/Users/mihais/Downloads/saturnmap.jpg"), true);
    //"C:/Users/mihais/Downloads/neptunemap.jpg"), true);
    //"C:/Users/mihais/Downloads/plutomap1k.jpg"), true);
    //"C:/Users/mihais/Downloads/moonmap4k.jpg"), true);


    public String getTextureFile() {
        return textureFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void init (GL2 gl, GLU glu) {
        //GLU glu = GLU.createGLU(gl);

        try {


        Texture texture = TextureIO.newTexture(getClass().getResourceAsStream(textureFile), true, null);

        GLUquadric sphere = new GLUquadricImpl(gl, true, new ShaderState(), 0);
        glu.gluQuadricDrawStyle(sphere, glu.GLU_FILL);
        glu.gluQuadricTexture(sphere, true);
        glu.gluQuadricNormals(sphere, glu.GLU_SMOOTH);

        //Making a display list
        id = gl.glGenLists(1);
        gl.glNewList(id, gl.GL_COMPILE);
        texture.enable(gl);
        texture.bind(gl);
        //gl.glTranslatef(2.0f, 0.0f, 0.0f);
        gl.glRotatef(-90, 1.0f, 0.0f, 0.0f);
        glu.gluSphere(sphere, 2.0, 180, 180);
        texture.disable(gl);
        gl.glEndList();

        glu.gluDeleteQuadric(sphere);

            //-----------------
            //and whenever you want to render, call glCallList(mysphereID)
            //to kill the display list, glDeleteLists(mysphereID, 1);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
