package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AskForUnit extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	AnchorPane root = new AnchorPane();
	TextField txfUnit = new TextField();
	Label lblUnit = new Label("Unit");

	@Override
	public void start(Stage ps) throws Exception {
	
		
		
		Scene sc = new Scene(root);
		ps.setScene(sc);
		ps.setTitle("Select Unit");
	}
}
