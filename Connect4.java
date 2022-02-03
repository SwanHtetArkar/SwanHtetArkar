package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Connect4 extends Application{

	private BorderPane root = new BorderPane();
	private Pane game = new Pane();
	private boolean redMove = true;
	private Label lblTitle = new Label("Connect Four");
	private Pane discRoot = new Pane();
	private Disc[][] grid= new Disc[7][6];
	private	Timeline animation1;
	private Pane bottomPane = new Pane();
	private int time = 0;
	private Label lblTimer = new Label("00 : 00");
	private int minutes=0;
	private Label player1 = new Label("Player 1");
	private Label player2 = new Label("Player 2");
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage st) throws Exception {
		// TODO Auto-generated method stub
		game.getChildren().add(discRoot);
		root.setCenter(game);
		game();

		lblTitle.setTranslateY(-40);
		lblTitle.setTranslateX(160);
		lblTitle.setStyle("-fx-font-family: 'Lucida Console';"
				+ "-fx-font-size: 30;"
				+ "-fx-font-weight: bold");
	
		

		animation1 = new Timeline(new KeyFrame(Duration.seconds(1), e->changeClock()));
		animation1.setCycleCount(Timeline.INDEFINITE);
		animation1.play();

		player1.setStyle("-fx-border-color: black;"
				+ "-fx-border-radius: 10;"
				+ "-fx-font-size: 20;"
				+ "-fx-font-family: 'Castellar';"
				+ "-fx-padding: 10 10 10 10;"
				+ "-fx-background-color: red;"
				+ "-fx-background-radius: 10;"
				+ "-fx-text-fill: white;"
				);
		player1.setEffect(new DropShadow(5,8,8, Color.GREY));
		player1.setTranslateY(-8);
		player2.setStyle("-fx-border-color: black;"
				+ "-fx-border-radius: 10;"
				+ "-fx-font-size: 20;"
				+ "-fx-font-family: 'Castellar';"
				+ "-fx-padding: 10 10 10 10;"
				+ "-fx-background-color: white;"
				+ "-fx-background-radius: 10;"
				);

		player2.setEffect(new DropShadow(2,3,3, Color.GREY));
		player2.setTranslateX(400);

		lblTimer.setStyle("-fx-border-color: black;"
				+ "-fx-border-radius: 5;"
				+ "-fx-font-size: 20;"
				+ "-fx-font-family: 'Consolas';"
				+ "-fx-padding: 10 10 10 10;"
				+ "-fx-background-color: green;"
				+ "-fx-background-radius: 5;"
				+ "-fx-text-fill: white;");
		lblTimer.setPrefWidth(100);
		
		lblTimer.setTranslateX(210);
		
		game.getChildren().add(lblTitle);
		bottomPane.getChildren().addAll(player1,lblTimer,player2);
		game.getChildren().addAll(makecolumns());
		root.setBottom(bottomPane);
		root.setPrefSize(600, 650);
		root.setPadding(new Insets(70,38,50,38));
		root.setStyle("-fx-background-color: silver");
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.show();
	}
	public java.util.List<Rectangle> makecolumns(){
		 java.util.List<Rectangle> list = new ArrayList<>();
		 
		 for (int i = 0; i < 7; i++) {
			Rectangle rect = new Rectangle(70,70*6.5);
			rect.setTranslateX(15+(70*i));
			rect.setFill(Color.TRANSPARENT);
			rect.setOnMouseEntered(e->rect.setFill(Color.rgb(200, 200, 50, 0.3)));
			rect.setOnMouseExited(e->rect.setFill(Color.TRANSPARENT));
			final int column = i;
			rect.setOnMouseClicked(e->{
				rect.setDisable(true);
				placeDisc(new Disc(redMove),column,rect);
				if (redMove) {

					player2.setStyle("-fx-border-color: black;"
							+ "-fx-border-radius: 10;"
							+ "-fx-font-size: 20;"
							+ "-fx-font-family: 'Castellar';"
							+ "-fx-padding: 10 10 10 10;"
							+ "-fx-background-color: yellow;"
							+ "-fx-background-radius: 10;"
							);
					player2.setEffect(new DropShadow(5,8,8, Color.GREY));
					player2.setTranslateY(-8);
					player1.setStyle("-fx-border-color: black;"
							+ "-fx-border-radius: 10;"
							+ "-fx-font-size: 20;"
							+ "-fx-font-family: 'Castellar';"
							+ "-fx-padding: 10 10 10 10;"
							+ "-fx-background-color: white;"
							+ "-fx-background-radius: 10;"
							);

					player1.setEffect(new DropShadow(2,3,3, Color.GREY));
					player1.setTranslateY(0);
					

				}else {

					player1.setStyle("-fx-border-color: black;"
							+ "-fx-border-radius: 10;"
							+ "-fx-font-size: 20;"
							+ "-fx-font-family: 'Castellar';"
							+ "-fx-padding: 10 10 10 10;"
							+ "-fx-background-color: red;"
							+ "-fx-background-radius: 10;"
							+ "-fx-text-fill: white;"
							);
					player1.setEffect(new DropShadow(5,8,8, Color.GREY));
					player1.setTranslateY(-8);
					player2.setStyle("-fx-border-color: black;"
							+ "-fx-border-radius: 10;"
							+ "-fx-font-size: 20;"
							+ "-fx-font-family: 'Castellar';"
							+ "-fx-padding: 10 10 10 10;"
							+ "-fx-background-color: white;"
							+ "-fx-background-radius: 10;"
							);

					player2.setEffect(new DropShadow(2,3,3, Color.GREY));
					player2.setTranslateY(0);
					
				}
			});
			list.add(rect);
		}
		 return list; 
	}
	public void placeDisc(Disc disc ,int column,Shape rect) {
		int row = 5;
		do {
			if(!getDisc(column, row).isPresent())
				break;
			row--;
		} while (row>=0);
		if(row<0) {
			 return;
		}
		grid[column][row] = disc;
		final int currentRow = row;
		discRoot.getChildren().add(disc);
		disc.setTranslateX(15+(70*column));
		
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5),disc);
		animation.setToY(15+(70*row));
		animation.setOnFinished(e->{
			if (gameEnded(column,currentRow)) {
				gameOver();
			}
			rect.setDisable(false);
			redMove = !redMove;
		});
		animation.play();
	}
	public boolean  gameEnded(int column, int row) {
		java.util.List<Point2D> vertical = IntStream.rangeClosed(row-3, row+3)
				.mapToObj(r -> new Point2D(column, r)).collect(Collectors.toList());
		
		java.util.List<Point2D> horizontal = IntStream.rangeClosed(column-3, column+3)
				.mapToObj(c -> new Point2D(c, row)).collect(Collectors.toList());

		Point2D topLeft = new Point2D(column-3, row - 3);
		List<Point2D> diagonal1 = IntStream.rangeClosed(0,6)
				.mapToObj(i-> topLeft.add(i,i)).collect(Collectors.toList());
		
		Point2D bottomLeft = new Point2D(column-3, row + 3);
		List<Point2D> diagonal2 = IntStream.rangeClosed(0,6)
				.mapToObj(i-> bottomLeft.add(i,-i)).collect(Collectors.toList());
		
		return checkRange(vertical) ||checkRange(horizontal) 
				||checkRange(diagonal1) ||checkRange(diagonal2) ;
	}
	public boolean checkRange(java.util.List<Point2D> points) {
		int chain = 0;
		for (Point2D p : points) {
			int column  = (int) p.getX();
			int row = (int)p.getY();
			
			Disc disc = getDisc(column, row).orElse(new Disc(!redMove));
			if (disc.red == redMove) {
				chain++;
				if (chain == 4) {
					return true;
				}
			}else {
				chain =0;
			}
		}
		return false;
	}
	private void gameOver() {
		System.out.println("winner"+ (redMove ? "RED": "Yellow"));
		discRoot.getChildren().clear();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				grid[j][i]= null;
			}		
		}
		
		game();
	}
	private Optional<Disc> getDisc(int column ,int row){
		if (column <0 || column >=7||
				row <0 || row>=6 ) {
			return Optional.empty();
		}
		return Optional.ofNullable(grid[column][row]);
	}

	public void game() {
		time = 0;
		minutes = 0;
		Shape rect = new Rectangle(70*7.5,70*6.5);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				
				
				Shape circle = new Circle(50+(70*j),50+(70*i), 30,Color.GRAY);
//				game.getChildren().add(circle);
//			    PointLight light = new PointLight();
//			    light.setColor(Color.RED);
//			    Group lightGroup = new Group();
//			    lightGroup.getChildren().add(light);
			    rect = Shape.subtract(rect, circle);

			}
		}
		Light.Distant light= new Light.Distant();
		light.setAzimuth(45.0);
		light.setElevation(30);
		
		Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(5);
		
		rect.setEffect(lighting);
		rect.setFill(Color.BLUE);

		game.getChildren().add(rect);
	}

	private void changeClock() {
		time++;
		lblTimer.setText(String.format("%02d : %02d",minutes, time));
		if (time==60) {
			time=0;
			minutes++;
		}
	}
		
	
	public static class Disc extends Circle{
		private final boolean red;
		public Disc(boolean red) {
			super(30,red ? Color.RED: Color.YELLOW);
			this.red = red;
			
			setCenterX(35);
			setCenterY(35);
		}
		
	}
}
