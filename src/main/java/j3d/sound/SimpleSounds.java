/*
 * $RCSfile: SimpleSounds.java,v $
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
 * $Revision: 1.3 $
 * $Date: 2007/02/09 17:21:52 $
 * $State: Exp $
 */

package j3d.sound;

import java.applet.Applet;
import java.net.URL;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.swing.JOptionPane;
import javax.vecmath.*;
import j3d.Resources;

/*
 * This Java3D program:
 *    Creates an instance of the JavaSoundMixer AudioDevice, initializing it
 *         and attaching it to the PhysicalEnvironment by using
 *         SimpleUniverse.
 *    Creates one cached Background and two cached Vector sound sources.
 *    Creates and executes a custom behavior (SimpleSoundsBehavior) that
 *         starts the sound playing and transforms the PointSound source
 *         by modifying the TransformGroup that contains the Sound nodes.
 *
 * Usage: 
 *    java SimpleSounds [URLpath [name1 [name2 [name2]]]]
 *
 * The first optional command line parameter is the URL path to directory
 *       containing "file:" or "http:" and then directory path string.
 *    If you are using the suppled default sound files in the same directory
 *       as this test program then only the URLpath need be supplied on the 
 *       command line.
 *    If this parameter is not included then the current path to the directory
 *       this program is running in is used for an application
 *       and the codebase is used for an applet.
 * The second thru fourth optional command line parameters are sound file names
 *    If not given, the default file names are:
 *       techno_machine.au
 *       hello_universe.au
 *       roar.au
 *    that correspond to the 3 'voice' quality, 8-bit, u-law, 8-kHz samples
 *    included in the same directory as this test program.
 * 
 *    Java Sound engine has been advertised to support the following 8- and 16-
 *    bit, linear and u-law, mono and stereo sound sample file formats: AU,
 *    AIFF, WAV, and PCM.  Non-cached MIDI and RMF files are also supported.
 *    Neither compressed formats (DVI, GSM, MOD) nor A-law formated files are
 *    supported at this time, but they will be converted.
 */

public class SimpleSounds extends Applet {

    private static URL[] url = new URL[3];
    private SimpleUniverse u = null;

    public BranchGroup createSceneGraph() {
	// Create the root of the subgraph
	BranchGroup objRoot = new BranchGroup();

	// Create the transform group node and initialize it to the identity.
	// Enable the TRANSFORM_WRITE capability so that our behavior code
	// can modify it at runtime.  Add it to the root of the subgraph.
	TransformGroup objTrans = new TransformGroup();
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	objRoot.addChild(objTrans);

	// Create a simple shape leaf node and add it into the scene graph.
	objTrans.addChild(new ColorCube(0.4));

	// Create a new Behavior object that will perform the desired
	// operation on the specified transform object and add it into the
	// scene graph.
	Transform3D yAxis = new Transform3D();
        Alpha rotation = new Alpha(-1, Alpha.INCREASING_ENABLE,
                                   0, 0,
                                   20000, 0, 0,
                                   0, 0, 0);
        RotationInterpolator rotator =
            new RotationInterpolator(rotation,
                                     objTrans, yAxis,
                                     0.0f, (float) Math.PI*2.0f);
	BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	rotator.setSchedulingBounds(bounds);
	objTrans.addChild(rotator);
        /*
         * Create a sound node and add it to the scene graph
         */
        BackgroundSound sound1 = new BackgroundSound();
        PointSound sound2 = new PointSound();
        PointSound sound3 = new PointSound();
        sound1.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        sound1.setCapability(PointSound.ALLOW_INITIAL_GAIN_WRITE);
        sound1.setCapability(PointSound.ALLOW_SOUND_DATA_WRITE);
        sound1.setCapability(PointSound.ALLOW_SCHEDULING_BOUNDS_WRITE);
        sound1.setCapability(PointSound.ALLOW_CONT_PLAY_WRITE);
        sound1.setCapability(PointSound.ALLOW_RELEASE_WRITE);
        sound1.setCapability(PointSound.ALLOW_DURATION_READ);
        sound1.setCapability(PointSound.ALLOW_IS_PLAYING_READ);
        sound1.setCapability(PointSound.ALLOW_LOOP_WRITE);
        sound2.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        sound2.setCapability(PointSound.ALLOW_INITIAL_GAIN_WRITE);
        sound2.setCapability(PointSound.ALLOW_SOUND_DATA_WRITE);
        sound2.setCapability(PointSound.ALLOW_SCHEDULING_BOUNDS_WRITE);
        sound2.setCapability(PointSound.ALLOW_CONT_PLAY_WRITE);
        sound2.setCapability(PointSound.ALLOW_RELEASE_WRITE);
        sound2.setCapability(PointSound.ALLOW_DURATION_READ);
        sound2.setCapability(PointSound.ALLOW_IS_PLAYING_READ);
        sound2.setCapability(PointSound.ALLOW_POSITION_WRITE);
        sound2.setCapability(PointSound.ALLOW_LOOP_WRITE);
        sound3.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        sound3.setCapability(PointSound.ALLOW_INITIAL_GAIN_WRITE);
        sound3.setCapability(PointSound.ALLOW_SOUND_DATA_WRITE);
        sound3.setCapability(PointSound.ALLOW_SCHEDULING_BOUNDS_WRITE);
        sound3.setCapability(PointSound.ALLOW_CONT_PLAY_WRITE);
        sound3.setCapability(PointSound.ALLOW_RELEASE_WRITE);
        sound3.setCapability(PointSound.ALLOW_DURATION_READ);
        sound3.setCapability(PointSound.ALLOW_IS_PLAYING_READ);
        sound3.setCapability(PointSound.ALLOW_POSITION_WRITE);

	BoundingSphere soundBounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        sound1.setSchedulingBounds(soundBounds);
        sound2.setSchedulingBounds(soundBounds);
        sound3.setSchedulingBounds(soundBounds);
	objTrans.addChild(sound1);
	objTrans.addChild(sound2);
	objTrans.addChild(sound3);


        /*
	 * Create a new Behavior object that will play the sound
	 */
	SimpleSoundsBehavior player = new SimpleSoundsBehavior(
                  sound1, sound2, sound3, 
                  url[0], url[1], url[2], soundBounds);
	player.setSchedulingBounds(soundBounds);
	objTrans.addChild(player);

        // Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

	return objRoot;
    }

    public SimpleSounds() {
    }

    public void init() {

	setLayout(new BorderLayout());
        GraphicsConfiguration config =
           SimpleUniverse.getPreferredConfiguration();

        Canvas3D c = new Canvas3D(config);
	add("Center", c);

        url[0] = Resources.getResource("resources/audio/techno_machine.au");
        if (url == null) {
            System.err.println("resources/audio/techno_machine.au not found");
            System.exit(1);
        }
            
         url[1] = Resources.getResource("resources/audio/hello_universe.au");
        if (url == null) {
            System.err.println("resources/audio/hello_universe.au not found");
            System.exit(1);
        }
         
        url[2] = Resources.getResource("resources/audio/roar.au");
        if (url == null) {
            System.err.println("resources/audio/roar.au not found");
            System.exit(1);
        }         
                                      
	/*
         * Create a simple scene and attach it to the virtual universe
         */
	u = new SimpleUniverse(c);
        AudioDevice audioDev = u.getViewer().createAudioDevice();
	BranchGroup scene = createSceneGraph();

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        u.getViewingPlatform().setNominalViewingTransform();

	u.addBranchGraph(scene);
        
        JOptionPane.showMessageDialog(this,
                ("This program is still a work in progress.\n" +
                "Please check back in Java 3D 1.5.\n"),
                "Incomplete Work",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void destroy() {
	u.cleanup();
    }

    /*
     * The following allows SimpleSounds to be run as an application
     * as well as an applet
     */
    public static void main(String[] args) {
	new MainFrame(new SimpleSounds(), args, 256, 256);
    }
}
