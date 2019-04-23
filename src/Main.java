
import java.io.File;

import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public final class Main extends Application {

	private static MediaPlayer mediaPlayer;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Media media = new Media(Paths.get("music/DuelOfFates.mp3").toUri().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
			Group root = new Group();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Final Project");
			PlayerChar p = new PlayerChar(50, 50, 10 , 0.43);

			Canvas canvas = new Canvas(720, 480);
			root.getChildren().add(canvas);

			ArrayList<String> input = new ArrayList<>(); 

			scene.setOnKeyPressed(e -> {
				String code = e.getCode().toString();
				if (!input.contains(code)) // only add once... prevent duplicates
					input.add(code);
			
				
			});
			
			scene.setOnKeyReleased(e -> {
				String code = e.getCode().toString();
				input.remove(code);
			});
			

			    


			GraphicsContext gc = canvas.getGraphicsContext2D(); 


			GameLoop gl = new GameLoop(input, gc, p);
			gl.start();

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}