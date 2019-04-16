import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Stages.TestStage;
import Stages.TestStage2;

import static java.util.stream.Stream.of;

import java.nio.file.attribute.PosixFileAttributes;

class GameLoop extends AnimationTimer {

	private long t1 = System.nanoTime(); //Gets total time elapsed in nanoseconds, so early value
	private long t2; //to be initialized as later value to compare (t2-t1)
	private long diff;
	private long interval = 200000000;
	private int buffer = 720;
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
	//	System.out.println(e.posX);
		if (!isBattle) { //If there is no battle, make tiles and check for input
			if (e.totalPosX > 720) {
				TestStage2 te2 = new TestStage2();
				te2.generateTiles(gc);
				e.posX = e.totalPosX - buffer;			
			}
			if (e.totalPosX < 720) {
				TestStage te = new TestStage();
				te.generateTiles(gc);
				e.posX = e.totalPosX;
		
			}
			// !!!!!!!!!!!! You need to Fix whenever the music loops incorrectly and shit. !!!!!!!!!
			
			
//			if (e.posY > 440) {
//				System.out.println("OUT OF BOUNDS DOWN");
//				e.posY = 440;
//			}
			
			if (e.posY < 0) { // Out of Bounds TOP
				e.posY = 0;
			}
			if (e.totalPosX < 0) { // Out of Bounds LEFT
				e.totalPosX = 0;
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

}}