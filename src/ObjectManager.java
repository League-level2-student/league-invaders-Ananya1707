import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship s;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	int score = 0;
	
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
		
		checkCollision();
		purgeObjects();
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
		
	}
	
	void checkCollision() {
		for(int i = 0; i < aliens.size(); i++){
			Alien alien = aliens.get(i);
			if(s.collisionBox.intersects(alien.collisionBox)){
				alien.isActive = false;
				s.isActive = false;
				
			}
			
			for(int j = 0; j < projectiles.size(); j++){
				Projectile projectile = projectiles.get(j);
				if(projectile.collisionBox.intersects(alien.collisionBox)){
					alien.isActive = false;
					projectile.isActive = false;
					score += 1;
					
				}			

			}
			
		}

	}
	
	int getscore() {
		return score;
	}


}
