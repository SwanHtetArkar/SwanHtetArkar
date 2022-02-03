package games;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameOverScene extends Application{
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
		if (wplayer==0) {
			lblWinnerMsg.setText("Player 1 has won with "+p1Point + " Points!!!");
			
		}else {
			lblWinnerMsg.setText("Player 2 has won with "+p2Point + " Points!!!");
		}
		Button btnClose = new Button("Close");
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
			new WordChain().gameEnd();
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
