/**
 * @author: Shreeniket Bendre
 * Project: Pong
 * File: PongMain.java
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JLabel;

public class PongMain extends JFrame implements ActionListener{
	boolean started = false;

	private Paddle paddleA;
	private Paddle paddleB;
	private Ball ball;
	private JLabel scoreLeft, scoreRight;

	private int scoreA, scoreB;


	public PongMain() {
		setTitle("Pong");
		//getContentPane().setBackground(Color.black);

		setBounds(100,100,650,649);
		setLayout(null);
		setResizable(false);

		paddleA = new Paddle(6,276);
		paddleB = new Paddle(620,276);
		ball = new Ball(315,310);
		
		scoreLeft = new JLabel("0");
		scoreLeft.setBounds(185, 0, 50, 50);
		scoreLeft.setFont(new Font("Serif", Font.PLAIN, 50));
		
		scoreRight = new JLabel("0");
		scoreRight.setBounds(425,0, 50, 50);
		scoreRight.setFont(new Font("Serif", Font.PLAIN, 50));
		
		scoreA = 0;
		scoreB = 0;

		add(paddleA);
		add(paddleB);
		add(ball);
		add(scoreLeft);
		add(scoreRight);
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_UP && paddleB.getY()-20>getContentPane().getY()) {
					paddleB.setDy(-1);
				}
				else if (e.getKeyCode() == e.VK_DOWN && paddleB.getY()+3+paddleB.getHeight()<getContentPane().getY()+getContentPane().getHeight()) {
					paddleB.setDy(1);
				}
				else if(e.getKeyCode() == e.VK_S && paddleA.getY()+3+paddleA.getHeight()<getContentPane().getY()+getContentPane().getHeight()) {
					paddleA.setDy(1);

				}
				else if(e.getKeyCode() == e.VK_W && paddleA.getY()-20>getContentPane().getY()) {
					paddleA.setDy(-1);
					
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

				if(e.getKeyCode() == e.VK_DOWN && paddleB.getY()+paddleB.getHeight()+3<getContentPane().getY()+getContentPane().getHeight())
					paddleB.setDy(0);

				else if(e.getKeyCode() == e.VK_UP && paddleB.getY()-3>getContentPane().getY())
					paddleB.setDy(0);

				else if(e.getKeyCode() == e.VK_S && paddleA.getY()+paddleA.getHeight()+3<getContentPane().getY()+getContentPane().getHeight())
					paddleA.setDy(0);

				else if(e.getKeyCode() == e.VK_W && paddleA.getY()-3>getContentPane().getY())
					paddleA.setDy(0);

			}

		});

		Timer time = new Timer(2,this);
		time.start();

		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (paddleA.getX()-3<getContentPane().getX() ||
				paddleA.getX()+3+paddleA.getWidth()>getContentPane().getX()+getContentPane().getWidth() ||
				paddleA.getY()+3+paddleA.getHeight()>getContentPane().getY()+getContentPane().getHeight() ||
				paddleA.getY()-3<getContentPane().getY()) {
			paddleA.setLocation(paddleA.getLocation().x, paddleA.getLocation().y-paddleA.getDy());
			paddleA.setDy(0);
		}

		if (paddleB.getX()-3<getContentPane().getX() ||
				paddleB.getX()+3+paddleB.getWidth()>getContentPane().getX()+getContentPane().getWidth() ||
				paddleB.getY()+3+paddleB.getHeight()>getContentPane().getY()+getContentPane().getHeight() ||
				paddleB.getY()-3<getContentPane().getY()) {
			paddleB.setLocation(paddleB.getLocation().x, paddleB.getLocation().y-paddleB.getDy());
			paddleB.setDy(0);
		}
		if (paddleA.getX()+3<getContentPane().getX()||
				paddleA.getX()-3+paddleA.getWidth()>getContentPane().getX()+getContentPane().getWidth() ||
				paddleA.getY()-3+paddleA.getHeight()>getContentPane().getY()+getContentPane().getHeight() ||
				paddleA.getY()+3<getContentPane().getY()) {
			//paddleA.setLocation(10,10);
		}
		if (paddleB.getX()+3<getContentPane().getX()||
				paddleB.getX()-3+paddleB.getWidth()>getContentPane().getX()+getContentPane().getWidth() ||
				paddleB.getY()-3+paddleB.getHeight()>getContentPane().getY()+getContentPane().getHeight() ||
				paddleB.getY()+3<getContentPane().getY()) {
			//paddleB.setLocation(300,300);
		}
		paddleA.update();
		paddleB.update();

		if (ball.getX()<getContentPane().getX() || 
				ball.getX()+ball.getWidth()>getContentPane().getX()+getContentPane().getWidth()) {
			if (ball.getX() >= (getContentPane().getX()+getContentPane().getWidth())/2) {
				scoreA++;
			}
			else {
				scoreB++;
			}
			scoreLeft.setText(Integer.toString(scoreA));
			scoreRight.setText(Integer.toString(scoreB));
		
			if (scoreA == 10) {
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(null,"Game Over! The Winner is Player W.A.S.D.");
				System.exit(0);
			}
			else if (scoreB == 10){
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(null,"Game Over! The Winner is Player Arrow Keys.");
				System.exit(0);
			}
			ball.setDx(0);
			ball.setDy(0);
			ball.reset();
		}
		else if (ball.getY()<getContentPane().getY() || 
				ball.getY()+ball.getHeight()>getContentPane().getY()+getContentPane().getHeight()) {
			ball.setDy(-ball.getDy());
		}


		char result = isTouching();
		if (result != 'N') {
			ball.setDx(-ball.getDx());
		}

		ball.update();
		repaint();
	}


	public char isTouching() {
		char returnChar = 'N';		

		if ((paddleB.getY()<(ball.getY()+ball.getHeight())) 
				&& (paddleB.getX()<ball.getX()+ball.getWidth() && !(paddleB.getX()+paddleB.getWidth()<ball.getX()))
				&& (paddleB.getY()+paddleB.getHeight()>ball.getY())) {
			returnChar = 'B';
		}
		else if ((paddleB.getY()+paddleB.getHeight()>(ball.getY()))
				&& (paddleB.getX()<ball.getX()+ball.getWidth() && !(paddleB.getX()+paddleB.getWidth()<ball.getX()))
				&& (paddleB.getY()<ball.getY()+ball.getHeight())) {
			returnChar = 'B';
		}
		else if((paddleB.getX()<(ball.getX()+ball.getWidth()))
				&& (paddleB.getY()<ball.getY()+ball.getHeight() && !(paddleB.getY()+paddleB.getHeight()<ball.getY()))
				&& (paddleB.getX()+paddleB.getWidth()>ball.getX())){
			returnChar = 'B';
		}
		else if((paddleB.getX()+paddleB.getWidth()>(ball.getX()))
				&&(paddleB.getY()<ball.getY()+ball.getHeight() && (paddleB.getY()+paddleB.getHeight()>ball.getY()))
				&& (paddleB.getX()<ball.getX()+ball.getWidth())){
			returnChar = 'B';
		}


		if ((paddleA.getY()<(ball.getY()+ball.getHeight())) 
				&& (paddleA.getX()<ball.getX()+ball.getWidth() && !(paddleA.getX()+paddleA.getWidth()<ball.getX()))
				&& (paddleA.getY()+paddleA.getHeight()>ball.getY())) {
			returnChar = 'A';
		}
		else if ((paddleA.getY()+paddleA.getHeight()>(ball.getY()))
				&& (paddleA.getX()<ball.getX()+ball.getWidth() && !(paddleA.getX()+paddleA.getWidth()<ball.getX()))
				&& (paddleA.getY()<ball.getY()+ball.getHeight())) {
			returnChar = 'A';
		}
		else if((paddleA.getX()<(ball.getX()+ball.getWidth()))
				&& (paddleA.getY()<ball.getY()+ball.getHeight() && !(paddleA.getY()+paddleA.getHeight()<ball.getY()))
				&& (paddleA.getX()+paddleA.getWidth()>ball.getX())){
			returnChar = 'A';
		}
		else if((paddleA.getX()+paddleA.getWidth()>(ball.getX()))
				&&(paddleA.getY()<ball.getY()+ball.getHeight() && !(paddleA.getY()+paddleA.getHeight()<ball.getY()))
				&& (paddleA.getX()<ball.getX()+ball.getWidth())){
			returnChar = 'A';
		}

		return returnChar;
	}

	public static void main(String[] args) 
	{
		new PongMain();
	}

}
