package main;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public final class Main extends Application {

	private static MediaPlayer mediaPlayer;
	VBox root;
	HBox layout;
	GameLoop gl;

	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			root = new VBox(); 
			Scene scene = new Scene(root,720,480);    
			PlayerChar p = new PlayerChar(50, 50, 10 , 0.43);;

			Canvas canvas = new Canvas(720, 480);
			root.getChildren().add(canvas);

			
			primaryStage.setTitle("Pokemon Gun");
			primaryStage.setScene(scene);
			
			
			ArrayList<String> input = new ArrayList<>(); 
			scene.setOnKeyPressed(e -> {
				String code = e.getCode().toString();
				if (!input.contains(code))
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