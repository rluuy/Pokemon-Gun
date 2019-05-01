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
			PlayerChar p = new PlayerChar(50, 50, 10 , 0.43);

			Canvas canvas = new Canvas(720, 480);
			root.getChildren().add(canvas);	
			primaryStage.setTitle("Pokemon Gun");
			primaryStage.setScene(scene);
			
			VBox PauseBox = new VBox();
			PauseBox.setAlignment(Pos.CENTER);
			PauseBox.setPadding(new Insets(10));
			PauseBox.setSpacing(10);
			Scene PauseMenu = new Scene(PauseBox,720,480);
			Button btn1 = new Button("New");
			Button btn2 = new Button("Save");
			Button btn3 = new Button("Load");
			Button btn4 = new Button("Quit");
			Button btn5 = new Button("Cancel");
			PauseBox.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);
			
			btn5.setOnAction(e -> {
				primaryStage.setScene(scene);
			});

			ArrayList<String> input = new ArrayList<>(); 
			
			scene.setOnKeyPressed(e -> {
				String code = e.getCode().toString();
				if(code.equals("ESCAPE"))
                {
                	primaryStage.setScene(PauseMenu);
                }
				else if (!input.contains(code))
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
			
			btn1.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					GameLoop gl = new GameLoop();
					gl.start();
					primaryStage.setScene(scene);
				}
			});
			
			btn4.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event)
				{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Pokemon Gun");
					alert.setHeaderText("You are about to quit!");
					alert.setContentText("Are you sure?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK)
					{
					Platform.exit();
					System.exit(0);
					}
				}
			});
			
			btn2.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					ObjectOutputStream objectOutputStream = null;
					try {
						objectOutputStream = new ObjectOutputStream(new FileOutputStream("data/save_game.dat"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					try {
						objectOutputStream.writeObject(gl);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						objectOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}