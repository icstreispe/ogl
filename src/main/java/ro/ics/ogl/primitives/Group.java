package ro.ics.ogl.primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X13 on 10.01.2017.
 */
public class Group implements Figure {

    private List<Polygon> faces = new ArrayList<>();
    //private Texture texture;
    private String name;
    private Material material;

    public Group(String basePath, String name) {
        this.name = name;
        //texture = new Texture(basePath + name + "." + Texture.FILE_TYPE);
    }


    @Override
    public void init(GLAutoDrawable drawable) {
        //texture.init(drawable);
        material.init (drawable);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        //texture.display(drawable);

        //gl.glBegin(GL2.GL_POLYGON); //astea tr scoase ca sa se vada textura
        material.display(drawable);

        //TODO
        for (Polygon p : faces) {
            p.display(drawable);
        }
        //gl.glEnd();
    }


    public void setMaterial(Material material) {
        this.material = material;
    }

    public void addFace(Polygon p) {
        faces.add(p);
    }

    public List<Polygon> getFaces() {
        return faces;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        this.name = s;
    }

    public Material getMaterial() {
        return material;
    }
}
