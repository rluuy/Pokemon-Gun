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
	GameLoop gl;
	
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
			MenuItem menu2 = new Menu("Save");
			MenuItem menu3 = new Menu("Load");
			MenuItem menu4 = new Menu("Exit");
			
			menu1.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					root.getChildren().add(menuBar);
					primaryStage.setScene(scene);
				}
			});
			menu4.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event)
				{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("The Legend of Zelda");
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
			menu.getItems().addAll(menu1,menu2,menu3,menu4);
			layout.getChildren().add(menuBar);
	        Canvas can = new Canvas(720, 100);
			layout.getChildren().add(can);
			System.out.println(System.getProperty("user.dir"));
			
			Image image = new Image("file:images/zelda_logo.png");
	        ImageView imageView = new ImageView(image);
	        layout.getChildren().add(imageView);
			primaryStage.setTitle("Final Project");
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
			gl = new GameLoop(input, gc, p);
			menu2.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					System.out.println("here");
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
			
			gl.start();
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}