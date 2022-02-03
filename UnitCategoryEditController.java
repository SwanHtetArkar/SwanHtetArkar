package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

public class UnitCategoryEditController {
	UnitCategoryData selected;
	int UnitCategoryID;
	
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private TextField txfUnitCategoryName;
	@FXML
	private TextArea txaUnitCategoryRemark;
	@FXML
	private Button btnMax;
	@FXML
	private Button btnMinimize;
	@FXML
	private Button btnClose;
	 
	double x,y;
	@FXML
	void dragged(MouseEvent event) {
		 Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		 stage.setX(event.getSceneX()-x);
		 stage.setY(event.getSceneY()-y);
	}

	@FXML
	void pressed(MouseEvent event) {
		 x=event.getSceneX();
		 y=event.getSceneY();
	}
	@FXML
	void btnCloseOnAction(ActionEvent event) {
		 Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		 stage.close();
	}
	@FXML
	void btnMaxOnAction(ActionEvent event) {
		 Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		 //stage.setFullScreenExitHint(" ");
		 stage.setFullScreen(true);
	}

	@FXML
	void btnMinimizeOnAction(ActionEvent event) {
		 Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		 stage.setIconified(true);
	}

	// Event Listener on Button[#save].onAction
	@FXML
	public void saveOnAction(ActionEvent event) {
		// TODO Autogenerated
		if(UnitCategoryDBControl.editUnitCategory(UnitCategoryID, txfUnitCategoryName.getText(), 
				txaUnitCategoryRemark.getText())){
			checkData();
			new Alert(AlertType.INFORMATION, "Successfully Added!", ButtonType.OK).showAndWait();
			new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitCategoryView.fxml");
		}else {
    		new Alert(AlertType.WARNING, "Failed Editing!", ButtonType.OK).showAndWait();
    	}
	}
	// Event Listener on Button[#cancel].onAction
	@FXML
	public void cancelOnAction(ActionEvent event) {
		// TODO Autogenerated
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Unit Category");
		alert.setContentText("Would you like to return to the Unit Category List View?");
		ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
		        if ( type == okButton) {
		        	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitCategoryView.fxml");
		        } else{
		        	txfUnitCategoryName.clear();
		        	txaUnitCategoryRemark.clear();
		        	
		        	UnitCategoryData selected =UnitCategoryViewController.getSelected(); 
		        	txfUnitCategoryName.setText(selected.getUnitCategoryName()); 
		        	txaUnitCategoryRemark.setText(selected.getUnitCategoryRemark()); 
		        	UnitCategoryID=UnitCategoryDBControl.selctedUnitCategoryID(selected.getUnitCategoryName() 
			        		  , selected.getUnitCategoryRemark()); 
		        }
		});
	}
	// Event Listener on TextField[#txfUnitCategoryName].onAction
	@FXML
	public void txfUnitCategoryNameOnAction(ActionEvent event) {
		// TODO Autogenerated
		txfUnitCategoryName.requestFocus();
	}
	 @FXML
	    void initialize() {
	          
	          UnitCategoryData selected =UnitCategoryViewController.getSelected();
	          txfUnitCategoryName.setText(selected.getUnitCategoryName());
	          txaUnitCategoryRemark.setText(selected.getUnitCategoryRemark());
	          UnitCategoryID=UnitCategoryDBControl.selctedUnitCategoryID(selected.getUnitCategoryName()
	        		  , selected.getUnitCategoryRemark());
	          
	    }
	 private boolean checkData() {
		 if(txfUnitCategoryName.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Unit Name should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}
			return true;
		}
}
