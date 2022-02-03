package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.mysql.jdbc.Blob;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

public class VendorInputController {
	@FXML
	private ImageView icon;
	@FXML
	private FontAwesomeIcon Close;
	@FXML
	private FontAwesomeIcon Minimize;
	@FXML
	private Button btnMax;
	@FXML
	private Button btnMinimize;
	@FXML
	private Button btnClose;
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private Button btnPhoto; 
	@FXML 
	private Label VendorCode;
	@FXML
	private TextField txfVendorCode;
	@FXML
	private Label VendorName;
	@FXML
	private Label VendorAddress;
	@FXML
	private ImageView Save;
	@FXML
	private ImageView Cancel;
	@FXML
	private TextField txfVendorName;
	@FXML
	private TextArea txaVendorAddress;
	@FXML
	private Label VendorPhoneNumber;
	@FXML
	private TextField txfVendorPhone;
	@FXML
	private TextField txfVendorPhoto;
	@FXML
	private Button btnBrowse;
	@FXML
	private AnchorPane SavePhoto;
	@FXML
	private ImageView ImageSave;
	
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
			if (!txfVendorPhoto.getText().isEmpty()) {
				if(VendorDBControl.RegisterVendor(txfVendorName.getText(), txaVendorAddress.getText(), txfVendorPhone.getText(),txfVendorName.getText()+" .jpg")){
	        		new Alert(AlertType.INFORMATION, "Successfully Added!", ButtonType.OK).showAndWait();
	        		new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "VendorView.fxml");
					VendorPhotoFileController.createPhoto(txfVendorPhoto.getText(), txfVendorName.getText());
	
	        	}else {
	        		new Alert(AlertType.WARNING, "Failed Register!", ButtonType.OK).showAndWait();
	        	}
			}else {
				if(VendorDBControl.RegisterVendor(txfVendorName.getText(), txaVendorAddress.getText(), txfVendorPhone.getText(),"")){
	        		new Alert(AlertType.INFORMATION, "Successfully Added!", ButtonType.OK).showAndWait();
	        		new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "VendorView.fxml");
					VendorPhotoFileController.createPhoto(txfVendorPhoto.getText(), txfVendorName.getText());
	
	        	}else {
	        		new Alert(AlertType.WARNING, "Failed Register!", ButtonType.OK).showAndWait();
	        	}
			}
    		
		}
	}
	// Event Listener on Button[#cancel].onAction
	@FXML
	public void cancelOnAction(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Vendor");
		alert.setContentText("Would you like to return to the Vendor List View?");
		ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
		        if ( type == okButton) {
		        	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "VendorView.fxml");
		        } else{
		        	txfVendorName.clear();
		        	txaVendorAddress.clear();
		    		txfVendorPhone.clear();
		    		txfVendorPhoto.clear();
		        }
		});
	}
	// Event Listener on TextField[#txfVendorCode].onAction
	@FXML
	public void txaVendorAddressOnAction(ActionEvent event) {
		txaVendorAddress.requestFocus();
	}
	// Event Listener on TextField[#txfVendorName].onAction
	@FXML
	public void txfVendorNameOnAction(ActionEvent event) {
		txfVendorName.requestFocus();
	}
	// Event Listener on TextField[#txfVendorPhone].onAction
	@FXML
	public void txfVendorPhoneOnAction(ActionEvent event) {
		txfVendorPhone.requestFocus();
	}
	// Event Listener on TextField[#txfVendorPhoto].onAction
	@FXML
	public void txfVendorPhotoOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	@FXML 
	public void btnPhotoOnAction(ActionEvent event) { 
		// TODO Autogenerated 
	} 
	// Event Listener on Button[#btnBrowse].onAction
	@FXML
	public void btnBrowseOnAction(ActionEvent event) throws FileNotFoundException {
		FileChooser filechooser = new FileChooser();
		File selectedFile = filechooser.showOpenDialog(new Stage());
		txfVendorPhoto.setText(selectedFile.getAbsolutePath());

		
		
		ImageSave.setImage(new Image(new FileInputStream(selectedFile.getAbsolutePath()))); 

//		if (VendorImage== null) {
//			System.out.println("Not Inserted");
//		}
	}
	
	private boolean checkData() {
		if(txfVendorName.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Name should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}
		if(txaVendorAddress.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Address should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}
		if(!txfVendorPhone.getText().matches("\\d{2}-\\d{6,10}")) {
			new Alert(AlertType.WARNING,"Invalid phone number format!\n"
					+ " Eg.XX-XXXXXX...",ButtonType.OK).showAndWait();
			txfVendorPhone.requestFocus();
			txfVendorPhone.selectAll();
			return false;
		}
		return true;
	}
}
