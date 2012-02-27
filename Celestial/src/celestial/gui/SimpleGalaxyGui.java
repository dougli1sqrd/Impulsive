package celestial.gui;

import celestial.physics.ModifiedEulerMethod;
import celestial.physics.Vector;
import celestial.simulator.Galaxy;
import acm.program.GraphicsProgram;
import acm.graphics.*;
import static java.awt.Color.*;

public class SimpleGalaxyGui extends GraphicsProgram	{

	private Galaxy milky;
	
	private GOval[] planets;
	
	public final static int SIZE = 600;
	public final static double UNIVRSE_SIZE = 5.0E10;
	
	public SimpleGalaxyGui()	{
		
		milky = new Galaxy();
		milky.addNewBody(new Vector(-2E10, 0, 0), new Vector(0, 500, 0), 5.98E24, 6.37E6);
		milky.addNewBody(new Vector(0, 0, 0), new Vector(0, -20, 0), 2.032E26, 6.95E8);
		milky.addNewBody(new Vector(3E9, 0, 0), new Vector(0, -2100, 0), 6.0E23, 6.0E5);
		milky.addNewBody(new Vector(-5E8, 0, 0), new Vector(0, 7200, 0), 3.0E20, 6000);
	}
	
	public static void main(String[] args)	{
		
		new SimpleGalaxyGui().start();
		
	}
	
	public void run()	{
		
		ModifiedEulerMethod sim = new ModifiedEulerMethod(milky);
		while(true)	{
			
			milky.simulateStep(sim);
			updatePos();
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void init()	{
		
		planets = new GOval[(int)milky.galaxySize()];
		for(int i=0; i<planets.length; i++)	{
			
			double getx = milky.getCelestialBody(i).getPosition().getComponent(0);
			double gety = milky.getCelestialBody(i).getPosition().getComponent(1);
			int[] position = realToPixTransform(getx, gety);
			planets[i] = new GOval(position[0], position[1], 5, 5);
			planets[i].setColor(BLACK);
			planets[i].setFilled(true);
			planets[i].setFillColor(BLACK);
			planets[i].setVisible(true);
			add(planets[i]);
		}
		
		setSize(SIZE, SIZE);
		setBackground(WHITE);
	}
	
	public int[] realToPixTransform(double x, double y)	{
		
		double mperpix = UNIVRSE_SIZE/SIZE; //meters per pixel, so actual dist / m/pix = pixels
		int pixx = (int)(x/mperpix) + SIZE/2;
		int pixy = (int)(SIZE/2 - y/mperpix);
		return new int[] {pixx, pixy};
	}
	
	public void updatePos()	{
		
		
		for(int i=0; i<planets.length; i++)	{
			
			double getx = milky.getCelestialBody(i).getPosition().getComponent(0);
			double gety = milky.getCelestialBody(i).getPosition().getComponent(1);
			int[] position = realToPixTransform(getx, gety);
			planets[i].setLocation(position[0], position[1]);
		}
	}
}
