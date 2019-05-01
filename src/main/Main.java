package main;
import java.io.*;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	GameLoopModel gm;
	Canvas canvas;
	GraphicsContext gc;
	PlayerChar p;
	Scene scene;
	int filecount=0;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			root = new VBox(); 
			scene = new Scene(root,720,480);    
			p = new PlayerChar(50, 50, 10 , 0.43,3);

			canvas = new Canvas(720, 480);
			root.getChildren().add(canvas);	
			primaryStage.setTitle("Pokemon Gun");
			primaryStage.setScene(scene);
			
			gc = canvas.getGraphicsContext2D(); 
			gm = new GameLoopModel(p);
			gl = new GameLoop(gm,gc);
			
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
				else if (!gm.input.contains(code))
					gm.input.add(code);
			});
			
			scene.setOnKeyReleased(e -> {
				String code = e.getCode().toString();
				gm.input.remove(code);
				System.out.println(input.toString());
			});
			
//			GraphicsContext gc = canvas.getGraphicsContext2D(); 
//			gl = new GameLoop(input, gc, p);
//			gl.start();
			
			gl.start();
			
			primaryStage.show();
			
			btn1.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					root = new VBox(); 
					scene = new Scene(root,720,480);    
					p = new PlayerChar(50, 50, 10 , 0.43,3);
					canvas = new Canvas(720, 480);
					root.getChildren().add(canvas);	
					gc = canvas.getGraphicsContext2D(); 
					gm = new GameLoopModel(p);
					gl = new GameLoop(gm,gc);
					gl.start();
					primaryStage.setScene(scene);
					primaryStage.show();
					scene.setOnKeyPressed(e -> {
						String code = e.getCode().toString();
						if(code.equals("ESCAPE"))
		                {
		                	primaryStage.setScene(PauseMenu);
		                }
						else if (!gm.input.contains(code))
							gm.input.add(code);
					});
					
					scene.setOnKeyReleased(e -> {
						String code = e.getCode().toString();
						gm.input.remove(code);
						System.out.println(input.toString());
					});
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
							String d = (String)"data/save_game"+(filecount++)+".dat";
							System.out.println("here");
							ObjectOutputStream objectOutputStream = null;
							try {
								objectOutputStream = new ObjectOutputStream(new FileOutputStream(d));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}   
							try {
									objectOutputStream.writeObject(gm);
									System.out.println("Saving"+ gm.totalPosX);
									primaryStage.setScene(scene);
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
			
			VBox LoadBox = new VBox();
			LoadBox.setAlignment(Pos.CENTER);
			LoadBox.setPadding(new Insets(10));
			LoadBox.setSpacing(10);
			Scene LoadMenu = new Scene(LoadBox,720,480);
			Button ld1 = new Button("Load 1");
			ld1.setOnAction(e -> { filecount=1; });
			Button ld2 = new Button("Load 2");
			ld2.setOnAction(e -> { filecount=2; });
			Button ld3 = new Button("Load 3");
			ld3.setOnAction(e -> { filecount=3; });
			Button ld4 = new Button("Load 4");
			ld4.setOnAction(e -> { filecount=4; });
			Button ld5 = new Button("Load 5");
			ld5.setOnAction(e -> { filecount=5; });
			Button ld6 = new Button("< Back");
			LoadBox.getChildren().addAll(ld1, ld2, ld3, ld4, ld5, ld6);
			ld6.setOnAction(e -> {  primaryStage.setScene(PauseMenu); });
			
			btn3.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					root = new VBox(); 
					scene = new Scene(root,720,480);    
					canvas = new Canvas(720, 480);
					root.getChildren().add(canvas);	
					gc = canvas.getGraphicsContext2D(); 
					gm = Load(filecount-1);
					System.out.println("Loading"+ gm.totalPosX);
					p = new PlayerChar(10 , 0.43, gm.PlayerCharHealth,gm.posX,gm.posY,gm.totalPosX,gm.totalPosY);
					System.out.println(gm.toString());
					gl = new GameLoop(gm,gc,p);
					gl.start();
					primaryStage.setScene(scene);
					primaryStage.show();
					scene.setOnKeyPressed(e -> {
						String code = e.getCode().toString();
						if(code.equals("ESCAPE"))
		                {
		                	primaryStage.setScene(PauseMenu);
		                }
						else if (!gm.input.contains(code))
							gm.input.add(code);
					});
					scene.setOnKeyReleased(e -> {
						String code = e.getCode().toString();
						gm.input.remove(code);
						System.out.println(input.toString());
					});
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public GameLoopModel Load(int load)
	{
		GameLoopModel gm=null;
		String d = (String)"data/save_game"+(load)+".dat";
		ObjectInputStream objectInputStream = null;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(d));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			gm = (GameLoopModel) objectInputStream.readObject();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			objectInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sending in load");
		return gm;
	}

}