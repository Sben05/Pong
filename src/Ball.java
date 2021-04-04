/**
 * @author: Shreeniket Bendre
 * Project: Pong
 * File: Ball.java
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Ball extends JComponent {
	
	private Ellipse2D.Double circle; 
	private int dx;
	private int dy;
	
	public Ball(int x, int y){
		circle = new Ellipse2D.Double(0,0,15,15);
		this.setSize(new Dimension(16,16));
		this.setLocation(x,y);
		
		dx = 3;
		dy = 7;
	}
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.fill(circle);
	}
	public void setDx(int x) {
		dx = x;
	}
	public void setDy(int y) {
		dy = y;
	}
	public int getDx() {
		return dx;
	}
	public int getDy() {
		return dy;
	}
	public void update () {
		setLocation(getX()+dx, getY()+dy);
	}
	public void reset() {
		setLocation(315, 310);
		
		int rand = (int)(Math.random()*2)+1;
		if (rand == 1) dx = 3;
		else dx = -3;
		
		rand = (int)(Math.random()*2)+1;
		if (rand == 1) dy = 7;
		else dy = -7;
		
		
	}
}
