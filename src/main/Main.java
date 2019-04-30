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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public final class Main extends Application {

	private static MediaPlayer mediaPlayer;
	VBox root, layout;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) 
	{
		try {
			
																							
			root = new VBox(); 
			Scene scene = new Scene(root);    
			PlayerChar p = new PlayerChar(50, 50, 10 , 0.43);;
			
			Canvas canvas = new Canvas(720, 480);
			root.getChildren().add(canvas);
			
			layout = new VBox();
			Scene Layoutscene = new Scene(layout);
			Menu menu = new Menu("File");
			MenuBar menuBar = new MenuBar();
			menuBar.getMenus().add(menu);
			MenuItem menu1 = new Menu("New");
			MenuItem menu2 = new Menu("Load");
			MenuItem menu3 = new Menu("Exit");
			menu1.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					root.getChildren().add(menuBar);
					primaryStage.setScene(scene);
				}
			});
			menu3.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event)
				{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Pokemon Gun");
					alert.setHeaderText("You are about to quit without saving");
					alert.setContentText("Are you sure?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK)
					{
					Platform.exit();
					System.exit(0);
					}
				}
			});
			menu.getItems().addAll(menu1,menu2,menu3);
			layout.getChildren().add(menuBar);
	        Canvas can = new Canvas(500, 100);
			layout.getChildren().add(can);
			System.out.println(System.getProperty("user.dir"));
			
			Image image = new Image("file:images/emerald_Battle_1.png");
	        ImageView imageView = new ImageView(image);
	        layout.getChildren().add(imageView);
	    
			primaryStage.setTitle("Pokemon Gun");
			primaryStage.setScene(Layoutscene);
			
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