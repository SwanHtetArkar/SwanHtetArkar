package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class test extends Application{
	private Label lblItemname=new Label("ItemName");
	private ScrollBar ScrollBar=new ScrollBar();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane root= new GridPane();
		root.getChildren();
		root.add(lblItemname, 2, 0);
		root.add(ScrollBar, 1, 1, 3, 0);
		Scene sc=new Scene(root,700,570);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(sc);
		primaryStage.show();
	}

}
