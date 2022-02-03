package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CustomerUpdateController {
	public int selectedID = 0;
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private Label CustomerName;
	@FXML
	private TextField txfCustomerName;
	@FXML
	private TextArea txaCustomerAddress;
	@FXML
	private ComboBox<String> cmbTownship;
	@FXML
	private Label CustomerAddress;
	@FXML
	private Label CustomerPhone;
	@FXML
	private TextField txfCustomerPhone;
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
	

	@FXML
	public void saveOnAction(ActionEvent event) {
		if(checkData()) {
    		if(CustomerUpdateDBControl.UpdateCustomer(selectedID, txfCustomerName.getText(), txaCustomerAddress.getText(), txfCustomerPhone.getText())) {
        		new Alert(AlertType.INFORMATION, "Successfully Added!", ButtonType.OK).showAndWait();
        		new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "CustomerView.fxml");
        	}else {
        		new Alert(AlertType.WARNING, "Failed Register!", ButtonType.OK).showAndWait();
        	}
		}
	}
	@FXML
	public void cancelOnAction(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Customer");
		alert.setContentText("Would you like to return to the Customer List View?");
		ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
		        if ( type == okButton) {
		        	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "Customer.fxml");
		        } else{
		        	txfCustomerName.clear(); 
		        	txaCustomerAddress.clear(); 
		        	txfCustomerPhone.clear(); 
		        	
		        	CustomerData selected = CustomerViewController.getSelected();
		    		selectedID = selected.getCustomer_ID();
		    		txfCustomerName.setText(selected.getCustomer_Name());
		    		txaCustomerAddress.setText(selected.getCustomer_Address());
		    		txfCustomerPhone.setText(selected.getCustomer_Phone());
		    		cmbTownship.getSelectionModel().select(selected.getTownshipName());
		    		cmbTownship.getItems().addAll(CustomerViewDBControl.getTownship());
		    }
		});
	}
	@FXML
	public void txfCutomerNameOnAction(ActionEvent event) {
		txfCustomerName.requestFocus();
	}
	@FXML
	public void txfCustomerPhoneOnAction(ActionEvent event) {
		txfCustomerPhone.requestFocus();
	}
	@FXML
	public void cmbTownshipOnAction(ActionEvent event) {
		//
	}
	@FXML
	public void initialize() {
		CustomerData selected = CustomerViewController.getSelected();
		selectedID = selected.getCustomer_ID();
		txfCustomerName.setText(selected.getCustomer_Name());
		txaCustomerAddress.setText(selected.getCustomer_Address());
		txfCustomerPhone.setText(selected.getCustomer_Phone());
		cmbTownship.getSelectionModel().select(selected.getTownshipName());
		cmbTownship.getItems().addAll(CustomerViewDBControl.getTownship());
	}
	
	 private boolean checkData() {
	    	if(txfCustomerName.getText().isEmpty()) {
				new Alert(AlertType.WARNING,"Name should not be empty!",ButtonType.OK).showAndWait();
				txfCustomerName.requestFocus();
				txfCustomerName.selectAll();
				return false;
			}
	    	if(txaCustomerAddress.getText().isEmpty()) {
				new Alert(AlertType.WARNING,"Address should not be empty!",ButtonType.OK).showAndWait();
				txaCustomerAddress.requestFocus();
				txaCustomerAddress.selectAll();
				return false;
			}
	    	if(txfCustomerPhone.getText().isEmpty()) {
	    		new Alert(AlertType.WARNING,"Phone number should not be empty!",ButtonType.OK).showAndWait();
	    		return false;
	    	}
	    	if(!txfCustomerPhone.getText().matches("\\d{2}-\\d{6,10}")) {
				new Alert(AlertType.WARNING,"Invalid phone number format!\n"
						+ " Eg.XX-XXXXXX...",ButtonType.OK).showAndWait();
				txfCustomerPhone.requestFocus();
				txfCustomerPhone.selectAll();
				return false;
			}
			return true;
		}
}
