import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject  {

	Alien(int x, int y, int width, int height) {
		super(x, y, width, height, 1);
		// TODO Auto-generated constructor stub
	}
	
	void update() {
		y += speed;
		
	}
	
	void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
		
	}
	

}
