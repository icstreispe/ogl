package ro.ics.ogl.primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;

import static com.jogamp.opengl.GL.GL_FRONT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;

/**
 * Created by X13 on 31.12.2016.
 */
public class Material implements Figure {
    //TODO de facut dupa fisierele .mtl
    public Color ambientColor;  //Ka
    public Texture ambientTexture;  //map_Ka
    public Color diffuseColor;  //Kd
    public Texture diffuseTexture;  //map_Kd
    public Color specularColor;  //Ks
    public String specularTexture;  //map_Ks
    public float specularExp;  //Ns
    public float refractionIndex;  //Ni
    public float transparency;  //Tr or d
    public int illumModel;     //illum

    private String name;


    public Material(String name) {
        this.name = name;
        diffuseTexture = new Texture(null);
    }


    @Override
    public void init(GLAutoDrawable drawable) {
        //texture.init(drawable);
        diffuseTexture.init(drawable);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();


        //gl.glEnable(GL_COLOR_MATERIAL);    //TODO unde ar tr sa fie?

        if (ambientColor != null) {
            gl.glMaterialfv(GL_FRONT, GL_AMBIENT, ambientColor.toArray(), 0);
        }
        if (diffuseColor != null) {
            gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, diffuseColor.toArray(), 0);
            //cand vrem sa vedem culorile
            // gl.glColor3fv(diffuseColor.toArray(), 0);
        }
        if (specularColor != null) {
            gl.glMaterialfv(GL_FRONT, GL_SPECULAR, specularColor.toArray(), 0);
        }

        gl.glMaterialf(GL_FRONT, GL_SHININESS, specularExp);   //ok

        //gl.glMaterialf(GL.GL_FRONT, GL_EMISSION, refractionIndex);  //nope

        diffuseTexture.display(drawable);  //TODO
        if (ambientTexture != null) {
            ambientTexture.display(drawable);  //TODO
        }
        //specularTexture.display(drawable);  //TODO
    }
}