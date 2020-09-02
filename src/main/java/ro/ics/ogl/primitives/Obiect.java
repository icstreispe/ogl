package ro.ics.ogl.primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import ro.ics.ogl.Figure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by X13 on 10.01.2017.
 */
public class Obiect implements Figure {

    private String name;
    private List<Point> points = new ArrayList<>();
    private List<Point> pointsTex = new ArrayList<>();
    private List<Point> pointsNorm = new ArrayList<>();

    private Map<String, String> libs = new HashMap<>();    //mtl libs loaded
    private List<Group> groups = new ArrayList<>();
    private Map<String, Texture> textures = new HashMap<>();    //all textures

    private Map<String, Material> materials = new HashMap<>();      //all materials


    public Obiect(String s) {
        this.name = s;
    }


    @Override
    public void init(GLAutoDrawable drawable) {

        for (Group p : groups) {
            p.init(drawable);
        }
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glColor3fv(Color.WHITE.toArray(), 0);  //temporar

        //TODO
        for (Group p : groups) {
            p.display(drawable);
        }
    }


    public void addPoint(Point p) {
        points.add(p);
    }

    public void addPointTex(Point p) {
        pointsTex.add(p);
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Point> getPointsTex() {
        return pointsTex;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Map<String, Material> getMaterials() {
        return materials;
    }

    public Map<String, Texture> getTextures() {
        return textures;
    }

    public List<Point> getPointsNorm() {
        return pointsNorm;
    }

    public Map<String, String> getLibs() {
        return libs;
    }
}
