package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	static Stage st = new Stage();
	@Override
	public void start(Stage primaryStage) {
		try {
			st = primaryStage;
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("InventoryView.fxml"));
			Scene scene = new Scene(root,1366,768);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Tha Pyay Ni");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getSt() {
		return st;
	}

	public void setSt(Stage st) {
		this.st = st;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
