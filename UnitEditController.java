package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;

public class UnitEditController {
	UnitData selected;
	int UnitID;
	int UnitCategoryID;
	
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private TextField txfUnitCode;
	@FXML
	private TextField txfUnitName;
	@FXML
	private TextArea txaUnitRemark;
	@FXML
	private ComboBox<String> cmbUnitCategoryName;
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
		if(checkData()) {
			if(UnitDBControl.editUnit(UnitID,txfUnitCode.getText(),txfUnitName.getText(),
					txaUnitRemark.getText(), UnitCategoryID)){
				
				new Alert(AlertType.INFORMATION, "Successfully Added!", ButtonType.OK).showAndWait();
				new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitView.fxml");
			}else {
	    		new Alert(AlertType.WARNING, "Unit Code Already Exist", ButtonType.OK).showAndWait();
	    	}
		}
	}
	// Event Listener on Button[#cancel].onAction
	@FXML
	public void cancelOnAction(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Unit");
		alert.setContentText("Would you like to return to the Unit List View?");
		ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
		        if ( type == okButton) {
		        	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitView.fxml");
		        } else{
		        	 txfUnitCode.clear();
		        	 txfUnitName.clear();
		        	 txaUnitRemark.clear();
		        	 
		        	 cmbUnitCategoryName.getItems().addAll(UnitCategoryDBControl.getUnitCategoryName());
		        	 UnitData selected =UnitViewController.getSelected();
		        	 txfUnitCode.setText(selected.getUnitCode());
		             txfUnitName.setText(selected.getUnitName());
		             txaUnitRemark.setText(selected.getUnitRemark());
		             cmbUnitCategoryName.getSelectionModel().select(selected.getUnitCategoryName());
		             UnitID=selected.getUnitID();
		             UnitCategoryID=UnitDBControl.getUnitCategoryID(cmbUnitCategoryName.getSelectionModel().getSelectedItem());
		        }
		});
	}
	// Event Listener on TextField[#txfUnitCode].onAction
	@FXML
	public void txfUnitCodeOnAction(ActionEvent event) {
		txfUnitCode.requestFocus();
	}
	// Event Listener on TextField[#txfUnitName].onAction
	@FXML
	public void txfUnitNameOnAction(ActionEvent event) {
		txfUnitName.requestFocus();
	}
	// Event Listener on ComboBox[#cmbUnitCategoryName].onAction
	@FXML
	public void cmbUnitCategoryNameOnAction(ActionEvent event) {
		cmbUnitCategoryName.requestFocus();
		UnitCategoryID=UnitDBControl.getUnitCategoryID(cmbUnitCategoryName.getSelectionModel().getSelectedItem());
	}
	
	@FXML
    void initialize() {
	 cmbUnitCategoryName.getItems().addAll(UnitCategoryDBControl.getUnitCategoryName());
	 UnitData selected =UnitViewController.getSelected();
	 txfUnitCode.setText(selected.getUnitCode());
     txfUnitName.setText(selected.getUnitName());
     txaUnitRemark.setText(selected.getUnitRemark());
     cmbUnitCategoryName.getSelectionModel().select(selected.getUnitCategoryName());
     UnitID=selected.getUnitID();
     UnitCategoryID=UnitDBControl.getUnitCategoryID(cmbUnitCategoryName.getSelectionModel().getSelectedItem());
     System.out.println(UnitID);
          
    }
	
	private boolean checkData() {
		if(txfUnitCode.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Unit Code should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}else
		if(!txfUnitCode.getText().matches("[a-zA-Z]{3}_\\d{3}")) { 
				new Alert(AlertType.WARNING,"Invalid Format!\neg.Uni_001",ButtonType.OK).showAndWait();
				txfUnitCode.selectAll();
				txfUnitCode.requestFocus();
				return false;
		}
		if(txfUnitName.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Unit Name should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}
		if(cmbUnitCategoryName.getSelectionModel().isEmpty()) {
			new Alert(AlertType.WARNING,"Please select your position.",ButtonType.OK).showAndWait();
			return false;
		}
		return true;
	}
}
