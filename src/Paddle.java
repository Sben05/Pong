/**
 * @author: Shreeniket Bendre
 * Project: Pong
 * File: Paddle.java
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Paddle extends JComponent{

	private Rectangle2D.Double main;

	private int dy;

	public Paddle(int x, int y){
		main = new Rectangle2D.Double(0,0,10,80);

		this.setSize(new Dimension(11,81));
		this.setLocation(x,y);
		dy = 0;

	}
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
	
		g2.setColor(Color.black);
		g2.fill(main);
	}
	public void setDy(int y) {
		dy = y*6;
	}
	public int getDy(){
		return dy;
	}
	public void update () {
		setLocation(getX(),getY()+dy);
	}
}
