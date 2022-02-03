package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CustomerDebtListController {
	@FXML
	private Button btnCustomerDebtList;
	@FXML
	private Button btnSaleOrderReport;
	@FXML
    private TableView<CustomerDebtData> table;
    @FXML
    private TableColumn<CustomerDebtData, String> CustomerName;
    @FXML
    private TableColumn<CustomerDebtData, String> CustomerPhone;
    @FXML
    private TableColumn<CustomerDebtData, Integer> DebtAmount;
	@FXML
	private Label lblEnterName;
	@FXML
	private Label lblEnterPhno;
	@FXML
	private TextField txfDebtAmount;
	@FXML
	private TextField txfPaymentAmount;
	@FXML
	private TextField txfRemainingAmount;
	@FXML
	private TextField txfSearch;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnVendor;
	@FXML
	private Button btnInventory;
	@FXML
	private Button btnCustomer;
	@FXML
	private Button btnPOS;
	@FXML
	private Button btnUnit;
	@FXML
	private Button btnReport;
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
    void btnSaveOnAction(ActionEvent event) {
    
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
    void btnInventoryOnAction(ActionEvent event) {
    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "InventoryView.fxml");	
    }
    @FXML
    void btnPOSOnAction(ActionEvent event) {
    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "Pos.fxml");	
    }
    @FXML
    void btnCustomerOnAction(ActionEvent event) {
    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "Customer.fxml");	
    }
    @FXML
    void btnVendorOnAction(ActionEvent event) {
    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "VendorView.fxml");	
    }
    @FXML
    void btnUnitOnAction(ActionEvent event) {
    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitView.fxml");	
    }
    @FXML
    void btnUnitCategoryOnAction(ActionEvent event) {
    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitCategoryView.fxml");	
    }
    @FXML
    void btnReportOnAction(ActionEvent event) {
    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "SaleOrderReport.fxml");	
    }

	// Event Listener on Button[#btnCustomerDebtList].onAction
	@FXML
	public void btnCustomerDebtListOnAction(ActionEvent event) {
		new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "CustomerDebtList.fxml");	
	}
	// Event Listener on Button[#btnSaleOrderReport].onAction
	@FXML
	public void btnSaleOrderReportOnAction(ActionEvent event) {
		new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "SaleOrderReport.fxml");	
	}
	// Event Listener on TextField[#txfDebtAmount].onAction
	@FXML
	public void txfDebtAmountOnAction(ActionEvent event) {
		
	}
	// Event Listener on TextField[#txfPaymentAmount].onAction
	@FXML
	public void txfPaymentAmountOnAction(ActionEvent event) {
		System.out.println(txfPaymentAmount.getText());
	}
	// Event Listener on TextField[#txfRemainingAmount].onAction
	@FXML
	public void txfRemainingAmountOnAction(ActionEvent event) {
		
	}
	// Event Listener on TextField[#txfSearch].onAction
	@FXML
	public void txfSearchOnAction(ActionEvent event) {
		
	}
	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void btnCancleOnAction(ActionEvent event) {
		txfSearch.clear();
	}
	// Event Listener on Button[#btnSearch].onAction
	@FXML
	public void btnSearchOnAction(ActionEvent event) {
		ArrayList<CustomerDebtData> data = CustomerDebtListDBControl.getCustomer(txfSearch.getText());
		table.getItems().clear();
	        for (int i = 0; i < data.size(); i++) {
	        	table.getItems().add(data.get(i));
			}
	}
	@FXML
	public void initialize() {
		txfDebtAmount.setEditable(false);
		txfRemainingAmount.setEditable(false);
//For Table
	    CustomerName.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
	    CustomerPhone.setCellValueFactory(new PropertyValueFactory<>("Customer_Phone_Number"));
	    DebtAmount.setCellValueFactory(new PropertyValueFactory<>("Debt_Amount"));
//start up
	    ArrayList<CustomerDebtData> data1 = CustomerDebtListDBControl.getCustomer("");
		table.getItems().clear();
	        for (int i = 0; i < data1.size(); i++) {
	        	table.getItems().add(data1.get(i));
			}
	    
//listen to table
	    try {
	    	table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				table.getSelectionModel().clearSelection();
				CustomerDebtData selected = table.getSelectionModel().getSelectedItem();
				
				lblEnterName.setText(selected.getCustomer_Name());
				lblEnterPhno.setText(selected.getCustomer_Phone_Number());
				txfDebtAmount.setText(selected.getDebt_Amount()+"");
				txfRemainingAmount.setText(selected.getDebt_Amount()+"");
	    	});
		}catch (Exception e) {
			// TODO: handle exception
		}		
	    
//Payment 
	    txfPaymentAmount.textProperty().addListener((observable, oldValue, newValue) -> {
	    	if (!txfPaymentAmount.getText().isEmpty()) {
				if (!txfPaymentAmount.getText().matches("[0-9]*")) {
		    		new Alert(AlertType.WARNING,"Please Insert A Valid Number",ButtonType.OK).showAndWait();
				}else {
					txfRemainingAmount.setText(Integer.parseInt(txfDebtAmount.getText())-Integer.parseInt(txfPaymentAmount.getText())+"");
				}
			}else {
				txfRemainingAmount.setText(txfDebtAmount.getText());
			}
	    	
			
        });
//if save is pressed 
	    btnSave.setOnAction(e->{
	    	if (CustomerDebtListDBControl.PayDebt(POSDBcontrol.getCustomerID(lblEnterName.getText(), lblEnterPhno.getText()),
	    			Integer.parseInt(txfRemainingAmount.getText()))) {
	    		ArrayList<CustomerDebtData> data = CustomerDebtListDBControl.getCustomer("");
				table.getItems().clear();
			        for (int i = 0; i < data.size(); i++) {
			        	table.getItems().add(data.get(i));
					}
			        txfPaymentAmount.clear();
			        new Alert(AlertType.INFORMATION,"Debt Amount Updated",ButtonType.OK).showAndWait();
			}else {
				new Alert(AlertType.WARNING,"Debt Amount Cannot Be Updated",ButtonType.OK).showAndWait();
			}
	    	
	    	
	    });
	    
	}	
	    
	
}
