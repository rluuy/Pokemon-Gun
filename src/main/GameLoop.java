package main;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import stages.Stage1;
import stages.Stage2;
import stages.Stage3;
import stages.Stage4;
import stages.Stage5;
import stages.Stage6;
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

import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;

class GameLoop extends AnimationTimer {

	private long t1 = System.nanoTime(); //Gets total time elapsed in nanoseconds, so early value
	private long t2; //to be initialized as later value to compare (t2-t1)
	private long diff;
	private long interval = 200000000;
	private int bufferX = 720;
	private int bufferY = 480;
	private int bufferScalarX = 1;
	private int bufferScalarY = 1;
	private boolean isBattle = false;
	private GraphicsContext gc;
	private PlayerChar e;
	private ArrayList<String> input;
	private ArrayList<Rectangle> obstacles;
	private ArrayList<EnemyChar1> enemy;
	private static MediaPlayer mediaPlayer;
	

	//d

	GameLoop(ArrayList<String> inInput, GraphicsContext inGC, PlayerChar inE) {
		input = inInput;
		gc = inGC;
		e = inE;
		
	}

	public void handle(long currentNanoTime) { //code of start, handle called by .start()	
		if (!isBattle) { 
		//	System.out.println("y = " + e.totalPosY + " x = " + e.totalPosX );
			if (e.totalPosX < 720) { // Stage 1-1 (Going Left and Right)
				bufferScalarX = 0;
				bufferScalarY = 0;
				Stage1 s1 = new Stage1();
				s1.generateTiles(gc);
				obstacles = s1.getObstacles();
				e.posX = e.totalPosX;}
			
			if (e.totalPosY < 480) {
				bufferScalarX = 0;
				bufferScalarY = 0;
				Stage1 s1 = new Stage1(); // Stage 1-1 (Going Up and Down)
				s1.generateTiles(gc);
				e.posY = e.totalPosY;
				obstacles = s1.getObstacles();
			}
			if (e.totalPosX > 720) { // Stage 1-2
				bufferScalarX = 1;
				bufferScalarY = 0;
				Stage2 s2 = new Stage2();
				s2.generateTiles(gc);
				obstacles = s2.getObstacles();
				
				e.posX = e.totalPosX - bufferX;			
			}
			if (e.totalPosX > 1440) { // Stage 1-3
				bufferScalarX = 2;
				bufferScalarY = 0;
				Stage3 s3 = new Stage3();
				s3.generateTiles(gc);
				e.posX = e.totalPosX - (bufferX * bufferScalarX);
				obstacles = s3.getObstacles();
			}
			if (e.totalPosY > 480) { // Stage 2-1
				bufferScalarX = 0;
				bufferScalarY = 1;
				Stage4 s4 = new Stage4();
				s4.generateTiles(gc);
				e.posY = e.totalPosY - bufferY;
				obstacles = s4.getObstacles();
			}
			if (e.totalPosY > 480 && e.totalPosX > 720) { // Stage 2-2
				bufferScalarX = 1;
				bufferScalarY = 1;
				Stage5 s5 = new Stage5();
				s5.generateTiles(gc);
				e.posY = e.totalPosY - bufferY;
				e.posX = e.totalPosX - bufferX;		
				obstacles = s5.getObstacles();
			}
			if (e.totalPosY > 480 && e.totalPosX > 1440) { // Stage 2-3
				bufferScalarX = 2;
				bufferScalarY = 1;
				Stage6 s6 = new Stage6();
				s6.generateTiles(gc);
				e.posY = e.totalPosY - bufferY;
				e.posX = e.totalPosX - (bufferX * 2);	
				obstacles = s6.getObstacles();
			}
			if (e.totalPosY > 960) { // Stage 3-1
				bufferScalarX = 0;
				bufferScalarY = 2;
				Stage7 s7 = new Stage7();
				s7.generateTiles(gc);
				e.posY = e.totalPosY - (bufferY * 2);	
				obstacles = s7.getObstacles();
				
			}
			if (e.totalPosY > 960 && e.totalPosX > 720){ // Stage 3-2
				bufferScalarX = 1;
				bufferScalarY = 2;
				Stage8 s8 = new Stage8();
				s8.generateTiles(gc);
				e.posY = e.totalPosY - (bufferY * 2);	
				e.posX = e.totalPosX - bufferX;
				obstacles = s8.getObstacles();
			}
			if (e.totalPosY > 960 && e.totalPosX > 1440){ // Stage 3-3
				bufferScalarX = 2;
				bufferScalarY = 2;
				Stage9 s9 = new Stage9();
				s9.generateTiles(gc);
				e.posY = e.totalPosY - (bufferY * 2);	
				e.posX = e.totalPosX - (bufferX * 2);		
				obstacles = s9.getObstacles();			
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
				diff = t2 - t1; //check time elapsed, reset t1 if gets too late
				t2 = System.nanoTime();
				diff = t2 - t1;

				if (diff < interval) gc.drawImage(dir.image1.apply(e), e.posX, e.posY, e.width, e.height);
				if (diff > interval && diff < interval * 2) gc.drawImage(dir.image.apply(e), e.posX, e.posY, e.width, e.height);
				if (diff > interval * 2 && diff < interval * 3) gc.drawImage(dir.image2.apply(e), e.posX, e.posY, e.width, e.height);
				if (diff > interval * 3) gc.drawImage(dir.image.apply(e), e.posX, e.posY, e.width, e.height);
				if (diff > interval * 4) t1 = System.nanoTime();
				dir.move.accept(e);
				e.direction = dir;

			});
			
			if (input.size() == 0) gc.drawImage(e.direction.image.apply(e), e.posX, e.posY, e.width, e.height);
			
			if (input.contains("SPACE")) {
				gc.drawImage(e.direction.image.apply(e), e.posX, e.posY, e.width, e.height);
		

			}
			
			gc.fillText("HP: " + Integer.toString(e.getHealth()), 20, 20);
			gc.fillText("AMMO: UNLIMITED ", 20, 40);
			collision();
			
			
			List<Bullet> projectiles = e.getFireBullets();
			for (int i = 0; i < projectiles.size(); i++) {
				Image pokeball = new Image("file:images/pokeball.png");
				projectiles.get(i).update(e.direction);
				if (projectiles.get(i).getX() > 720 || projectiles.get(i).getY() > 480 || projectiles.get(i).getY() < 0 || projectiles.get(i).getX() < 0 ) {
					projectiles.remove(i);
					return;
				} else {
					 gc.drawImage(pokeball, projectiles.get(i).getX(), projectiles.get(i).getY());
				}


			}
			
}


	private void collision() {
		Rectangle playerRect = new Rectangle(e.posX,e.posY, 48, 48);
		for(Rectangle collision : obstacles) {
	        if(collision.intersects(playerRect.getBoundsInLocal())){ 
	        	if (e.direction.toString().equals("UP")) {
		        	e.totalPosY = (int) collision.getY() + (bufferY * bufferScalarY) + 50;
		        	continue;
	        	}
	        	else if (e.direction.toString().equals("DOWN")) {
		        	e.totalPosY = (int) collision.getY() + (bufferY * bufferScalarY) - 58;
		        	continue;
	        	}
	        	else if (e.direction.toString().equals("LEFT")) {
		        	e.totalPosX = (int) collision.getX() + (bufferX * bufferScalarX) + 50;
		        	continue;
	        	}
	        	else if (e.direction.toString().equals("RIGHT")) {
		        	e.totalPosX = (int) collision.getX() + (bufferX * bufferScalarX) - 50;
		        	continue;
	        	}
	        
	        	
	        }
		}
	}



	}