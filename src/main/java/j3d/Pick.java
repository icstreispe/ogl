package j3d;

import com.sun.j3d.utils.picking.*;

import com.sun.j3d.utils.universe.SimpleUniverse;

import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;

import javax.swing.*;
import javax.vecmath.*;

import java.awt.event.*;

import java.awt.*;
import java.util.Enumeration;


public class Pick extends MouseAdapter {

    private PickCanvas pickCanvas;


    public Pick()

    {

        JFrame frame = new JFrame("Box and Sphere");

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

        Canvas3D canvas = new Canvas3D(config);

        canvas.setSize(400, 400);

        SimpleUniverse universe = new SimpleUniverse(canvas);

        BranchGroup group = new BranchGroup();


        // create a color cube


        Vector3f vector = new Vector3f(-0.3f, 0.0f, 0.0f);

        Transform3D transform = new Transform3D();

        transform.setTranslation(vector);

        TransformGroup transformGroup = new TransformGroup(transform);

        ColorCube cube = new ColorCube(0.3);

        transformGroup.addChild(cube);

        group.addChild(transformGroup);


        //create a sphere


        Vector3f vector2 = new Vector3f(+0.3f, 0.0f, 0.0f);

        Transform3D transform2 = new Transform3D();

        transform2.setTranslation(vector2);

        TransformGroup transformGroup2 = new TransformGroup(transform2);

        Appearance appearance = new Appearance();

        appearance.setPolygonAttributes(

                new PolygonAttributes(PolygonAttributes.POLYGON_LINE,

                        PolygonAttributes.CULL_BACK,0.0f));

        Sphere sphere = new Sphere(0.3f,appearance);

        transformGroup2.addChild(sphere);

        group.addChild(transformGroup2);


        universe.getViewingPlatform().setNominalViewingTransform();

        universe.addBranchGraph(group);

        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent winEvent) {

                System.exit(0);

            }

        });



        pickCanvas = new PickCanvas(canvas, group);

        pickCanvas.setMode(PickCanvas.BOUNDS);

        canvas.addMouseListener(this);

        frame.add(canvas);

        frame.pack();

        frame.setVisible(true);

    }


    public static void main( String[] args ) {

        new Pick();

    }


    public void mouseClicked(MouseEvent e)

    {

        pickCanvas.setShapeLocation(e);

        PickResult result = pickCanvas.pickClosest();

        if (result == null) {

            System.out.println("Nothing picked");

        } else {

            Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

            Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);

            if (p != null) {

                System.out.println(p.getClass().getName());

            } else if (s != null) {

                System.out.println(s.getClass().getName());

            } else{

                System.out.println("null");

            }

        }

    }

    public void enablePicking(Node node) {

        node.setPickable(true);

        node.setCapability(Node.ENABLE_PICK_REPORTING);

        try {

            Group group = (Group) node;

            for (Enumeration e = group.getAllChildren(); e.hasMoreElements();) {

                enablePicking((Node)e.nextElement());

            }

        }

        catch(ClassCastException e) {

            // if not a group node, there are no children so ignore exception

        }

        try {

            Shape3D shape = (Shape3D) node;

            PickTool.setCapabilities(node, PickTool.INTERSECT_FULL);

            for (Enumeration e = shape.getAllGeometries(); e.hasMoreElements();) {

                Geometry g = (Geometry)e.nextElement();

                g.setCapability(g.ALLOW_INTERSECT);

            }

        }

        catch(ClassCastException e) {

            // not a Shape3D node ignore exception

        }

    }

} // end of class Pick