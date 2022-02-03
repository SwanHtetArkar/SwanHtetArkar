package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class InventoryViewController {
	public static int StartPointer= 1;
	public static int EndPointer = 11;
	public final int rowInOnePage = 10;
	private static InventoryData selected=null; 
	
    public static InventoryData getSelected() {
		return selected;
	}

	public static void setSelected(InventoryData selected) {
		InventoryViewController.selected = selected;
	}
	@FXML
	private TextField txfSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnCancle;
	@FXML
	private ComboBox cmbBy;
	@FXML
	private ComboBox cmbValue;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnAddNew;
	@FXML
	private FontAwesomeIcon Update;
	@FXML
	private FontAwesomeIcon Delete;
	@FXML
	private FontAwesomeIcon AddNew;
	@FXML
	private FontAwesomeIcon FastBackward;
	@FXML
	private FontAwesomeIcon Backward;
	@FXML
	private FontAwesomeIcon StepBackward;
	@FXML
	private FontAwesomeIcon StepForward;
	@FXML
	private FontAwesomeIcon Forward;
	@FXML
	private FontAwesomeIcon FastForward;
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
	private Button btnAsc;
	@FXML
	private Button btnDesc;
	@FXML
	private ImageView search;
	@FXML
	private ImageView cancel;
	@FXML
	private TableView<InventoryData> table;
	@FXML
	private TableColumn<String,InventoryData> Code;
	@FXML
	private TableColumn<String,InventoryData> Name;
	@FXML
	private TableColumn<String,InventoryData> Unit1;
	@FXML
	private TableColumn<String,InventoryData> UnitType;
	@FXML
	private TableColumn<Integer,InventoryData> Price;
	@FXML
	private TableColumn<Integer,InventoryData> Quantity;
	@FXML
	private TableColumn<String,InventoryData> Brand;
	@FXML
	private TableColumn<String,InventoryData> Location;
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
    void btnReportOnAction(ActionEvent event) {
    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "SaleOrderReport.fxml");	
    }
	// Event Listener on TextField[#txfSearch].onAction
	@FXML
	public void txfSearchOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnSearch].onAction
	@FXML
	public void btnSearchOnAction(ActionEvent event) {
		table.getItems().clear();
	if (txfSearch.getText().isEmpty()) {
    	ArrayList<InventoryData> data = InventoryViewDBControl.getInventoryInitialize(); //start up condition 
        for (int i = 0; i < data.size(); i++) {
			table.getItems().add(data.get(i));
		}
	}else {
		ArrayList<InventoryData> data = InventoryViewDBControl.InventorysearchFunction(txfSearch.getText());
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
	// Event Listener on ComboBox[#cmbBy].onAction
	@FXML
	public void cmbByOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ComboBox[#cmbValue].onAction
	@FXML
	public void cmbValueOnAction(ActionEvent event) {
		table.getItems().clear();
		if (cmbValue.getSelectionModel().getSelectedIndex()!=0) {
			InventoryViewDBControl.startup1(ItemDBControl.getUnitID(cmbValue.getSelectionModel().getSelectedItem()+"")); 
	        InventoryViewDBControl.startup2(ItemDBControl.getUnitID(cmbValue.getSelectionModel().getSelectedItem()+"")); 
	        ArrayList<InventoryData> data = InventoryViewDBControl.getInventoryInitialize();
	        for (int i = 0; i < data.size(); i++) {
				table.getItems().add(data.get(i));
			}
	        StartPointer = data.get(0).getItem_ID();
		    EndPointer = data.get(data.size()-1).getItem_ID();
		}else {
			InventoryViewDBControl.startup1(); 
	        InventoryViewDBControl.startup2(); 
	        ArrayList<InventoryData> data = InventoryViewDBControl.getInventoryInitialize();
	        for (int i = 0; i < data.size(); i++) {
				table.getItems().add(data.get(i));
			}
	        StartPointer = data.get(0).getItem_ID();
		    EndPointer = data.get(data.size()-1).getItem_ID();
		}
        
	}
	// Event Listener on Button[#btnUpdate].onAction
	@FXML
	public void btnUpdateOnAction(ActionEvent event) {
		if (table.getSelectionModel().getSelectedItem()== null) {
		new Alert(AlertType.WARNING,"Please select one row!!!",ButtonType.OK).showAndWait();
	} else {
		InventoryData selectedItem =table.getSelectionModel().getSelectedItem();
		selected = new InventoryData(selectedItem.getItem_ID(), selectedItem.getItem_Code(), selectedItem.getItem_Name(),
				selectedItem.getDescription(), selectedItem.getPrice_Selling(),selectedItem.getQuantity(),
				selectedItem.getBrand_Name(),selectedItem.getUnit(),selectedItem.getUnit_Type(),selectedItem.getLocation());
		System.out.println("SelectedWork");
		new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "InventoryEdit.fxml");
	}
	}
	// Event Listener on Button[#btnDelete].onAction
	@FXML
	public void btnDeleteOnAction(ActionEvent event) {
		if (table.getSelectionModel().getSelectedItem()== null) {
			new Alert(AlertType.WARNING,"Please select one row!!!",ButtonType.OK).showAndWait();
		}else if(table.getSelectionModel().getSelectedItem()!=null){
				selected= table.getSelectionModel().getSelectedItem();
				InventoryViewDBControl.DeleteInventory(selected.getItem_ID());
				table.getItems().clear();
				new Alert(AlertType.INFORMATION,"Successfully deleted!",ButtonType.OK).showAndWait();
				 InventoryViewDBControl.startup1(); 
			     InventoryViewDBControl.startup2();
				ArrayList<InventoryData> data = InventoryViewDBControl.getInventoryInitialize();
	  	        for (int i = 0; i < data.size(); i++) {
	  				table.getItems().add(data.get(i));
	  			}
		}else{
			new Alert(AlertType.WARNING,"Something Wrong",ButtonType.OK).showAndWait();
		}
	}
	// Event Listener on Button[#btnAddNew].onAction
	@FXML
	public void btnAddNewOnAction(ActionEvent event) {
    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "InventoryNew.fxml");	
	}
	// Event Listener on Button[#btnFastBackward].onAction
	@FXML
	public void btnFastBackwardOnAction(ActionEvent event) {
		table.getItems().clear();
		ArrayList<InventoryData> data = InventoryViewDBControl.getInventoryInitialize();
        for (int i = 0; i < data.size(); i++) {
        		table.getItems().add(data.get(i));
		}
        btnFastForward.setDisable(false);
        btnFastBackward.setDisable(true);
        StartPointer = data.get(0).getItem_ID();
        EndPointer = data.get(data.size()-1).getItem_ID();
    
	}
	// Event Listener on Button[#btnBackward].onAction
	@FXML
	public void btnBackwardOnAction(ActionEvent event) {
		ArrayList<InventoryData> data = null;
		table.getItems().clear();
		try {
			for(int i=0;i<3;i++){
				data = backward();
			}
			
		} finally {
			StartPointer= data.get(data.size()-1).getItem_ID(); 
	        EndPointer = data.get(0).getItem_ID(); 
			for (int i = data.size()-1;i >= 0; i--) {
        		table.getItems().add(data.get(i));
			}	
		}
        btnFastForward.setDisable(false);
        btnFastBackward.setDisable(false);
	}
	// Event Listener on Button[#btnStepBackward].onAction
	@FXML
	public void btnStepBackwardOnAction(ActionEvent event) {
		ArrayList<InventoryData> data = backward();
		table.getItems().clear();
        for (int i = data.size()-1;i >= 0; i--) {
        		table.getItems().add(data.get(i));
		}
        btnFastForward.setDisable(false);
        btnFastBackward.setDisable(false);
	}
	// Event Listener on Button[#btnStepForward].onAction
	@FXML
	public void btnStepForwardOnAction(ActionEvent event) {
		ArrayList<InventoryData> data = forward();
		table.getItems().clear();
        for (int i =0;i <data.size(); i++) {
        		table.getItems().add(data.get(i));
		}
        btnFastForward.setDisable(false);
        btnFastBackward.setDisable(false);
	}
	// Event Listener on Button[#btnForward].onAction
	@FXML
	public void btnForwardOnAction(ActionEvent event) {
		ArrayList<InventoryData> data = null;
		table.getItems().clear();
		try {
			for(int i=0;i<3;i++){
				data = forward();
			}
		} finally {
			for (int i =0;i < data.size(); i++) {
        		table.getItems().add(data.get(i));
			}
		}
        btnFastForward.setDisable(false);
        btnFastBackward.setDisable(false);
	}
	// Event Listener on Button[#btnFastForward].onAction
	@FXML
	public void btnFastForwardOnAction(ActionEvent event) {
		table.getItems().clear();
    	ArrayList<InventoryData> temp =new ArrayList<>();
    	try {
			temp = forward();
			while (temp.size()>temp.size()-1) {
				temp = forward();
			}
		} catch (Exception e) {
		
		}
    	finally {
    		for (int i = 0; i < temp.size(); i++) {
    			table.getItems().add(temp.get(i));
    		}
    		btnFastForward.setDisable(true); 
            btnFastBackward.setDisable(false); 
		}
	}
	public static ArrayList<InventoryData> forward() {
		StartPointer = EndPointer;//move start point to next page
    	ArrayList<InventoryData> data = InventoryViewDBControl.getInventoryForward(StartPointer);
        EndPointer = data.get(data.size()-1).getItem_ID();//save the id which ended
        StartPointer = data.get(0).getItem_ID();
        return data;
	}
	public static ArrayList<InventoryData> backward(){
		EndPointer =StartPointer;
    	ArrayList<InventoryData> data = InventoryViewDBControl.getInventoryBackward(EndPointer);
        StartPointer= data.get(data.size()-1).getItem_ID();
        EndPointer = data.get(0).getItem_ID();
		return data;
	}
	// Event Listener on Button[#btnAsc].onAction
	@FXML
	public void btnAscOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnDesc].onAction
	@FXML
	public void btnDescOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	@FXML
    void initialize() {
        try {
        	Code.setCellValueFactory(new PropertyValueFactory<>("Item_Code"));
	        Name.setCellValueFactory(new PropertyValueFactory<>("Item_Name"));
	        Unit1.setCellValueFactory(new PropertyValueFactory<>("Unit"));
	        UnitType.setCellValueFactory(new PropertyValueFactory<>("Unit_Type"));
	        Price.setCellValueFactory(new PropertyValueFactory<>("Price_Selling"));
	        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
	        Brand.setCellValueFactory(new PropertyValueFactory<>("Brand_Name"));
	        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
	        InventoryViewDBControl.startup1(); 
	        InventoryViewDBControl.startup2(); 
	        ArrayList<InventoryData> data = InventoryViewDBControl.getInventoryInitialize();
	        for (int i = 0; i < data.size(); i++) {
				table.getItems().add(data.get(i));
			}
	        StartPointer = data.get(0).getItem_ID();
		    EndPointer = data.get(data.size()-1).getItem_ID();

		    cmbBy.getItems().add("Unit Type");
		    cmbValue.getItems().add("None");
		    cmbValue.getItems().addAll(ItemDBControl.getUnitName());
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
			new Alert(AlertType.INFORMATION,"No data added in Vendor",ButtonType.OK).show();
		}
	}
}
