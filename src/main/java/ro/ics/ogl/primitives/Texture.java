package ro.ics.ogl.primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;
import ro.ics.ogl.Figure;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL.GL_LINEAR;

/**
 * Created by X13 on 10.01.2017.
 * poate fi folosit ca element fake
 */
public class Texture implements Figure {

    // Texture
    private com.jogamp.opengl.util.texture.Texture texture;
    private String path;// = "../images/nehe.png";  //goala cand nu exista textura
    public static final String FILE_TYPE = "tga";

    // Texture image flips vertically. Shall use TextureCoords class to retrieve the
    // top, bottom, left and right coordinates.
    private float top, bottom, left, right;


    public Texture(String path) {
        this.path = path;
    }


        //TODO poate vor tr mutati in Scene
        @Override
    public void init(GLAutoDrawable drawable) {
        if (path == null){
            return ;
        }

        GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context

        // Use linear filter for texture if image is larger than the original texture
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        // Use linear filter for texture if image is smaller than the original texture
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

        try {
            // Create a OpenGL Texture object from (URL, mipmap, file suffix)
            // Use URL so that can read from JAR and disk file.
            texture = TextureIO.newTexture(getClass().getResource(path), false, FILE_TYPE);


            //texturi todo
//            int [] normalbuffer;
//            int size;
//            gl.glGenBuffers(size,  normalbuffer, 0);
//            gl.glBindBuffer(GL_ARRAY_BUFFER, 0);//normalbuffer);
//            gl.glBufferData(GL_ARRAY_BUFFER, normals.size() * sizeof(glm::vec3), &normals[0], GL_STATIC_DRAW);


            TextureCoords textureCoords = texture.getImageTexCoords();
            top = textureCoords.top();
            bottom = textureCoords.bottom();
            left = textureCoords.left();
            right = textureCoords.right();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        if (path == null){
            return ;
        }

        GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context

        if (texture != null) {
            // Enables this texture's target in the current GL context's state.
            texture.enable(gl);  // same as gl.glEnable(texture.getTarget());
            // gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
            // Binds this texture to the current GL context.
            texture.bind(gl);  // same as gl.glBindTexture(texture.getTarget(), texture.getTextureObject());
        }
    }

}
