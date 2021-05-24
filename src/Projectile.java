import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {

	Projectile(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, 10);
		// TODO Auto-generated constructor stub
	}
	
	void update() {
		y -= speed;
		
	}
	
	void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
		
	}

}
