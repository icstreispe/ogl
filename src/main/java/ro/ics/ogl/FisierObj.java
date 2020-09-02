package ro.ics.ogl;

import ro.ics.ogl.primitives.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by X13 on 01.01.2017.
 */
public class FisierObj {


    //private static final float ZOOM = 1;

    private static final int SHADES = 256;
    private static Color[] colors = new Color[SHADES];

    public static List<Obiect> readFile(String path, float zoom) {

        List l = new ArrayList();
        try {
            Obiect o = new Obiect(null);
            l.add(o);   //for now

            Group g = null;
            MyColor currentColor = new MyColor();
            String basePath = path.substring(0, path.lastIndexOf("/"))+"/";
            Material currentMaterial = new Material(null);  //fake

            BufferedReader br = new BufferedReader(new InputStreamReader(FisierObj.class.getResourceAsStream(path)));
            try {
                String line = null;

                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split("\\s+");
                    if (tokens == null || tokens.length == 0
                            || tokens[0].equals("")
                            || tokens[0].equals("#")) {
                        continue;

                    } else if (tokens[0].equalsIgnoreCase("o")) {   //common namespace pt toate elementele?
                        //o = new Obiect(tokens[1]);
                        //l.add(o);

                    } else if (tokens[0].equalsIgnoreCase("g")) {

                        g = new Group(basePath, tokens[1]);
                        if (tokens.length > 1) {
                            g.setName(tokens[1]);
                        }
                        o.getGroups().add(g);

                    } else if (tokens[0].equalsIgnoreCase("v")) {
                        float x = Float.parseFloat(tokens[1]) / zoom;
                        float y = Float.parseFloat(tokens[2]) / zoom;
                        float z = Float.parseFloat(tokens[3]) / zoom;
                        Point p = new Point(x, y, z);
                        o.addPoint(p);
                        setMaterial(g, currentMaterial);

                    } else if (tokens[0].equalsIgnoreCase("vt")) {
                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        float z = 0;
                        if (tokens.length >= 4) {
                            z = Float.parseFloat(tokens[3]);  //usually 0
                        }
                        Point p = new Point(x, y, z);
                        o.addPointTex(p);
                        setMaterial(g, currentMaterial);

                    } else if (tokens[0].equalsIgnoreCase("vn")) {
                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        float z = Float.parseFloat(tokens[3]);
                        Point p = new Point(x, y, z);

                        o.getPointsNorm().add(p);
                        setMaterial(g, currentMaterial);

                    } else if (tokens[0].equalsIgnoreCase("f") && tokens.length == 4) {

                        Point p1 = getPoint(o.getPoints(), tokens[1]);
                        Point p2 = getPoint(o.getPoints(), tokens[2]);
                        Point p3 = getPoint(o.getPoints(), tokens[3]);

                        modifyColor(currentColor);
                        Triangle t = new Triangle(p1, p2, p3);//, currentColor.color);
                        g.addFace(t);

                        if (isTexture(tokens[1])) {
                            Point pt1 = getPointTex(o.getPointsTex(), tokens[1]);
                            Point pt2 = getPointTex(o.getPointsTex(), tokens[2]);
                            Point pt3 = getPointTex(o.getPointsTex(), tokens[3]);

                            t.setTexture(pt1, pt2, pt3);
                        }
                        setMaterial(g, currentMaterial);

                    } else if (tokens[0].equalsIgnoreCase("f") && tokens.length == 5) {
                        Point p1 = getPoint(o.getPoints(), tokens[1]);
                        Point p2 = getPoint(o.getPoints(), tokens[2]);
                        Point p3 = getPoint(o.getPoints(), tokens[3]);
                        Point p4 = getPoint(o.getPoints(), tokens[4]);

                        modifyColor(currentColor);
                        Quad t = new Quad(p1, p2, p3, p4);//, currentColor.color);
                        g.addFace(t);

                        if (isTexture(tokens[1])) {
                            Point pt1 = getPointTex(o.getPointsTex(), tokens[1]);
                            Point pt2 = getPointTex(o.getPointsTex(), tokens[2]);
                            Point pt3 = getPointTex(o.getPointsTex(), tokens[3]);
                            Point pt4 = getPointTex(o.getPointsTex(), tokens[4]);

                            t.setTexture(pt1, pt2, pt3, pt4);
                        }
                        setMaterial(g, currentMaterial);

                    } else if (tokens[0].equalsIgnoreCase("f")) {   //aici deocamdata fara textura

                        Point[] pts = new Point[tokens.length - 1];
                        for (int i = 1; i < tokens.length; i++) {
                            Point p = getPoint(o.getPoints(), tokens[i]);
                            pts[i - 1] = p;
                        }

                        modifyColor(currentColor);
                        Polygon q = new Polygon(pts);   //currentColor.color,
                        g.addFace(q);
                        setMaterial(g, currentMaterial);

                    } else if (tokens[0].equalsIgnoreCase("mtllib")) {
                        loadMtl(basePath , tokens[1], o);

                    } else if (tokens[0].equalsIgnoreCase("usemtl")) {
                        currentMaterial = getMaterial(o, tokens[1]);

                    } else if (tokens[0].equalsIgnoreCase("s")){
                        //TODO smooth shading 1 or off

                        setMaterial(g, currentMaterial);
                    } else if (tokens[0].equalsIgnoreCase("l")){
                        //TODO line
                    } else {
                        System.out.println ("UNKNOWN: " + tokens[0]);
                    }

                }
            } finally {
                br.close();
            }
            return l;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //set only when added new elements
    private static void setMaterial(Group g, Material currentMaterial) {
        if (g != null) {
            g.setMaterial(currentMaterial);
        }
    }


    private static Material getMaterial(Obiect o, String name) {
        Material currentMaterial = o.getMaterials().get(name);
        if (currentMaterial == null){   //ar fi tr sa fie incarcat din mtl dinainte
            currentMaterial = new Material(null);
            o.getMaterials().put(name, currentMaterial);
        }
        return currentMaterial;
    }

    private static void loadMtl(String basePath, String name, Obiect o) {

        if (o.getLibs().get(name) != null){
            return ;
        }
        o.getLibs().put(name, name);    //marks lib as loaded


        try {
            Material m = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(FisierObj.class.getResourceAsStream(basePath+name)));
            try {
                String line = null;

                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split("\\s+");

                    if (tokens == null || tokens.length == 0
                            || tokens[0].equals("")
                            || tokens[0].equals("#")) {
                        continue;
                    } else if (tokens[0].equalsIgnoreCase("newmtl")){
                        m = new Material(tokens[1]);
                        o.getMaterials ().put(tokens[1], m);

                    } else if (tokens[0].equalsIgnoreCase("Kd")){
                        m.diffuseColor = new Color (Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3]), 1f);
                    } else if (tokens[0].equalsIgnoreCase("Ka")){
                        m.ambientColor = new Color (Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3]), 1f);
                    } else if (tokens[0].equalsIgnoreCase("Ks")){
                        m.specularColor = new Color (Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3]), 1f);

                    } else if (tokens[0].equalsIgnoreCase("Ns")){
                        m.specularExp = Float.parseFloat(tokens[1]);

                    } else if (tokens[0].equalsIgnoreCase("Ni")){
                        m.refractionIndex = Float.parseFloat(tokens[1]);

                    } else if (tokens[0].equalsIgnoreCase("map_Ka")){
                        m.ambientTexture = loadTexture(o, basePath + tokens[1]);   //TODO
                    } else if (tokens[0].equalsIgnoreCase("map_Kd")){
                        m.diffuseTexture = loadTexture(o, basePath + tokens[1]);      //TODO



                    } else if (tokens[0].equalsIgnoreCase("d")){
                        m.transparency = Float.parseFloat(tokens[1]);

                    } else if (tokens[0].equalsIgnoreCase("illum")){
                        m.illumModel = Integer.parseInt(tokens[1]);
                    } else {
                        System.out.println ("UNKNOWN: " + tokens[0]);
                    }
                }

            } finally {
                br.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Texture loadTexture (Obiect o, String path){
        Texture t = o.getTextures().get(path);
        if (t != null){
            return t;
        }
        t = new Texture (path);
        o.getTextures().put(path, t);
        return t;
    }

    private static boolean isTexture(String s) {
        return s.contains("/");
    }

    private static Point getPoint(List<Point> list, String token) {
        int i = getPointIndex(token);
        return list.get(i);
    }

    private static Point getPointTex(List<Point> list, String token) {
        int i = getPointTexIndex(token);
        return list.get(i);
    }

    private static int getPointIndex(String token) {
        if (!token.contains("/")) {
            return Integer.parseInt(token) - 1;
        }
        return Integer.parseInt(token.substring(0, token.indexOf('/'))) - 1;
    }

    private static int getPointTexIndex(String token) {
        if (!token.contains("/")) {
            return -1;
        }
        String subToken = token.substring(token.indexOf('/') + 1);
        if (!subToken.contains("/")) {
            return Integer.parseInt(token.substring(token.indexOf('/') + 1)) - 1;
        }
        return Integer.parseInt(subToken.substring(subToken.indexOf('/') + 1)) - 1;
    }

    public static void main(String args[]) {
        System.out.print("cuc/123".indexOf("/"));
    }

    private static void modifyColor(MyColor c) {
        c.index++;
        c.index %= SHADES;//Color.values().length;
        if (colors[c.index] == null) {
            float f = ((float) c.index) / SHADES;
            colors[c.index] = new Color(f, f, f, 1f);
        }
        c.color = colors[c.index];
    }

    private static class MyColor {
        int index = -1;
        Color color;
    }
}
