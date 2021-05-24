import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship s;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	
	ObjectManager(Rocketship rocket){
		s = rocket;
	}
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));

	}
	
	void update() {
		for(int i = 0; i < aliens.size(); i++){
			Alien alien = aliens.get(i);
			alien.update();
			if(alien.y >= LeagueInvaders.HEIGHT) {
				alien.isActive = false;
			}
		}
		
		for(int i = 0; i < projectiles.size(); i++){
			Projectile projectile = projectiles.get(i);
			projectile.update();
			if(projectile.y >= LeagueInvaders.HEIGHT) {
				projectile.isActive = false;
			}
		}
		
	}
	
	void draw(Graphics g) {
		s.draw(g);
		
		for(int i = 0; i < aliens.size(); i++){
			Alien alien = aliens.get(i);
			alien.draw(g);

		}
		
		for(int i = 0; i < projectiles.size(); i++){
			Projectile projectile = projectiles.get(i);
			projectile.draw(g);

		}
	}
	
	void purgeObjects(){
		
		for(int i = 0; i < aliens.size(); i++){
			Alien alien = aliens.get(i);
			if(alien.isActive == false) {
				aliens.remove(i);
			}

		}
		
		for(int i = 0; i < projectiles.size(); i++){
			Projectile projectile = projectiles.get(i);
			if(projectile.isActive == false) {
				projectiles.remove(i);
			}

		}
		
	}

}
