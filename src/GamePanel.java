import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font instructionsFont;
	Timer frameDraw;
	Rocketship r  = new Rocketship(250, 700, 50, 50);
	ObjectManager m = new ObjectManager(r);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Timer alienSpawn;

	
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		instructionsFont  = new Font("Arial", Font.PLAIN, 25);
		
		frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
	    
	    if (needImage) {
	        loadImage ("space.png");
	    }

		
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
			drawMenuState(g);
		}
		
		else if(currentState == GAME){
			drawGameState(g);

		}
		
		else if(currentState == END){
		    drawEndState(g);
		}

	}
	
	void updateMenuState() {
		
	}
	
	void updateGameState() {
		m.update();
		if(!r.isActive) {
			alienSpawn.stop();
		}
		
	}
	
	void updateEndState() {
		
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 20, 100);
		g.setFont(instructionsFont);
		g.drawString("Press ENTER to start", 120, 340);
		g.drawString("Press Space for instructions", 85, 500);
		
	}
	
	void drawGameState(Graphics g) {
		
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
		m.draw(g);
		
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 120, 100);
		g.setFont(instructionsFont);
		g.drawString("You have killed 0 enemies", 100, 340);
		g.drawString("Press ENTER to restart", 115, 500);
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
			updateMenuState();
		}
		
		else if(currentState == GAME){
			updateGameState();
		}
		
		else if(currentState == END){
		    updateEndState();
		}
		
		System.out.println("action");
		repaint();

		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
				
			} 
			
			else if(currentState == GAME) {
				currentState++;
				alienSpawn.stop();
			}
			
			else {
				currentState++;
				startGame();
			}
			
		}
		
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			m.addProjectile(r.getProjectile());

		}
		

		
		if(currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    System.out.println("UP");
			    if(r.y >= 10) {
			    	r.up();
			    }
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			    System.out.println("DOWN");
			    if(r.y <= 725) {
			    	r.down();
			    }
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			    System.out.println("RIGHT");
			    if(r.x <= 440) {
			    	r.right();
			    }
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    System.out.println("LEFT");
			    if(r.x >= 10) {
			    	r.left();
			    }
			}
			
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	void startGame() {
	    alienSpawn = new Timer(1000 , m);
	    alienSpawn.start();


	}
	

}
