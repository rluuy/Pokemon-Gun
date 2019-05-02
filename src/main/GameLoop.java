package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import stages.Enemy;
import stages.Item;
import stages.Sprite;
import stages.Stage1;
import stages.Stage2;
import stages.Stage3;
import stages.Stage4;
import stages.Stage5;
import stages.Stage6;
import stages.Stage62;
import stages.Stage7;
import stages.Stage8;
import stages.Stage9;
import stages.TestStage;
import stages.TestStage2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.ElementKind;

import static java.util.stream.Stream.of;

import java.io.Serializable;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;

class GameLoop extends AnimationTimer implements Serializable {

	private long t1 = System.nanoTime();
	private long t2;
	private long diff;
	private long interval = 200000000;
	private int bufferX = 720;
	private int bufferY = 480;
	private int bufferScalarX = 1;
	private int bufferScalarY = 1;
	private boolean isBattle = false;
	private GraphicsContext gc;
	private PlayerChar e;
	private boolean dflag = false;
	private boolean sflag = true;
	private boolean keyflag = false;

	private ArrayList<String> input;
	private ArrayList<Rectangle> obstacles;
	private ArrayList<Rectangle> dungeon;
	private ArrayList<Item> playerItems = new ArrayList<Item>();

	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Enemy> enemies;
	private ArrayList<Sprite> projectilesP = new ArrayList<Sprite>();
	private ArrayList<Sprite> projectilesE = new ArrayList<Sprite>();

	Image pokeball = new Image("file:images/pokeball.png");
	private boolean gotKey = false;
	private boolean gotBoots = false;
	private boolean gotItem = true;
	private boolean gotItem2 = true;
	private boolean gotItem3 = true;
	private boolean gotItem4 = true;
	private boolean gotItem5 = true;
	private boolean gotItem6 = true;
	private boolean gotItem7 = true;
	private boolean gotItem8 = true;
	private boolean gotItem9 = true;

	long start = System.nanoTime();
	long start2 = System.nanoTime();
	long startCollision = System.nanoTime();
	long elapsedCollision;

	GameLoop(ArrayList<String> inInput, GraphicsContext inGC, PlayerChar inE) {
		input = inInput;
		gc = inGC;
		e = inE;

	}

	/**
	 * Handle's purpose is to run the gameloop itself. It handles loading stages,
	 * placement of enemies, in-game collisions, movement, as well as holds the data
	 * for items. It basically is the heart of the program.
	 * 
	 * The Map is divided up into 9 different stages {[1-1, 1-2, 1-3] [2-1, 2-2,
	 * 2-3] [3-1, 3-2, 3-3]}
	 */
	public void handle(long currentNanoTime) { // code of start, handle called by .start()
		// Stage 1-1 (Going Left and Right)
		if (e.totalPosX < 720) {
			bufferScalarX = 0;
			bufferScalarY = 0;
			Stage1 s1 = new Stage1();
			s1.generateTiles(gc);
			e.posX = e.totalPosX;
			obstacles = s1.getObstacles();
			if (gotItem) { // Code for checking Items and Enemies
				enemies = s1.getEnemies();
				gotItem = false;
				gotItem2 = true;
				gotItem3 = true;
				gotItem4 = true;
				gotItem5 = true;
				gotItem6 = true;
				gotItem7 = true;
				gotItem8 = true;
				gotItem9 = true;
			}
		}
		// Stage 1-1 (Going Up and Down)
		if (e.totalPosY < 480) {
			bufferScalarX = 0;
			bufferScalarY = 0;
			Stage1 s1 = new Stage1();
			s1.generateTiles(gc);
			e.posY = e.totalPosY;
			obstacles = s1.getObstacles();
			dungeon = s1.getD();
		}
		// Stage 1-2
		if (e.totalPosX > 720 && e.totalPosX < 1440 && e.totalPosY < 480) {
			bufferScalarX = 1;
			bufferScalarY = 0;
			Stage2 s2 = new Stage2();
			s2.generateTiles(gc);
			obstacles = s2.getObstacles();
			dungeon = s2.getD();
			e.posX = e.totalPosX - bufferX;
			if (gotItem2) {
				enemies = s2.getEnemies();
				gotItem = true;
				gotItem2 = false;
				gotItem3 = true;
				gotItem4 = true;
				gotItem5 = true;
				gotItem6 = true;
				gotItem7 = true;
				gotItem8 = true;
				gotItem9 = true;
			}

		}
		if (e.totalPosX > 1440 && e.totalPosY < 480) { // Stage 1-3
			bufferScalarX = 2;
			bufferScalarY = 0;
			Stage3 s3 = new Stage3();
			s3.generateTiles(gc);
			e.posX = e.totalPosX - (bufferX * bufferScalarX);
			obstacles = s3.getObstacles();
			dungeon = s3.getD();
			if (gotItem3) {
				enemies = s3.getEnemies();
				gotItem = true;
				gotItem2 = true;
				gotItem3 = false;
				gotItem4 = true;
				gotItem5 = true;
				gotItem6 = true;
				gotItem7 = true;
				gotItem8 = true;
				gotItem9 = true;
				if (!gotKey) {
					items = s3.getItems();
				}
			}
			
		}
		if (e.totalPosY > 480 && e.totalPosY < 960 && e.totalPosX < 720) { // Stage 2-1
			bufferScalarX = 0;
			bufferScalarY = 1;
			Stage4 s4 = new Stage4();
			s4.generateTiles(gc);
			e.posY = e.totalPosY - bufferY;
			obstacles = s4.getObstacles();
			dungeon = s4.getD();
			if (gotItem4) {
				enemies = s4.getEnemies();
				gotItem = true;
				gotItem2 = true;
				gotItem3 = true;
				gotItem4 = false;
				gotItem5 = true;
				gotItem6 = true;
				gotItem7 = true;
				gotItem8 = true;
				gotItem9 = true;
			}
		}
		if (e.totalPosY > 480 && e.totalPosY < 960 && e.totalPosX > 720 && e.totalPosX < 1440 && dflag == false) { // Stage
																													// 2-2
			bufferScalarX = 1;
			bufferScalarY = 1;
			Stage5 s5 = new Stage5();
			s5.generateTiles(gc, false);
			e.posY = e.totalPosY - bufferY;
			e.posX = e.totalPosX - bufferX;
			obstacles = s5.getObstacles();
			dungeon = s5.getD();
			if (gotItem5) {
				enemies = s5.getEnemies();
				gotItem = true;
				gotItem2 = true;
				gotItem3 = true;
				gotItem4 = true;
				gotItem5 = false;
				gotItem6 = true;
				gotItem7 = true;
				gotItem8 = true;
				gotItem9 = true;
			}
		}
		if (e.totalPosY > 480 && e.totalPosY < 960 && e.totalPosX > 1440) { // Stage 2-3
			bufferScalarX = 2;
			bufferScalarY = 1;
			for (int i =0; i < playerItems.size(); i++) {
				Item item = playerItems.get(i);
				if (item.type == 1) {
					keyflag = true;
				}
			}
			if (keyflag == true) {
					Stage62 s62 = new Stage62();
					s62.generateTiles(gc);
					e.posY = e.totalPosY - bufferY;
					e.posX = e.totalPosX - (bufferX * 2);
					obstacles = s62.getObstacles();
					dungeon = s62.getD();
					if (gotItem6) {
						enemies = s62.getEnemies();
						gotItem = true;
						gotItem2 = true;
						gotItem3 = true;
						gotItem4 = true;
						gotItem5 = true;
						gotItem6 = false;
						gotItem7 = true;
						gotItem8 = true;
						gotItem9 = true;
						return;	
					}
				}
				else if (keyflag == false) {
					Stage6 s6 = new Stage6();
					s6.generateTiles(gc);
					e.posY = e.totalPosY - bufferY;
					e.posX = e.totalPosX - (bufferX * 2);
					obstacles = s6.getObstacles();
					dungeon = s6.getD();
					if (gotItem6) {
						enemies = s6.getEnemies();
						gotItem = true;
						gotItem2 = true;
						gotItem3 = true;
						gotItem4 = true;
						gotItem5 = true;
						gotItem6 = false;
						gotItem7 = true;
						gotItem8 = true;
						gotItem9 = true;
					}
				}
		
		
			
		}
		if (e.totalPosY > 960 && e.totalPosX < 720) { // Stage 3-1
			bufferScalarX = 0;
			bufferScalarY = 2;
			Stage7 s7 = new Stage7();
			s7.generateTiles(gc);
			e.posY = e.totalPosY - (bufferY * 2);
			obstacles = s7.getObstacles();
			dungeon = s7.getD();
			if (gotItem7) {
				enemies = s7.getEnemies();
				gotItem = true;
				gotItem2 = true;
				gotItem3 = true;
				gotItem4 = true;
				gotItem5 = true;
				gotItem6 = true;
				gotItem7 = false;
				gotItem8 = true;
				gotItem9 = true;
				
			}
		}
		if (e.totalPosY > 960 && e.totalPosX > 720 && e.totalPosX < 1440) { // Stage 3-2
			bufferScalarX = 1;
			bufferScalarY = 2;
			Stage8 s8 = new Stage8();
			s8.generateTiles(gc);
			e.posY = e.totalPosY - (bufferY * 2);
			e.posX = e.totalPosX - bufferX;
			obstacles = s8.getObstacles();
			dungeon = s8.getD();
			if (gotItem8) {
				enemies = s8.getEnemies();
				gotItem = true;
				gotItem2 = true;
				gotItem3 = true;
				gotItem4 = true;
				gotItem5 = true;
				gotItem6 = true;
				gotItem7 = true;
				gotItem8 = false;
				gotItem9 = true;
			}
		}
		if (e.totalPosY > 960 && e.totalPosX > 1440) { // Stage 3-3
			bufferScalarX = 2;
			bufferScalarY = 2;
			Stage9 s9 = new Stage9();
			s9.generateTiles(gc);
			e.posY = e.totalPosY - (bufferY * 2);
			e.posX = e.totalPosX - (bufferX * 2);
			obstacles = s9.getObstacles();
			dungeon = s9.getD();
			if (gotItem9) {
				enemies = s9.getEnemies();
				gotItem = true;
				gotItem2 = true;
				gotItem3 = true;
				gotItem4 = true;
				gotItem5 = true;
				gotItem6 = true;
				gotItem7 = true;
				gotItem8 = true;
				gotItem9 = false;
			}
		}

		// Code for Out of Bounds Checking
		if (e.posY < 0) { // Out of Bounds TOP
			e.totalPosY = 0;
		}
		if (e.totalPosX < 0) { // Out of Bounds LEFT
			e.totalPosX = 0;
		}
		if (e.totalPosX > 2120) { // Out of Bounds RIGHT
			e.posX = 680;
			e.totalPosX = 2120;
		}
		if (e.totalPosY > 1380) { // Out of Bounds DOWN
			e.posY = 420;
			e.totalPosY = 1380;
		}

		// Code for Animating the Player Char on Screen
		of(Direction.cachedValues).filter(v -> input.contains(v.name())).findFirst().ifPresent(dir -> {
			t2 = System.nanoTime();
			diff = t2 - t1; // check time elapsed, reset t1 if gets too late
			t2 = System.nanoTime();
			diff = t2 - t1;

			if (diff < interval)
				gc.drawImage(dir.image1.apply(e), e.posX, e.posY, e.width, e.height);
			if (diff > interval && diff < interval * 2)
				gc.drawImage(dir.image.apply(e), e.posX, e.posY, e.width, e.height);
			if (diff > interval * 2 && diff < interval * 3)
				gc.drawImage(dir.image2.apply(e), e.posX, e.posY, e.width, e.height);
			if (diff > interval * 3)
				gc.drawImage(dir.image.apply(e), e.posX, e.posY, e.width, e.height);
			if (diff > interval * 4)
				t1 = System.nanoTime();
			dir.move.accept(e);
			e.direction = dir;

		});

		// Draws the animation when there is no more legitimate input
		if (input.size() == 0)
			gc.drawImage(e.direction.image.apply(e), e.posX, e.posY, e.width, e.height);

		long end = System.nanoTime();
		long elapsed = end - start;

		// Draws the Animation for shooting a pokeball
		if (input.contains("SPACE") && elapsed > 200000000) {
			Sprite pokeballS = new Sprite();
			pokeballS.setImage(pokeball);
			double px = e.posX;
			double py = e.posY;
			pokeballS.setPosition(px, py);
			pokeballS.setVelocity(20);
			if (e.direction.toString() == "UP")
				pokeballS.direction = 1;
			else if (e.direction.toString() == "RIGHT")
				pokeballS.direction = 2;
			else if (e.direction.toString() == "DOWN")
				pokeballS.direction = 3;
			else if (e.direction.toString() == "LEFT")
				pokeballS.direction = 4;
			projectilesP.add(pokeballS);
			// gc.drawImage(e.direction.image.apply(e), e.posX, e.posY, e.width, e.height);

			start = System.nanoTime();
		}
		//a

		for (Item pokeball : items)
			pokeball.render(gc);

		long end2 = System.nanoTime();
		long elapsed2 = end2 - start2;

		for (Enemy enemy : enemies) {
			if (enemy.type == 5)
				enemy.AIDirection(e.posX, e.posY);
			enemy.render(gc);
		}

		if (elapsed2 > 500000000) {
			for (Enemy enemy : enemies) {

				Sprite pokeballS = new Sprite();
				pokeballS.setImage(pokeball);
				double px = enemy.positionX;
				double py = enemy.positionY;
				if (enemy.hasProjectileDir) {
					pokeballS.direction = enemy.projectileDir;
				} else {
					pokeballS.direction = enemy.direction;
				}
				if (enemy.hollow)
					pokeballS.hollow = true;
				pokeballS.setVelocity(10);
				pokeballS.setPosition(px, py);
				projectilesE.add(pokeballS);

				// enemy.render(gc);
			}
			start2 = System.nanoTime();
		}

		for (int i = 0; i < projectilesP.size(); i++) {
			if (projectilesP.get(i).positionX > 720 || projectilesP.get(i).positionY > 480
					|| projectilesP.get(i).positionY < 0 || projectilesP.get(i).positionX < 0) {
				projectilesP.remove(i);

			} else {
				projectilesP.get(i).renderMotion(gc);
				gc.drawImage(e.direction.image.apply(e), e.posX, e.posY, e.width, e.height);
			}
		}

		for (int i = 0; i < projectilesE.size(); i++) {
			if (projectilesE.get(i).positionX > 720 || projectilesE.get(i).positionY > 480
					|| projectilesE.get(i).positionY < 0 || projectilesE.get(i).positionX < 0) {
				projectilesE.remove(i);

			} else {
				projectilesE.get(i).renderMotion(gc);
				for (Enemy enemy : enemies) {
					enemy.render(gc);
				}
			}
		}

		long endCollision = System.nanoTime();
		elapsedCollision = endCollision - startCollision;
		enemyCollision();

		hit();
		projectileCollision();
		collision();
		dcollision();
		pickUpItem();
		playerHit();

		gc.fillText("HP: " + Integer.toString(e.getHealth()), 20, 20);
		gc.fillText("AMMO: UNLIMITED ", 20, 40);

	}

	/**
	 * playerHit's purpose is to detect when a player is hit and update the values
	 */
	private void playerHit() {
		Rectangle playerRect = new Rectangle(e.posX, e.posY, 48, 48);
		for (int i = 0; i < projectilesE.size(); i++) {
			Sprite projectile = projectilesE.get(i);
			Rectangle rect = new Rectangle(projectile.positionX, projectile.positionY, 48, 48);

			if (rect.intersects(playerRect.getBoundsInLocal())) {
				e.loseHealth();
				projectilesE.remove(i);
			}
		}
	}

	/**
	 * collision is for obstacle collisions in the game. It resets the players
	 * position back depending on the direction that the player is walking.
	 */
	private void collision() {
		Rectangle playerRect = new Rectangle(e.posX, e.posY, 48, 48);
		for (Rectangle collision : obstacles) {
			if (collision.intersects(playerRect.getBoundsInLocal())) {
				if (e.direction.toString().equals("UP")) {
					e.totalPosY = (int) collision.getY() + (bufferY * bufferScalarY) + 50;
					continue;
				} else if (e.direction.toString().equals("DOWN")) {
					e.totalPosY = (int) collision.getY() + (bufferY * bufferScalarY) - 58;
					continue;
				} else if (e.direction.toString().equals("LEFT")) {
					e.totalPosX = (int) collision.getX() + (bufferX * bufferScalarX) + 50;
					continue;
				} else if (e.direction.toString().equals("RIGHT")) {
					e.totalPosX = (int) collision.getX() + (bufferX * bufferScalarX) - 50;
					continue;
				}

			}
		}
	}

	/**
	 * dcollision is a special type of collision that sends the player to the
	 * somewhere else (dungeon) if they hit the stair block within the game.
	 */
	private void dcollision() {
		Rectangle playerRect = new Rectangle(e.posX, e.posY, 48, 48);
		if (dungeon != null) {
			for (Rectangle collision : dungeon) {
				if (collision.intersects(playerRect.getBoundsInLocal())) {
					if (collision.getX() == 336.0) { // Moves Player on Stage 2-2 to 1-3
						e.totalPosX = 2060;
						e.totalPosY = 0;
						e.posY = 0;
						e.posX = 660;
					} else if (collision.getX() == 672.0) { // Moves Player on Stage 1-3 to 2-2
						e.totalPosX = 1120;
						e.totalPosY = 626;
						e.posY = 146;
						e.posX = 400;
					} else if (collision.getX() == 0.0) { // Moves Player on Stage 3-1 to 2-3
						e.totalPosX = 2000;
						e.totalPosY = 652;
						e.posY = 172;
						e.posX = 600;
					} else if (collision.getX() == 624.0) { // Moves Player on Stage 2-3 to 3-1
						e.totalPosX = 50;
						e.totalPosY = 1380;
						e.posX = 50;
						e.posY = 420;
					}

				}
			}
		}
	}

	// Removes projectiles that collide into an obstacle
	private void projectileCollision() {
		for (Rectangle collision : obstacles) {
			for (int j = 0; j < projectilesP.size(); j++) {
				Sprite proj = projectilesP.get(j);
				Rectangle projectileRect = new Rectangle(proj.positionX, proj.positionY, 48, 48);
				if (collision.intersects(projectileRect.getBoundsInLocal())) {
					projectilesP.remove(j);
				}
			}
			for (int k = 0; k < projectilesE.size(); k++) {
				Sprite proj = projectilesE.get(k);
				Rectangle projectileRect = new Rectangle(proj.positionX, proj.positionY, 48, 48);
				if (collision.intersects(projectileRect.getBoundsInLocal())) {
					if (!proj.hollow)
						projectilesE.remove(k);
				}
			}
		}
	}

	// Player loses health when colliding with an enemy
	private void enemyCollision() {
		Rectangle playerRect = new Rectangle(e.posX, e.posY, 48, 48);
		for (int i = 0; i < enemies.size(); i++) {
			Sprite enemy = enemies.get(i);
			Rectangle enemyRect = new Rectangle(enemy.positionX, enemy.positionY, 30, 30);
			if (enemyRect.intersects(playerRect.getBoundsInLocal())) {
				System.out.print("1");
				if (elapsedCollision > 1000000000) {
					e.loseHealth();
					startCollision = System.nanoTime();
				}
				if (e.direction.toString().equals("UP")) {
					e.totalPosY = (int) enemyRect.getY() + (bufferY * bufferScalarY) + 50;
					continue;
				} else if (e.direction.toString().equals("DOWN")) {
					e.totalPosY = (int) enemyRect.getY() + (bufferY * bufferScalarY) - 58;
					continue;
				} else if (e.direction.toString().equals("LEFT")) {
					e.totalPosX = (int) enemyRect.getX() + (bufferX * bufferScalarX) + 50;
					continue;
				} else if (e.direction.toString().equals("RIGHT")) {
					e.totalPosX = (int) enemyRect.getX() + (bufferX * bufferScalarX) - 50;
					continue;
				}
			}
		}
	}

	// Player hit an enemy with a projectile
	private void hit() {
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			Rectangle enemyRect = new Rectangle(enemy.positionX, enemy.positionX, 48, 48);
			for (int j = 0; j < projectilesP.size(); j++) {
				Sprite projectile = projectilesP.get(j);
				Rectangle proj = new Rectangle(projectile.positionX, projectile.positionX, 48, 48);
				if (projectile.intersects(enemy)) {
					gc.drawImage(new Image("file:images/enemy1_down_rest copy.png"), enemy.positionX, enemy.positionY);
					projectilesP.remove(j);
					enemy.loseHealth();
					if (enemy.getHealth() == 0)
						enemies.remove(i);
				}
			}
		}
	}

	// Player picks up an item
	private void pickUpItem() {
		Rectangle playerRect = new Rectangle(e.posX, e.posY, 48, 48);
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			Rectangle rect = new Rectangle(item.positionX, item.positionY, 48, 48);

			if (rect.intersects(playerRect.getBoundsInLocal())) {
				if (item.type == 1)
					gotKey = true;
				System.out.print(gotKey);
				items.remove(i);
				playerItems.add(item);
			}
		}
	}

}