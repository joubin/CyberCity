package main;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.FPSAnimator;

import citygenerator.visualization.JOGLGraph;

import citygenerator.generator.CityGenerator;
import citygenerator.generator.HeightMapGenerator;
import citygenerator.graph.Graph;

public class Main {
	
	static final int SIZE = 513;
	
	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		
		HeightMapGenerator hmg = new HeightMapGenerator(SIZE);
		CityGenerator cityGenerator = new CityGenerator(hmg);
		Graph g = cityGenerator.getRandomChainGraph(	0, 0, 0,				//top left (x,y,z)
														SIZE, SIZE, SIZE,		 	//bottom right (x,y,z)
														81, 144, 				//min/max nodes per subgraph
														10, 12,					//min/max subgraphs
														20, 60);				//min/max size of subgraphs

		//System.out.println(g.toString());
	
		///////////////////////////////////////////////////////////////////////////////////
		
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);
        
        Frame frame = new Frame("Graph Generator");
        frame.add(canvas);

    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	
    	frame.setSize(800, 600);
    	frame.setVisible(true);
    	
    	//frame.setUndecorated(true);
    	//gd.setFullScreenWindow(frame);

        
        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        JOGLGraph joglGraph = new JOGLGraph(g, cityGenerator.getHeightMap(), SIZE);
        
        canvas.addGLEventListener(joglGraph);
        canvas.addMouseListener(joglGraph);
        canvas.addMouseWheelListener(joglGraph);
        canvas.addMouseMotionListener(joglGraph);
        
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.add(canvas);
        animator.start();
	}

}
