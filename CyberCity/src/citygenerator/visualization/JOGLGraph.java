package citygenerator.visualization;

import citygenerator.graph.CityNode;
import citygenerator.graph.Graph;
import citygenerator.graph.Point3D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class JOGLGraph implements GLEventListener, MouseListener,
		MouseWheelListener, MouseMotionListener {

	private Point3D size; //TODO
	private ArrayList<Point3D> points = new ArrayList<Point3D>();
	private HashMap<Point3D, ArrayList<Point3D>> edges = new HashMap<Point3D, ArrayList<Point3D>>();

	private float widthHeightRatio = 1F;
	private float distance = 500F;

	private GLU glu = new GLU();

	private Integer mouseX = 0, mouseY = 0;
	private float phi = 60;
	private float theta = 0;

	private double[][] heightMap;
	private double dataSize;

	public JOGLGraph(Graph g, double[][] map, int dataSize) {

		heightMap = map;
		this.dataSize = dataSize;
		
		this.size = g.getSize();
		for (CityNode n : g.getAllNodes()) {
			double x=n.getCoordinates().x, y=n.getCoordinates().y, z=n.getCoordinates().z;
			points.add(new Point3D(x, y, z));

			ArrayList<Point3D> adjacentPoints = new ArrayList<Point3D>();
			for (CityNode adj : n.getAdjacentNodes()) {
				double ax=adj.getCoordinates().x, ay=adj.getCoordinates().y, az=adj.getCoordinates().z;
				adjacentPoints.add(new Point3D(ax, ay, az));
			}

			edges.put(new Point3D(x, y, z), adjacentPoints);

		}
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		drawable.getGL().setSwapInterval(1); // VSync
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		update();
		render(drawable);
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		widthHeightRatio = (float) width / (float) height;

	}

	private void render(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		setCamera(gl, glu);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glPointSize(3.0F);

		//Draw axes
		//X
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glColor3f(.7F, .0F, .0F);
		gl.glVertex3i(-10000, 0, 0);
		gl.glVertex3i(10000, 0, 0);
		gl.glEnd();
		
		//Y
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glColor3f(.0F, .7F, .0F);
		gl.glVertex3i(0, -10000, 0);
		gl.glVertex3i(0, 10000, 0);
		gl.glEnd();
		
		//Z
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glColor3f(.0F, .0F, .8F);
		gl.glVertex3i(0, 0, -10000);
		gl.glVertex3i(0, 0, 10000);
		gl.glEnd();

		// Draw heightmap
		gl.glColor3f(.3F, .3F, .3F);
		for (int i = 0; i < dataSize; i++) {
			gl.glBegin(GL.GL_LINE_STRIP);
			for (int j = 0; j < dataSize; j++) {
				gl.glVertex3d(-256.0 + 512.0 * (i / dataSize), heightMap[i][j],
						-256.0 + 512.0 * (j / dataSize));
			}
			gl.glEnd();
		}

		for (int j = 0; j < dataSize; j++) {
			gl.glBegin(GL.GL_LINE_STRIP);
			for (int i = 0; i < dataSize; i++) {
				gl.glVertex3d( -256.0 + 512.0 * (i / dataSize), heightMap[i][j],
						-256.0 + 512.0 * (j / dataSize));
			}
			gl.glEnd();
		}
		
		//Draw nodes
		gl.glBegin(GL.GL_POINTS);
		gl.glColor3f(0, 1, 0);

		for (Point3D p : points) {
			gl.glVertex3d(p.x - 256, p.y, p.z - 256);
		}
		gl.glEnd();

		//Draw Edges
		gl.glBegin(GL.GL_LINES);
		for (Point3D from : edges.keySet()) {
			for (Point3D to : edges.get(from)) {
				gl.glVertex3d(from.x - 256, from.y, from.z - 256);
				gl.glVertex3d(to.x - 256, to.y, to.z - 256);
			}
		}
		gl.glEnd();
	}

	private void update() {

	}

	private void setCamera(GL2 gl, GLU glu) {
		// Change to projection matrix.
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		// Perspective.
		glu.gluPerspective(45, widthHeightRatio, 1, 100000);

		double radPhi = phi * Math.PI / 180.0;
		double radTheta = theta * Math.PI / 180.0;

		glu.gluLookAt(distance * Math.cos(radTheta) * Math.sin(radPhi),
				distance * Math.cos(radPhi),
				distance * Math.sin(radPhi) * Math.sin(radTheta), 0, 0, 0, 0,
				1, 0);

		// Change back to model view matrix.
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseX = null;
		mouseY = null;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		float dModifier = 25F * e.getWheelRotation();

		if (distance + dModifier > 0)
			distance += dModifier;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int dx = mouseX - e.getX();
		int dy = mouseY - e.getY();

		phi += .2 * (float) dy;

		if (phi < 5F)
			phi = 5F;
		else if (phi > 175F)
			phi = 175F;

		theta -= 0.2F * (float) dx;

		// if(theta < -170F)
		// theta = -170F;
		// else if (theta > 170F)
		// theta = 170F;

		mouseX = e.getX();
		mouseY = e.getY();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
