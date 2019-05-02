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
import java.util.concurrent.TimeUnit;

/**
 * This class contains the main method to launch the application and run the game.
 */
public final class Main extends Application {

	private static MediaPlayer mediaPlayer;
	private VBox root;
	private GameLoop gl;  //an instance of GameLoop class
	private GameLoopModel gm; //an instance of the GameLoopModel class
	private Canvas canvas;  //an instance of the canvas
	private GraphicsContext gc;
	private PlayerChar p;
	private Scene scene;
	private int stageN = 0;
	Label label;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This function takes in the coordinates of the player and returns what stage the player is on.
	 * @param inX the X-Coordinate of the player
	 * @param inY the Y-Coordinate of the player
	 * @return the stage on which the player is currently on.
	 */
	public int getStage(int inX, int inY)
	{
		if(inX==0 && inY==0)
			return 1;
		else if(inX==0 && inY==1)
			return 4;
		else if(inX==0 && inY==2)
			return 7;
		else if(inX==1 && inY==0)
			return 2;
		else if(inX==1 && inY==1)
			return 5;
		else if(inX==1 && inY==2)
			return 8;
		else if(inX==2 && inY==0)
			return 3;
		else if(inX==2 && inY==1)
			return 6;
		else if(inX==2 && inY==2)
			return 9;
		return -1;
	}	

	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			Media media = new Media(Paths.get("music/DuelOfFates.mp3").toUri().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
			//root is the VBox that contains the canvas where the GamePlay happens.
			root = new VBox(); 

			scene = new Scene(root,720,480);    
			p = new PlayerChar(50, 50, 10, 0.43,3);
			canvas = new Canvas(720, 480);
			root.getChildren().add(canvas);	

			primaryStage.setTitle("Pokemon Gun");
			primaryStage.setScene(scene);

			//creating a new GameLoopModel and a GameLoop based on that model.
			gc = canvas.getGraphicsContext2D(); 
			gm = new GameLoopModel(p);
			gl = new GameLoop(gm,gc);

			//The PauseBox contains all the buttons required for the Pause Menu.
			HBox MainPauseBox = new HBox();
			VBox PauseBox = new VBox();
			PauseBox.setAlignment(Pos.TOP_LEFT);
			PauseBox.setPadding(new Insets(10));
			PauseBox.setSpacing(10);
			Scene PauseMenu = new Scene(MainPauseBox,720,480);
			Button btn1 = new Button("New");
			Button btn2 = new Button("Save");
			Button btn3 = new Button("Load");
			Button btn4 = new Button("Quit");
			Button btn5 = new Button("Cancel");
			PauseBox.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);

			//ImageBox is the VBox that contains the image of the map and tells the player what stage
			// they are on.
			VBox ImageBox = new VBox();
			ImageBox.setAlignment(Pos.TOP_LEFT);
			ImageBox.setPadding(new Insets(10));
			ImageBox.setSpacing(10);
			Image mapImage = new Image("file:images/game_map.png");
			ImageView mapView = new ImageView();
			mapView.setFitHeight(400);
			mapView.setFitWidth(600);
			mapView.setImage(mapImage);
			ImageBox.getChildren().add(mapView);
			MainPauseBox.getChildren().add(PauseBox);
			MainPauseBox.getChildren().add(ImageBox);

			//if the user presses cancel on the Pause/Main menu
			btn5.setOnAction(e -> {
				primaryStage.setScene(scene);
			});

			ArrayList<String> input = new ArrayList<>(); 

			//track movements of the player according to the user input.
			scene.setOnKeyPressed(e -> {
				if(gl.isGameOver==1)
				{
					System.out.println("YOU DIED");
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("You Died!");
					alert.setHeaderText("You are about to quit");
					alert.setContentText("Do you want to go to the Menu?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK)
					{
						primaryStage.setScene(PauseMenu);
					}
					gl.isGameOver=0;
				}
				else if(gl.isGameOver==2)
				{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("You WON!");
					alert.setHeaderText("Congratulations");
					alert.setContentText("Do you want to go to the Menu?");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK)
					{
						primaryStage.setScene(PauseMenu);
					}
					gl.isGameOver=0;
				}
				String code = e.getCode().toString();
				if(code.equals("ESCAPE"))
				{

					stageN = getStage((gl.getPlayer().totalPosX)/720,(gl.getPlayer().totalPosY)/480);
					ImageBox.getChildren().clear();
					ImageBox.getChildren().add(mapView);
					label = new Label("You are in Stage "+stageN);
					ImageBox.getChildren().add(label);
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

			//Event Handler for when "New Game" Button is pressed
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
					gl.stop();
					gl = new GameLoop(gm,gc);
					gl.start();
					primaryStage.setScene(scene);
					primaryStage.show();
					scene.setOnKeyPressed(e -> {
						if(gl.isGameOver==1)
						{
							System.out.println("YOU DIED");
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("You Died!");
							alert.setHeaderText("You are about to quit");
							alert.setContentText("Do you want to go to the Menu?");

							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK)
							{
								primaryStage.setScene(PauseMenu);
							}
							gl.isGameOver=0;
						}
						else if(gl.isGameOver==2)
						{
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("You WON!");
							alert.setHeaderText("Congratulations");
							alert.setContentText("Do you want to go to the Menu?");
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK)
							{
								primaryStage.setScene(PauseMenu);
							}
							gl.isGameOver=0;
						}
						String code = e.getCode().toString();
						if(code.equals("ESCAPE"))
						{
							stageN = getStage((gl.getPlayer().totalPosX)/720,(gl.getPlayer().totalPosY)/480);
							ImageBox.getChildren().clear();
							ImageBox.getChildren().add(mapView);
							label = new Label("You are in Stage "+stageN);
							ImageBox.getChildren().add(label);
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

			//Event Handler for when "Quit" button is pushed
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

			//Event Handler for when "Save" button is pushed
			btn2.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					String d = (String)"data/save_game.dat";
					System.out.println("here");
					ObjectOutputStream objectOutputStream = null;
					try {
						objectOutputStream = new ObjectOutputStream(new FileOutputStream(d));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}   
					try {
						gm.posX = gl.getPlayer().posX;
						gm.posY = gl.getPlayer().posY;
						gm.totalPosX = gl.getPlayer().totalPosX;
						gm.totalPosY=gl.getPlayer().totalPosY;
						objectOutputStream.writeObject(gl.gm);
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

			//Event Handler for when "Load" button is pushed
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
					GameLoopModel glm = Load();
					if(glm==null)
					{
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Pokemon Gun");
						alert.setHeaderText("You haven't saved anything yet!");

						Optional<ButtonType> result = alert.showAndWait();
					}
					else
						gm=glm;
					p = new PlayerChar(10 , 0.43, gm.PlayerCharHealth,gm.posX,gm.posY,gm.totalPosX,gm.totalPosY);
					System.out.println(gm.toString());
					gl.stop();
					gl = new GameLoop(gm,gc,p);
					gl.start();
					primaryStage.setScene(scene);
					primaryStage.show();
					scene.setOnKeyPressed(e -> {
						if(gl.isGameOver==1)
						{
							System.out.println("YOU DIED");
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("You Died!");
							alert.setHeaderText("You are about to quit");
							alert.setContentText("Do you want to go to the Menu?");

							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK)
							{
								primaryStage.setScene(PauseMenu);
							}
							gl.isGameOver=0;
						}
						else if(gl.isGameOver==2)
						{
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("You WON!");
							alert.setHeaderText("Congratulations");
							alert.setContentText("Do you want to go to the Menu?");
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK)
							{
								primaryStage.setScene(PauseMenu);
							}
							gl.isGameOver=0;
						}
						String code = e.getCode().toString();
						if(code.equals("ESCAPE"))
						{
							stageN = getStage((gl.getPlayer().totalPosX)/720,(gl.getPlayer().totalPosY)/480);
							ImageBox.getChildren().clear();
							ImageBox.getChildren().add(mapView);
							label = new Label("You are in Stage "+stageN);
							ImageBox.getChildren().add(label);
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

			//When the Game is Over, show Game Over and revert to the Main Menu

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function returns the model that was saved by the player.
	 * @return
	 */
	public GameLoopModel Load()
	{
		GameLoopModel gm=null;
		String d = (String)"data/save_game.dat";
		ObjectInputStream objectInputStream = null;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(d));
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			gm = (GameLoopModel)objectInputStream.readObject();
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