package games;

import java.awt.event.ActionEvent;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.corba.se.pept.transport.EventHandler;
import com.sun.prism.paint.Color;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TicTacToeGame extends Application{
	private Label lblTitle = new Label("Tic Tac Toe");
	private Label lbltimer = new Label("15s");
	private BorderPane root = new BorderPane();
	private Pane game = new Pane();
	private GridPane board = new GridPane();
	private int player = 0;
	private String boardMap[][] = new String[3][3];
	int time = 15;
	private	Timeline animation;
	Pane player1 = new Pane();
	Pane player2 = new Pane();
	private int turns = 0;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage st) throws Exception {
		animation = new Timeline(new KeyFrame(Duration.seconds(1), e->changeClock()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		Line l1 = new Line(0,100,300,100);
		Line l2 = new Line(0,200,300,200);
		Line l3 = new Line(100,0,100,300);
		Line l4 = new Line(200,0,200,300);
		game.getChildren().addAll(l1,l2,l3,l4,board,lblTitle,lbltimer);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int col = i;
				int row = j;
				boardMap[i][j]="-";
				Button play = new Button();
				board.add(play, i, j);
				
				play.setPrefSize(100, 100);
				play.setOpacity(0);
				play.setOnAction(e->{
					if (player==0) {
						boardMap[row][col] = "X";
						ImageView cross = new ImageView();
						cross.setImage(new Image("photo/red-cross-md.png"));
						
						cross.setTranslateX(10+(100*col));
						cross.setTranslateY(10+(100*row));
						cross.setFitWidth(80);
						cross.setFitHeight(80);
						game.getChildren().add(cross);
						player=1;
						turns++;
						play.setDisable(true);
						
						if (checkWinLose("X", boardMap)) {
							animation.stop();
							new Alert(AlertType.CONFIRMATION,"Player 1 is the winner", ButtonType.FINISH).showAndWait();
							
							restart();
							
						}
						checkIfDraw("X", boardMap);

						time=15;
							player=1;
							player2.setStyle("-fx-border-color: black;"
									+ "-fx-border-radius:10;"
									+ "-fx-background-color: lime;"
									+ "-fx-background-radius: 10");

							player1.setStyle("-fx-border-color: black;"
									+ "-fx-border-radius:10;");
						
					
					
					lbltimer.setText(String.format("%02d", time)+"s");
					}else if(player==1) {
						boardMap[row][col] = "O";

						ImageView circle = new ImageView();
						circle.setImage(new Image("photo/blue-circle.png"));
						
						circle.setTranslateX(10+(100*col));
						circle.setTranslateY(10+(100*row));
						circle.setFitWidth(80);
						circle.setFitHeight(80);
						game.getChildren().add(circle);
						player=0;
						turns++;
						play.setDisable(true);

						if (checkWinLose("O", boardMap)) {
							System.out.println("Win O");

							new Alert(AlertType.CONFIRMATION,"Player 2 is the winner", ButtonType.FINISH).showAndWait();
							
							restart();
						}

						checkIfDraw("O", boardMap);
						time=16;
							player=0;
							player1.setStyle("-fx-border-color: black;"
									+ "-fx-border-radius:10;"
									+ "-fx-background-color: lime;"
									+ "-fx-background-radius: 10");

							player2.setStyle("-fx-border-color: black;"
									+ "-fx-border-radius:10;");
					}
				});
				
//				play.setOnAction(e->{
//					
//				});
			}
		}
		
		lblTitle.setTranslateY(-60);
		lblTitle.setTranslateX(58);
		
		lbltimer.setTranslateY(330);
		lbltimer.setTranslateX(117);
		lbltimer.setFont(new Font("arial",35));
		lbltimer.setStyle("-fx-border-color: black;"
				+ "-fx-padding: 5;"
				+ "-fx-border-radius:5;");
		lblTitle.setFont(new Font("Algerian" ,35));
		root.setCenter(game);
		

		ImageView cross = new ImageView(new Image("photo/red-cross-md.png"));
		cross.setFitWidth(60);
		cross.setFitHeight(60);
		ImageView circle = new ImageView(new Image("photo/blue-circle.png"));
		circle.setFitWidth(60);	
		circle.setFitHeight(60);
		
		
		Label p1 = new Label("Player 1");
		Label p2 = new Label("Player 2");

		player1.getChildren().addAll(cross,p1);
		player2.getChildren().addAll(circle,p2);

		player1.setStyle("-fx-border-color: black;"
				+ "-fx-border-radius:10;"
				+ "-fx-background-color: lime;"
				+ "-fx-background-radius: 10");
		player1.setPrefSize(100, 400);
		cross.setTranslateX(20);
		cross.setTranslateY(10);
		p1.setTranslateX(10);
		p1.setTranslateY(75);
		p1.setFont(new Font("arial bold",20));

		player2.setStyle("-fx-border-color: black;"
				+ "-fx-border-radius:10;");
		player2.setPrefSize(100, 400);
		circle.setTranslateX(20);
		circle.setTranslateY(10);
		p2.setTranslateX(10);
		p2.setTranslateY(75);
		p2.setFont(new Font("arial bold",20));
		
		root.setRight(player2);
		root.setLeft(player1);
		
		Scene sc = new Scene(root);
		root.setPrefSize(600, 500);
		root.setPadding(new Insets(100,20,100,20));
		root.setMargin(game, new Insets(0,30,0,30));
		
		st.setScene(sc);
		st.sizeToScene();
		st.setTitle("Tic Tac Toe");
		st.show();
		
	}
		public boolean checkWinLose(String player,String[][] board) {
			try {
					
				if (board[0][0].contains(player) && board[0][1].contains(player) && board[0][2].contains(player)  ) {
					return true;
				}else if (board[1][0].contains(player) && board[1][1].contains(player) && board[1][2].contains(player) ) {
					return true;
				}else if (board[2][0].contains(player) && board[2][1].contains(player) && board[2][2].contains(player) ) {
				return true;
				}else if (board[0][0].contains(player) && board[1][0].contains(player) && board[2][0].contains(player) ) {
					return true;
				}else if (board[0][1].contains(player) && board[1][1].contains(player) && board[2][1].contains(player) ) {
					return true;
				}else if (board[0][2].contains(player) && board[1][2].contains(player) && board[2][2].contains(player) ) {
					return true;
				}else if (board[0][0].contains(player) && board[1][1].contains(player) && board[2][2].contains(player) ) {
					return true;
				}else if (board[0][2].contains(player) && board[1][1].contains(player) && board[2][0].contains(player) ) {
					return true;
				}else
				return false;
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return false;
		}
		private void changeClock() {
			if (time>0) {
				time--;
			}
			if(time==0) {
				time=15;
				if (player==0) {
					player=1;
					player2.setStyle("-fx-border-color: black;"
							+ "-fx-border-radius:10;"
							+ "-fx-background-color: lime;"
							+ "-fx-background-radius: 10");

					player1.setStyle("-fx-border-color: black;"
							+ "-fx-border-radius:10;");
				}else if(player==1) {
					player=0;
					player1.setStyle("-fx-border-color: black;"
							+ "-fx-border-radius:10;"
							+ "-fx-background-color: lime;"
							+ "-fx-background-radius: 10");

					player2.setStyle("-fx-border-color: black;"
							+ "-fx-border-radius:10;");
				}
			}
			
			lbltimer.setText(String.format("%02d", time)+"s");
		}
		public void restart(){
			turns=0;
			game.getChildren().clear();
			player=0;
			player1.setStyle("-fx-border-color: black;"
				+ "-fx-border-radius:10;"
				+ "-fx-background-color: lime;"
				+ "-fx-background-radius: 10");

			player2.setStyle("-fx-border-color: black;"
				+ "-fx-border-radius:10;");
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e->changeClock()));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					int col = i;
					int row = j;
					boardMap[i][j]="-";
					Button play = new Button();
					board.add(play, i, j);
					
					play.setPrefSize(100, 100);
					play.setOpacity(0);
					play.setOnAction(e->{
						if (player==0) {
							boardMap[row][col] = "X";
							ImageView cross = new ImageView();
							cross.setImage(new Image("photo/red-cross-md.png"));
							
							cross.setTranslateX(10+(100*col));
							cross.setTranslateY(10+(100*row));
							cross.setFitWidth(80);
							cross.setFitHeight(80);
							game.getChildren().add(cross);
							player=1;
							turns++;
							play.setDisable(true);
							
							if (checkWinLose("X", boardMap)) {
								animation.stop();
								new Alert(AlertType.CONFIRMATION,"Player 1 is the winner", ButtonType.FINISH).showAndWait();
							
			
								restart();
								
							}

							checkIfDraw("X", boardMap);

							time=15;
								player=1;
								player2.setStyle("-fx-border-color: black;"
										+ "-fx-border-radius:10;"
										+ "-fx-background-color: lime;"
										+ "-fx-background-radius: 10");

								player1.setStyle("-fx-border-color: black;"
										+ "-fx-border-radius:10;");
							
						
						
						lbltimer.setText(String.format("%02d", time)+"s");
						}else if(player==1) {
							boardMap[row][col] = "O";

							ImageView circle = new ImageView();
							circle.setImage(new Image("photo/blue-circle.png"));
							
							circle.setTranslateX(10+(100*col));
							circle.setTranslateY(10+(100*row));
							circle.setFitWidth(80);
							circle.setFitHeight(80);
							game.getChildren().add(circle);
							player=0;
							turns++;
							play.setDisable(true);

							if (checkWinLose("O", boardMap)) {
								System.out.println("Win O");

								new Alert(AlertType.CONFIRMATION,"Player 2 is the winner", ButtonType.FINISH).showAndWait();
								restart();
							}

							checkIfDraw("O", boardMap);
							time=16;
								player=0;
								player1.setStyle("-fx-border-color: black;"
										+ "-fx-border-radius:10;"
										+ "-fx-background-color: lime;"
										+ "-fx-background-radius: 10");

								player2.setStyle("-fx-border-color: black;"
										+ "-fx-border-radius:10;");
						}
					});
					
//					play.setOnAction(e->{
//						
//					});
				}
			}
			Line l1 = new Line(0,100,300,100);
			Line l2 = new Line(0,200,300,200);
			Line l3 = new Line(100,0,100,300);
			Line l4 = new Line(200,0,200,300);
			game.getChildren().addAll(l1,l2,l3,l4,board,lblTitle,lbltimer);
			
		}
		public void checkIfDraw(String player,String[][] board) {
			if (turns==9 && !checkWinLose(player, board)) {
				new Alert(AlertType.INFORMATION,"It is a Draw!!",ButtonType.OK).showAndWait();
				restart();
			}
		}
		
}
