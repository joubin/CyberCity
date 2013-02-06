package main;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.Animator;

import citygenerator.visualization.JOGLGraph;

import citygenerator.generator.CityGenerator;
import citygenerator.generator.HeightMapGenerator;
import citygenerator.graph.Graph;

public class Main {
	
	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		
		HeightMapGenerator hmg = new HeightMapGenerator(513);
		CityGenerator cityGenerator = new CityGenerator(hmg);
		Graph g = cityGenerator.getRandomChainGraph(	0, 0, 0,				//top left (x,y,z)
														513, 513, 513,		 	//bottom right (x,y,z)
														81, 144, 				//min/max nodes per subgraph
														6, 8,					//min/max subgraphs
														20, 60);				//min/max size of subgraphs

		//System.out.println(g.toString());
	
		///////////////////////////////////////////////////////////////////////////////////
		
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);
        
        Frame frame = new Frame("Graph Generator");
        frame.setSize(300, 300);
        frame.add(canvas);
    	frame.setUndecorated(true);
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	gd.setFullScreenWindow(frame);

        
//        // by default, an AWT Frame doesn't do anything when you click
//        // the close button; this bit of code will terminate the program when
//        // the window is asked to close
//        frame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
        
        JOGLGraph joglGraph = new JOGLGraph(g, cityGenerator.getHeightMap(), 513);
        
        canvas.addGLEventListener(joglGraph);
        canvas.addMouseListener(joglGraph);
        canvas.addMouseWheelListener(joglGraph);
        canvas.addMouseMotionListener(joglGraph);
        
        Animator animator = new Animator(canvas);
        animator.add(canvas);
        animator.start();
	}

}
