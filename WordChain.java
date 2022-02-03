package games;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WordChain extends Application{
	private int turn = 30;
	private int p1Point = 0;
	private int p2Point = 0;
	private int pointer = 0;
	private String [] check = new String[50];
	private Label previous = new Label("WordChain");
	private Pane root = new Pane();
	private Pane firstPane = new Pane();
	private Image startPane = new Image("photo/vocabulary-game-word-chain.png");
	private ImageView imvLogo = new ImageView(startPane);
	private double progressBarPercentage = 0.00;
	private ProgressBar loading = new ProgressBar(progressBarPercentage);
	private Pane loadingScreen = new Pane();
	private Button btnStart = new Button("Start");
	int preSetTime = 60;
	int time = preSetTime;
	private Label lbltimer = new Label("15s");
	private	Timeline animation;
	private int player = 0;
	Circle circleP1 = new Circle(80,-50,30,Color.LIME);
	Circle circleP2 = new Circle(80,-50,30,Color.GRAY);

	Label turns = new Label(turn+ ":  Turns Left");
	private Pane game = new Pane();
	private TextField txfInput = new TextField();
	private Button btnConfirm= new Button("Confirm");
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage st) throws Exception {
		firstPane = Game();
		root.setPrefSize(1024, 600);
		root.getChildren().add(firstPane);

		Scene sc = new Scene(root);
		st.setScene(sc);
		st.setTitle("Word Chain");
		st.show();


	}
	public Pane loadingPage() {
		progressBarPercentage = 0;
		Timeline animation1;

		loading.setPrefSize(750, 30);
		
		loadingScreen.setStyle("-fx-background-image: url('photo/vocabulary-game-word-chain.png')");
	
		loadingScreen.getChildren().add(loading);
		loading.setTranslateX(150);
		loading.setTranslateY(520);
		loadingScreen.setPrefSize(1024, 600);
		loading.setStyle(" -fx-text-box-border: blue;" + 
				"  -fx-control-inner-background: #4F92DF;"
				+ "-fx-accent: cyan;"
				+ "-fx-border-radius: 10;"
				+ "-fx-background-radius: 10");


			animation1 = new Timeline(new KeyFrame(Duration.seconds(0.01), e->changeProgressbar()));
			animation1.setCycleCount(1000);
			animation1.play();
			

		
		return loadingScreen;
		
	}

	private void changeProgressbar() {
		progressBarPercentage+=0.001;
		loading.setProgress(progressBarPercentage);
			if (progressBarPercentage>0.99) {
				loadingScreen.getChildren().clear();
				loadingScreen.getChildren().add(btnStart);
				btnStart.setPrefSize(100, 40);
				btnStart.setStyle("-fx-font-family: 'impact';"
						+ "-fx-font-size: 20;"
						+ "-fx-background-color: cyan;"
						+ "-fx-border-color: blue;"
						+ "-fx-border-radius: 10;"
						+ "-fx-background-radius: 10");
				btnStart.setEffect(new DropShadow(10,5,5,Color.AQUAMARINE));
				btnStart.setTranslateX(468);
				btnStart.setTranslateY(520);
				
				btnStart.setOnAction(e->{
					firstPane.getChildren().clear();
					firstPane.setStyle("");
					firstPane = Game();
					root.getChildren().add(firstPane);
				});
			}
	}
	private Pane Game() {
		
		game.setPrefSize(1024, 600);
		game.setStyle("-fx-background-color: white;");
		game.getChildren().addAll(imvLogo,txfInput,btnConfirm);
		imvLogo.setPreserveRatio(true);
		imvLogo.setFitWidth(612);
		imvLogo.setTranslateX(200);

		txfInput.setTranslateX(280);
		txfInput.setTranslateY(390);
		txfInput.setPrefSize(370, 50);
		txfInput.setStyle("-fx-border-color: #4F92DF;"
				+ "-fx-border-radius: 5;"
				+ "-fx-font-size: 22;"
				+ "-fx-text-fill: #4F92DF;"
				+ "-fx-font-weight: bold;");
		
		btnConfirm.setTranslateX(670);
		btnConfirm.setTranslateY(390);
		btnConfirm.setPrefSize(100, 50);
		btnConfirm.setStyle("-fx-background-color: cyan;"
				+ "-fx-border-color: #4F92DF;"
				+ "-fx-border-radius: 5;"
				+ "-fx-background-radius: 5;"
				+ "-fx-font-family: 'impact';"
				+ "-fx-font-size: 16;"
				+ "-fx-text-fill: #4F92DF;");
		

		Light.Distant light= new Light.Distant();
		light.setAzimuth(90.0);
		light.setElevation(50);
		
		Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(5);
		
		Pane player1 = new Pane();
		circleP1.setEffect(lighting);
		
		Label lblNameP1 = new Label("Player 1");
		lblNameP1.setTranslateY(30);
		lblNameP1.setTranslateX(40);
		lblNameP1.setStyle("-fx-font-family: 'impact';"
				+ "-fx-font-size: 25;" );
		
		Label lblPointsP1 = new Label("0 pts");
		lblPointsP1.setTranslateX(45);
		lblPointsP1.setTranslateY(85);
		lblPointsP1.setStyle("-fx-font-family: 'impact';"
				+ "-fx-font-size: 25;" );
		
		player1.getChildren().addAll(lblNameP1,lblPointsP1,circleP1);
		player1.setTranslateY(340);
		player1.setTranslateX(50);
		player1.setStyle("-fx-border-color:#4F92DF;"
				+ "-fx-border-width: 5;"
				+ "-fx-border-radius: 10" );
		player1.setPrefSize(160, 160);

		Pane player2 = new Pane();
		circleP2.setStroke(Color.DARKSLATEGREY);
		circleP2.setEffect(lighting);
		
		Label lblNameP2 = new Label("Player 2");
		lblNameP2.setTranslateY(30);
		lblNameP2.setTranslateX(40);
		lblNameP2.setStyle("-fx-font-family: 'impact';"
				+ "-fx-font-size: 25;" );
		
		Label lblPointsP2 = new Label("0 pts");
		lblPointsP2.setTranslateX(45);
		lblPointsP2.setTranslateY(85);
		lblPointsP2.setStyle("-fx-font-family: 'impact';"
				+ "-fx-font-size: 25;" );
		
		player2.getChildren().addAll(lblNameP2,lblPointsP2,circleP2);
		player2.setTranslateY(340);
		player2.setTranslateX(830);
		player2.setStyle("-fx-border-color:#4F92DF;"
				+ "-fx-border-width: 5;"
				+ "-fx-border-radius: 10" );
		player2.setPrefSize(160, 160);

		animation = new Timeline(new KeyFrame(Duration.seconds(1), e->changeClock()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		

		lbltimer.setTranslateY(470);
		lbltimer.setTranslateX(475);
		lbltimer.setFont(new Font("arial",35));
		lbltimer.setStyle("-fx-border-color: black;"
				+ "-fx-padding: 5;"
				+ "-fx-border-radius:5;"
				+ "-fx-border-color:#4F92DF;"
				+ "-fx-border-width: 3");
		turns.setText(turn+ ":  Turns Left");
		turns.setStyle("-fx-font-family: 'impact';"
				+ "-fx-font-size: 16;"
				+ "-fx-text-fill: #4F92DF;");
		turns.setTranslateX(460);
		turns.setTranslateY(280);
		Line line = new Line(450,277,550,277);
		Line line1 = new Line(450,305,550,305);
		line.setStrokeWidth(3);
		line1.setStrokeWidth(3);
		line.setStroke(Color.CORNFLOWERBLUE);
		line1.setStroke(Color.CORNFLOWERBLUE);
		
		game.getChildren().addAll(player1,player2,lbltimer,previous,turns,line,line1);
		previous.setPrefSize(230, 50);
		previous.setTranslateX(390);
		previous.setTranslateY(320);
		previous.setAlignment(Pos.CENTER);
		previous.setStyle("-fx-border-color: #4F92DF;"
				+ "-fx-border-width: 3;"
				+ "-fx-border-radius: 10;"
				+ "-fx-font-family: 'impact';"
				+ "-fx-font-size: 24;"
				+ "-fx-text-fill: #4F92DF;");
		
		btnConfirm.setOnAction(e->{
			if (previous.getText().toLowerCase().charAt(previous.getText().length()-1)== txfInput.getText().toLowerCase().charAt(0)) {
				if (CheckText(txfInput.getText())) {
					if (player==0) {
						p1Point+= txfInput.getText().length();
						lblPointsP1.setText(p1Point+" pts");
						check[pointer] = txfInput.getText();
						pointer++;
					}else if(player==1) {

						p2Point+= txfInput.getText().length();
						lblPointsP2.setText(p2Point+" pts");
					}
					previous.setText(txfInput.getText());
					
					txfInput.clear();
					txfInput.requestFocus();
					turn--;
					turns.setText(turn+ ":  Turns Left");
					if (turn == 0) {
						try {
							new gameOverScene().start(new Stage());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					new Alert(AlertType.INFORMATION,"The word "+txfInput.getText()+" has already been entered or the word do not exists", ButtonType.OK).showAndWait();
				}
			}
				if (player==0) {
					player=1;
					circleP1.setFill(Color.GREY);

					circleP2.setFill(Color.LIME);
				}else if(player==1) {
					player=0;
					circleP1.setFill(Color.GREEN);

					circleP2.setFill(Color.LIME);
				}
				time=preSetTime;
				lbltimer.setText(String.format("%02d", time)+"s");
				
			
			
		});
		txfInput.setOnAction(e->{
			if (previous.getText().toLowerCase().charAt(previous.getText().length()-1)== txfInput.getText().toLowerCase().charAt(0)) {
				if (CheckText(txfInput.getText())) {
					if (player==0) {
					p1Point+= txfInput.getText().length();
					lblPointsP1.setText(p1Point+" pts");
				}else if(player==1) {

					p2Point+= txfInput.getText().length();
					lblPointsP2.setText(p2Point+" pts");
				}
					previous.setText(txfInput.getText());
					txfInput.clear();
					txfInput.requestFocus();
					turn--;
					turns.setText(turn+ ":  Turns Left");
					check[pointer] = txfInput.getText();
					pointer++;
					if (turn == 0) {
						try {
							new gameOverScene().start(new Stage());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					new Alert(AlertType.INFORMATION,"The word "+txfInput.getText()+" has already been entered or the word do not exists", ButtonType.OK).showAndWait();
				}
			}
				if (player==0) {
					player=1;
					circleP1.setFill(Color.GREY);

					circleP2.setFill(Color.LIME);
				}else if(player==1) {
					player=0;
					circleP1.setFill(Color.LIME);

					circleP2.setFill(Color.GREY);
				}

				time=preSetTime;
				lbltimer.setText(String.format("%02d", time)+"s");
				
		});
		return game;
	}
	private void changeClock() {
		if (time>0) {
			time--;
		}
		if(time==0) {
			time=preSetTime;
			if (player==0) {
				player=1;
				circleP1.setFill(Color.GREY);

				circleP2.setFill(Color.LIME);
			}else if(player==1) {
				player=0;
				circleP1.setFill(Color.LIME);

				circleP2.setFill(Color.GREY);
			}
		}
		
		lbltimer.setText(String.format("%02d", time)+"s");
	}
	private boolean CheckText(String text) {
		for (int i = 0; i < check.length; i++) {
			if (text.equalsIgnoreCase(check[i]) ) {
				return false;
			}
		}
        try {
        	String directory = new File("src/Library/English-word-list.txt").getAbsolutePath();
           File fileName = new File(directory);
            FileReader fileReader = new FileReader(fileName);     
            BufferedReader bufferedreader = new BufferedReader(fileReader);
            String strLine;            
           while ((strLine = bufferedreader.readLine()) != null) {
        	   if (text.equalsIgnoreCase(strLine)) {
				return true;
        	   }
            }         
           fileReader.close();
         } catch (IOException e) {
            e.printStackTrace();     
   }
		return false;
	}
	private void gameEnd() {
		p1Point= 0;
		p2Point= 0;
		turn = 50;
		player = 0;
		time = 15;
		animation.stop();
		firstPane.getChildren().clear();
		firstPane.getChildren().add(Game());
	}
public class gameOverScene extends Application{
	public void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage st1) throws Exception {
	
		ImageView congratulation = new ImageView(new Image("photo/Congratulation-PNG-Picture.png"));
		congratulation.setTranslateX(50);
		congratulation.setFitWidth(300);
		congratulation.setFitHeight(150);
		Label lblGameEndMsg= new Label("The Game Has ended.");
		lblGameEndMsg.setPadding(new Insets(150,115,20,120));
		lblGameEndMsg.setStyle("-fx-font-family : 'impact';"
				+ "-fx-font-size: 20;"
				+ "-fx-text-fill: white;");
		Label lblWinnerMsg= new Label();
		lblWinnerMsg.setPadding(new Insets(180,80,20,80));
		lblWinnerMsg.setStyle("-fx-font-family : 'impact';"
				+ "-fx-font-size: 20;"
				+ "-fx-text-fill: white;");
		if (player==0) {
			lblWinnerMsg.setText("Player 1 has won with "+p1Point + " Points!!!");
			
		}else {
			lblWinnerMsg.setText("Player 2 has won with "+p2Point + " Points!!!");
		}
		Button btnClose = new Button("RESTART");
		btnClose.setPrefSize(80, 40);
		btnClose.setTranslateX(160);
		btnClose.setTranslateY(230);
		btnClose.setStyle("-fx-background-color: #4F92DF;"
				+ "-fx-background-radius 30;"
				+ "-fx-border-radius 30;"
				+ "-fx-font-family: 'impact';"
				+ "-fx-font-size: 18");
		Pane gameOver = new Pane();
		gameOver.setStyle("-fx-background-color : cyan;");
		gameOver.getChildren().addAll(congratulation,lblGameEndMsg,lblWinnerMsg,btnClose);
		gameOver.setPrefSize(400, 300);
		Scene sc = new Scene(gameOver);
		st1.setScene(sc);
		st1.show();
		btnClose.setOnAction(e->{
			gameEnd();
			try {
				Stage stage = (Stage) btnClose.getScene().getWindow();
				stage.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
	}
		
	}
}
