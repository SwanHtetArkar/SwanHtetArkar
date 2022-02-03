package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class InventoryNewController {
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private Label item_Code;
	@FXML
	private TextField txfItemCode;
	@FXML
	private TextField txfItemName;
	@FXML
	private Label item_Name;
	@FXML
	private Label description;
	@FXML
	private Label price_Purchased;
	@FXML
	private TextField txfPricePurchased;
	@FXML
	private TextField txfPriceSelling;
	@FXML
	private Label price_Selling;
	@FXML
	private TextField txfPriceCustomer;
	@FXML
	private Label price_Customer;
	@FXML
	private Label quantity;
	@FXML
	private Label default_Quantity;
	@FXML
	private Label minimum;
	@FXML
	private Label unit_ID;
	@FXML
	private TextField txfBrand;
	@FXML
	private Label brand_ID;
	@FXML
	private TextField txfUnit;
	@FXML
	private TextField txfCustomizeQuantity;
	@FXML
	private Spinner<Integer> spinnerQuantity;
	@FXML
	private Spinner<Integer> spinnerMinimum;
	@FXML
	private Spinner<Integer> spinnerDefaultQuantity;
	@FXML
	private TextArea txaDescription;
	@FXML
    private ComboBox<String> cmbUnit;
    @FXML
    private ComboBox<String> cmbBrand;
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
	public void btnSaveOnAction(ActionEvent event) {
		if(checkData()) {
			if (txfCustomizeQuantity.getText().isEmpty()) {
				if (ItemDBControl.saveItem(txfItemCode.getText(), txfItemName.getText(), txaDescription.getText(),
						Double.parseDouble(txfPricePurchased.getText()), Double.parseDouble(txfPriceSelling.getText()),
						Double.parseDouble(txfPriceCustomer.getText()), 
						spinnerQuantity.getValue(),spinnerDefaultQuantity.getValue(),
						spinnerMinimum.getValue(), cmbBrand.getSelectionModel().getSelectedIndex()+1,
						ItemDBControl.getUnitID(cmbUnit.getSelectionModel().getSelectedItem()))) {
					new Alert(AlertType.CONFIRMATION,"Saved Successfully",ButtonType.OK).showAndWait();
					new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "InventoryView.fxml");
				}else {
	        		new Alert(AlertType.WARNING, "Failed Register!", ButtonType.OK).showAndWait();
	        	}
			}else {
				if (ItemDBControl.saveItem(txfItemCode.getText(), txfItemName.getText(), txaDescription.getText(),
					Double.parseDouble(txfPricePurchased.getText()), Double.parseDouble(txfPriceSelling.getText()),
					Double.parseDouble(txfPriceCustomer.getText()), 
					spinnerQuantity.getValue(),txfCustomizeQuantity.getText(), spinnerDefaultQuantity.getValue(),
					spinnerMinimum.getValue(), cmbBrand.getSelectionModel().getSelectedIndex()+1,
					ItemDBControl.getUnitID(cmbUnit.getSelectionModel().getSelectedItem()))) {
					new Alert(AlertType.CONFIRMATION,"Saved Successfully",ButtonType.OK).showAndWait();
					new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "InventoryView.fxml");
				}else {
	        		new Alert(AlertType.WARNING, "Failed Register!", ButtonType.OK).showAndWait();
	        	}
			}
			
		}
	}
	// Event Listener on Button[#cancel].onAction
	@FXML
	public void btnCancelOnAction(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Inventory");
		alert.setContentText("Would you like to return to the Inventory List View?");
		ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
		        if ( type == okButton) {
		        	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "InventoryView.fxml");
		        } else{
		        	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "InventoryNew.fxml");
		        }
		});
	}
	// Event Listener on TextField[#txfItemCode].onAction
	@FXML
	public void txfItemCodeOnAction(ActionEvent event) {
		txfItemCode.requestFocus();
	}
	// Event Listener on TextField[#txfItemName].onAction
	@FXML
	public void txfItemNameOnAction(ActionEvent event) {
		txfItemName.requestFocus();
	}
	// Event Listener on TextField[#txfCustomizeQuantity].onAction
	@FXML
	public void txfCustomizeQuantityOnAction(ActionEvent event) {
		txfCustomizeQuantity.requestFocus();
	}
	// Event Listener on TextField[#txfPricePurchased].onAction
	@FXML
	public void txfPricePurchasedOnAction(ActionEvent event) {
		txfPricePurchased.requestFocus();
	}
	// Event Listener on TextField[#txfPriceSelling].onAction
	@FXML
	public void txfPriceSellingOnAction(ActionEvent event) {
		txfPriceSelling.requestFocus();
	}
	// Event Listener on TextField[#txfPriceCustomer].onAction
	@FXML
	public void txfPriceCustomerOnAction(ActionEvent event) {
		txfPriceCustomer.requestFocus();
	}
	// Event Listener on TextField[#txfBrand].onAction
	@FXML
	public void txfBrandOnAction(ActionEvent event) {
		txfBrand.requestFocus();
	}
	// Event Listener on TextField[#txfUnit].onAction
	@FXML
	public void txfUnitOnAction(ActionEvent event) {
		txfUnit.requestFocus();
	}
	@FXML
    void cmbBrandOnAction(ActionEvent event) {
    	cmbBrand.requestFocus();
    }

    @FXML
    void cmbUnitOnAction(ActionEvent event) {
    	cmbUnit.requestFocus();
    }
	@FXML
	void initialize() {
		//Spinner 
		SpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
		spinnerQuantity.setValueFactory(factory);
		spinnerQuantity.setEditable(true);
		TextFormatter formatter = new TextFormatter(factory.getConverter(), factory.getValue());
		spinnerQuantity.getEditor().setTextFormatter(formatter);
		factory.valueProperty().bindBidirectional(formatter.valueProperty());
		
		SpinnerValueFactory factory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
		spinnerMinimum.setValueFactory(factory1);
		spinnerMinimum.setEditable(true);
		TextFormatter formatter1 = new TextFormatter(factory1.getConverter(), factory1.getValue());
		spinnerMinimum.getEditor().setTextFormatter(formatter1);
		factory1.valueProperty().bindBidirectional(formatter1.valueProperty());
		
		SpinnerValueFactory factory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
		spinnerDefaultQuantity.setValueFactory(factory2);
		spinnerDefaultQuantity.setEditable(true);
		TextFormatter formatter2 = new TextFormatter(factory2.getConverter(), factory2.getValue());
		spinnerDefaultQuantity.getEditor().setTextFormatter(formatter2);
		factory2.valueProperty().bindBidirectional(formatter2.valueProperty());
		//spinner
		
		//combo box
		cmbBrand.getItems().addAll(ItemDBControl.getBrandName());
		cmbUnit.getItems().addAll(ItemDBControl.getUnitName());
		//controller box
	}
	
	private boolean checkData() {
		if(txfItemCode.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Item Code should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}else
		if(!txfItemCode.getText().matches("[a-zA-Z]{3}_\\d{3}")) {
				new Alert(AlertType.WARNING,"Invalid Format!\neg.Ite_001",ButtonType.OK).showAndWait();
				txfItemCode.selectAll();
				txfItemCode.requestFocus();
				return false;
		}else{
			ArrayList<String> itemcode=ItemDBControl.getItemCode();
			for(int j=0;j<itemcode.size();j++) {
				if(txfItemCode.getText().toLowerCase().equals(itemcode.get(j).toLowerCase())){ 
					new Alert(AlertType.WARNING,"This Item Code already exist!",ButtonType.OK).showAndWait();
					return false;
				}
			}
		}
		if(txfItemName.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Item Name should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}
		if(txfPricePurchased.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Purchase Price should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}else
		if(!txfPricePurchased.getText().matches("[0-9]{0,9}")) {
			new Alert(AlertType.WARNING,"Purchase Price must be numbers!",ButtonType.OK).showAndWait();
			txfPricePurchased.selectAll();
			txfPricePurchased.requestFocus();
			return false;
		}
		
		if(txfPriceSelling.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Selling Price should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}else
		if(!txfPriceSelling.getText().matches("[0-9]{0,9}")) {
				new Alert(AlertType.WARNING,"Selling Price must be numbers!",ButtonType.OK).showAndWait();
				txfPriceSelling.selectAll();
				txfPriceSelling.requestFocus();
				return false;
		}
		
		if(txfPriceCustomer.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Customer Price should not be empty!",ButtonType.OK).showAndWait();
			return false;
		}else
		if(!txfPriceCustomer.getText().matches("[0-9]{0,9}")) {
				new Alert(AlertType.WARNING,"Customer Price must be numbers!",ButtonType.OK).showAndWait();
				txfPriceCustomer.selectAll();
				txfPriceCustomer.requestFocus();
				return false;
		}
		if(spinnerQuantity.getValue()==null) {
			new Alert(AlertType.WARNING,"Quantity should not be empty.",ButtonType.OK).showAndWait();
			return false;
		}
		if(spinnerDefaultQuantity.getValue()==null) {
			new Alert(AlertType.WARNING,"Default Quantity should not be empty.",ButtonType.OK).showAndWait();
			return false;
		}
		if(spinnerMinimum.getValue()==null) {
			new Alert(AlertType.WARNING,"Minimum should not be empty.",ButtonType.OK).showAndWait();
			return false;
		}
		if(cmbUnit.getSelectionModel().isEmpty()) {
			new Alert(AlertType.WARNING,"Select unit.",ButtonType.OK).showAndWait();
			return false;
		}
		if(cmbBrand.getSelectionModel().isEmpty()) {
			new Alert(AlertType.WARNING,"Select brand.",ButtonType.OK).showAndWait();
			return false;
		}
		return true;
	}
}
