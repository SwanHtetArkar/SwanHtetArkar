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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class UnitCategoryViewController {
	private static UnitCategoryData selected; 
	
    public static UnitCategoryData getSelected() {
		return selected;
	}

	public static void setSelected(UnitCategoryData selected) {
		UnitCategoryViewController.selected = selected;
	}
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
	@FXML
	private TextField txfSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnCancle;
	@FXML
	private TableView<UnitCategoryData> table;
//	@FXML
//	private TableColumn<Integer,UnitCategoryData> UnitCategoryID;
	@FXML
	private TableColumn<String,UnitCategoryData> UnitCategoryName;
	@FXML
	private TableColumn<String,UnitCategoryData> UnitCategoryRemark;
    @FXML
    private Button btnInventory;
    @FXML
    private Button btnPOS;
    @FXML
    private Button btnCustomer;
    @FXML
    private Button btnVendor;
    @FXML
    private Button btnUnit;
	@FXML
	private Button btnReport;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnAddNew;
	@FXML
	private Button btnFastBackward;
	@FXML
	private Button btnBackward;
	@FXML
	private Button btnStepBackward;
	@FXML
	private Button btnStepForward;
	@FXML
	private Button btnForward;
	@FXML
	private Button btnFastForward;
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

	// Event Listener on TextField[#txfSearch].onAction
	@FXML
	public void txfSearchOnAction(ActionEvent event) {
		txfSearch.requestFocus();
	}
	// Event Listener on Button[#btnSearch].onAction
	@FXML
	public void btnSearchOnAction(ActionEvent event) {
		ArrayList<UnitCategoryData> data = UnitCategoryDBControl.UnitCategorysearchFunction(txfSearch.getText());
		if(data.isEmpty()) {
			new Alert(AlertType.INFORMATION, "No Such Data Exists", ButtonType.OK).showAndWait();
		}else {
			table.getItems().clear();
	        for (int i = 0; i < data.size(); i++) {
				table.getItems().add(data.get(i));	
			}
		} 
	}
	// Event Listener on Button[#btnCancle].onAction
	@FXML
	public void btnCancleOnAction(ActionEvent event) {
		txfSearch.clear();
	}
	// Event Listener on Button[#btnUpdate].onAction
	@FXML
	public void btnUpdateOnAction(ActionEvent event) {
		if (table.getSelectionModel().getSelectedItem()== null) {
			new Alert(AlertType.WARNING,"Please select one row!!!",ButtonType.OK).showAndWait();
		} else {
			selected= table.getSelectionModel().getSelectedItem();
			new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitCategoryEdit.fxml");
		}
    	
	}
	// Event Listener on Button[#btnDelete].onAction
	@FXML
	public void btnDeleteOnAction(ActionEvent event) {
		if (table.getSelectionModel().getSelectedItem()== null) {
			new Alert(AlertType.WARNING,"Please select one row!!!",ButtonType.OK).showAndWait();
		}else{
			selected= table.getSelectionModel().getSelectedItem();
			if(UnitCategoryDBControl.DeleteUnitCategory(selected.getUnitCategoryID())) {
				table.getItems().clear();
				new Alert(AlertType.INFORMATION,"Successfully deleted!",ButtonType.OK).showAndWait();
				ArrayList<UnitCategoryData> data = UnitCategoryDBControl.getUnitCategoryData();
	  	        for (int i = 0; i < data.size(); i++) {
	  				table.getItems().add(data.get(i));
	  	        }
	  		}else {
	  			new Alert(AlertType.WARNING,"Row cannot be deleted.",ButtonType.OK).showAndWait();
	  		}
		}
		
	}
	// Event Listener on Button[#btnAddNew].onAction
	@FXML
	public void btnAddNewOnAction(ActionEvent event) {
		new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitCategoryInput.fxml");
	}
	// Event Listener on Button[#btnFastBackward].onAction
	@FXML
	public void btnFastBackwardOnAction(ActionEvent event) {
		
	}
	// Event Listener on Button[#btnBackward].onAction
	@FXML
	public void btnBackwardOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnStepBackward].onAction
	@FXML
	public void btnStepBackwardOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnStepForward].onAction
	@FXML
	public void btnStepForwardOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnForward].onAction
	@FXML
	public void btnForwardOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnFastForward].onAction
	@FXML
	public void btnFastForwardOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	@FXML
    void initialize() {
        
        
        try {
	        //UnitCategoryID.setCellValueFactory(new PropertyValueFactory<>("UnitCategoryID"));
	        UnitCategoryName.setCellValueFactory(new PropertyValueFactory<>("UnitCategoryName"));
	        UnitCategoryRemark.setCellValueFactory(new PropertyValueFactory<>("UnitCategoryRemark"));
	        ArrayList<UnitCategoryData> data = UnitCategoryDBControl.getUnitCategoryData();
	        for (int i = 0; i < data.size(); i++) {
				table.getItems().add(data.get(i));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			new Alert(AlertType.INFORMATION,"No data added in Unit Category",ButtonType.OK).show();
		}
	}
}
