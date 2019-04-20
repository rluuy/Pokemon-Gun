import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
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
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Stream.of;

import java.nio.file.attribute.PosixFileAttributes;

class GameLoop extends AnimationTimer {

	private long t1 = System.nanoTime(); //Gets total time elapsed in nanoseconds, so early value
	private long t2; //to be initialized as later value to compare (t2-t1)
	private long diff;
	private long interval = 200000000;
	private int bufferX = 720;
	private int bufferY = 480;
	private boolean isBattle = false;
	private GraphicsContext gc;
	private PlayerChar e;
	private ArrayList<String> input;

	//d

	GameLoop(ArrayList<String> inInput, GraphicsContext inGC, PlayerChar inE) {
		input = inInput;
		gc = inGC;
		e = inE;
		
	}

	public void handle(long currentNanoTime) { //code of start, handle called by .start()	
		System.out.println("y = " + e.posY + " x =  " + e.posX);
		if (!isBattle) { 
			if (e.totalPosX < 720) { // Stage 1-1 (Going Left and Right)
				Stage1 s1 = new Stage1();
				s1.generateTiles(gc);
				e.posX = e.totalPosX;}			
			if (e.totalPosY < 480) {
				Stage1 s1 = new Stage1(); // Stage 1-1 (Going Up and Down)
				s1.generateTiles(gc);
				e.posY = e.totalPosY;
			}
			if (e.totalPosX > 720) { // Stage 1-2
				Stage2 s2 = new Stage2();
				s2.generateTiles(gc);
				e.posX = e.totalPosX - bufferX;			
			}
			if (e.totalPosX > 1440) { // Stage 1-3
				Stage3 s3 = new Stage3();
				s3.generateTiles(gc);
				e.posX = e.totalPosX - (bufferX * 2);
			}
			if (e.totalPosY > 480) { // Stage 2-1
				Stage4 s4 = new Stage4();
				s4.generateTiles(gc);
				e.posY = e.totalPosY - bufferY;
			}
			if (e.totalPosY > 480 && e.totalPosX > 720) { // Stage 2-2
				Stage5 s5 = new Stage5();
				s5.generateTiles(gc);
				e.posY = e.totalPosY - bufferY;
				e.posX = e.totalPosX - bufferX;				
			}
			if (e.totalPosY > 480 && e.totalPosX > 1440) { // Stage 2-3
				Stage6 s6 = new Stage6();
				s6.generateTiles(gc);
				e.posY = e.totalPosY - bufferY;
				e.posX = e.totalPosX - (bufferX * 2);			
			}
			if (e.totalPosY > 960) { // Stage 3-1
				Stage7 s7 = new Stage7();
				s7.generateTiles(gc);
				e.posY = e.totalPosY - (bufferY * 2);	
			}
			if (e.totalPosY > 960 && e.totalPosX > 720){ // Stage 3-2
				Stage8 s8 = new Stage8();
				s8.generateTiles(gc);
				e.posY = e.totalPosY - (bufferY * 2);	
				e.posX = e.totalPosX - bufferX;
			}
			if (e.totalPosY > 960 && e.totalPosX > 1440){ // Stage 3-3
				Stage9 s9 = new Stage9();
				s9.generateTiles(gc);
				e.posY = e.totalPosY - (bufferY * 2);	
				e.posX = e.totalPosX - (bufferX * 2);				
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

	

			of(Direction.cachedValues).filter(v -> input.contains(v.name())).findFirst().ifPresent(dir -> {
				t2 = System.nanoTime();
				diff = t2 - t1; //check time elapsed, reset t1 if gets too late
				// System.out.println(diff);
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
		}
	
//		if (isBattle) {
//
//			if (!b.isBattleStart) { // Do we really need isBattle and isBattleStart???
//				b.startTimer();
//				b.setIsBattleStart(true);
//			}
//
//			if (input.contains("Z")) b.setIsReadyForTG2(true);
//
//			//System.out.println(isBattle);
//			b.updateBattle(gc);
//			isBattle = b.checkBattleOver();
//		}
//	}

}