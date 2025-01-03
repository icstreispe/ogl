/*
 * $RCSfile: OrientedTest.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND
 * REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 *
 * $Revision: 1.5 $
 * $Date: 2007/04/24 18:55:58 $
 * $State: Exp $
 */

package j3d.oriented_shape3d;

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import j3d.Resources;

public class OrientedTest extends Applet {

    // setup font stuff
    private String fontName = "TestFont";
    private String textString = "OrientedShape3D";
    float sl = textString.length();

    // paths to texture image files
    private java.net.URL earthImage = null;
    private java.net.URL stoneImage = null;

    private SimpleUniverse u = null;

    public BranchGroup createSceneGraph() {

	// Create the root of the branch graph
	BranchGroup objRoot = new BranchGroup();

        TransformGroup objScale = new TransformGroup();
        Transform3D textMat = new Transform3D();
        // Assuming uniform size chars, set scale to fit string in view
        textMat.setScale(1.2/sl);
        objScale.setTransform(textMat);



	// Create the transform group node and initialize it to the
	// identity.  Enable the TRANSFORM_WRITE capability so that
	// our behavior code can modify it at runtime.  Add it to the
	// root of the subgraph.
	TransformGroup objTrans = new TransformGroup();
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	objRoot.addChild(objTrans);

	BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

        Appearance apText = new Appearance();
	Material m = new Material();
        m.setLightingEnable(true);
	apText.setMaterial(m);


        Appearance apEarth= new Appearance();
        Material mm = new Material();
        mm.setLightingEnable(true);
	apEarth.setMaterial(mm);

        Appearance apStone = new Appearance();
	apStone.setMaterial(mm);

// create 3D text 
        Font3D f3d = new Font3D(new Font(fontName, Font.PLAIN, 2),
                                new FontExtrusion());
        Text3D txt = new Text3D(f3d, textString, 
             new Point3f( -sl/2.0f, 3.0f, 0.0f));
        OrientedShape3D textShape = new OrientedShape3D();
        textShape.setGeometry(txt);
        textShape.setAppearance(apText);
	textShape.setAlignmentAxis( 0.0f, 1.0f, 0.0f);
        objScale.addChild( textShape );


	// Create a simple shape leaf node, add it to the scene graph.

	Transform3D cubeMat = new Transform3D();
	TransformGroup cubeTrans = new TransformGroup(cubeMat);
	cubeMat.set(new Vector3d(0.9, 0.0, -1.0));
	cubeTrans.setTransform(cubeMat);
	cubeTrans.addChild(new ColorCube(0.3));
        objTrans.addChild(cubeTrans);

        TextureLoader stoneTex = new TextureLoader(stoneImage, new String("RGB"),
                TextureLoader.BY_REFERENCE | TextureLoader.Y_UP, this);
        if (stoneTex != null) apStone.setTexture(stoneTex.getTexture());

 	TextureAttributes texAttr = new TextureAttributes();
 	texAttr.setTextureMode(TextureAttributes.MODULATE);
 	apStone.setTextureAttributes(texAttr);
 

	Transform3D coneMat = new Transform3D();
	TransformGroup coneTrans = new TransformGroup(coneMat);
	coneMat.set(new Vector3d(0.0, 0.0, 0.0));
	coneTrans.setTransform(coneMat);
        coneTrans.addChild(new Cone(.2f, 0.8f, Cone.GENERATE_NORMALS |
                Cone.GENERATE_TEXTURE_COORDS |
                Cone.GENERATE_TEXTURE_COORDS_Y_UP, apStone));
        objTrans.addChild(coneTrans);

        TextureLoader earthTex = new TextureLoader(earthImage, new String("RGB"),
                TextureLoader.BY_REFERENCE | TextureLoader.Y_UP, this);
        if (earthTex != null) apEarth.setTexture(earthTex.getTexture());

 	apEarth.setTextureAttributes(texAttr);

	Transform3D cylinderMat = new Transform3D();
	TransformGroup cylinderTrans = new TransformGroup(cylinderMat);
	cylinderMat.set(new Vector3d(-0.9, 0.5, -1.0));
	cylinderTrans.setTransform(cylinderMat);
        cylinderTrans.addChild(new Cylinder(.35f, 2.0f, Cylinder.GENERATE_NORMALS |
                Cylinder.GENERATE_TEXTURE_COORDS |
                Cylinder.GENERATE_TEXTURE_COORDS_Y_UP, apEarth));
        objTrans.addChild(cylinderTrans);

        objTrans.addChild(objScale);




        // Set up the background
        Color3f bgColor = new Color3f(0.05f, 0.05f, 0.5f);
        Background bgNode = new Background(bgColor);
        bgNode.setApplicationBounds(bounds);
        objRoot.addChild(bgNode);

        // Set up the ambient light
        Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);

        // Set up the directional lights
        Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
        Vector3f light1Direction  = new Vector3f(1.0f, 1.0f, 1.0f);
        Color3f light2Color = new Color3f(1.0f, 1.0f, 0.9f);
        Vector3f light2Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);

        DirectionalLight light1
            = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);
          
        DirectionalLight light2
            = new DirectionalLight(light2Color, light2Direction);
        light2.setInfluencingBounds(bounds);
        objRoot.addChild(light2);

        apText.setMaterial(mm);
	
        // Have Java 3D perform optimizations on this scene graph.
        objRoot.compile();

	return objRoot;
    }

    public OrientedTest() {
    }

    public OrientedTest(java.net.URL earthURL, java.net.URL stoneURL) {
        earthImage = earthURL;
	stoneImage = stoneURL;
    }

    public void init() {
        // the paths to the image files for an applet
        earthImage = Resources.getResource("/j3d/images/earth.jpg");
        if (earthImage == null) {
            System.err.println("/j3d/images/earth.jpg not found");
            System.exit(1);
        }

        stoneImage = Resources.getResource("/j3d/images/stone.jpg");
        if (stoneImage == null) {
            System.err.println("/j3d/images/stone.jpg not found");
            System.exit(1);
        }

	setLayout(new BorderLayout());
        GraphicsConfiguration config =
           SimpleUniverse.getPreferredConfiguration();

        Canvas3D c = new Canvas3D(config);
	add("Center", c);

	// Create a simple scene and attach it to the virtual universe
	BranchGroup scene = createSceneGraph();
	u = new SimpleUniverse(c, 4);

	// add mouse behaviors to ViewingPlatform
	ViewingPlatform viewingPlatform = u.getViewingPlatform();

	// there is a special rotate behavior, so can't use the utility
	// method
	MouseRotateY rotate = new MouseRotateY(MouseRotateY.INVERT_INPUT);
	rotate.setTransformGroup(viewingPlatform.getMultiTransformGroup().
				   getTransformGroup(0));
	BranchGroup rotateBG = new BranchGroup();
	rotateBG.addChild(rotate);
	viewingPlatform.addChild(rotateBG);
	BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	rotate.setSchedulingBounds(bounds);

	MouseZoom zoom =
	    new MouseZoom(c, MouseZoom.INVERT_INPUT);
	zoom.setTransformGroup(viewingPlatform.getMultiTransformGroup().
			  getTransformGroup(1));
	zoom.setSchedulingBounds(bounds);
	BranchGroup zoomBG = new BranchGroup();
	zoomBG.addChild(zoom);
	viewingPlatform.addChild(zoomBG);
	
	MouseTranslate translate =
	    new MouseTranslate(c, MouseTranslate.INVERT_INPUT);
	translate.setTransformGroup(viewingPlatform.getMultiTransformGroup().
				    getTransformGroup(2));
	translate.setSchedulingBounds(bounds);
	BranchGroup translateBG = new BranchGroup();
	translateBG.addChild(translate);
	viewingPlatform.addChild(translateBG);

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        u.getViewingPlatform().setNominalViewingTransform();

	u.addBranchGraph(scene);
    }

    public void destroy() {
	u.cleanup();
    }
  
    //
    // The following allows OrientedTest to be run as an application
    // as well as an applet
    //
    public static void main(String[] args) {
        java.net.URL earthURL = null;
	java.net.URL stoneURL = null;
                
        earthURL = Resources.getResource("/j3d/images/earth.jpg");

        stoneURL = Resources.getResource("/j3d/images/stone.jpg");

	new MainFrame(new OrientedTest(earthURL, stoneURL), 400, 400);
    }
}
