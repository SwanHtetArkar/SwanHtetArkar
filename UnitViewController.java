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

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class UnitViewController {
	public static int StartPointer= 1;
	public static int EndPointer = 11;
	public final int rowInOnePage = 10;
	private static UnitData selected=null; 
	
    public static UnitData getSelected() {
		return selected;
	}

	public static void setSelected(UnitData selected) {
		UnitViewController.selected = selected;
	}
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
	private Button btnUnitCategory;
	@FXML
	private Button btnReport;
	@FXML
	private TextField txfSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnCancle;
	@FXML
	private TableView<UnitData> table;
	@FXML
	private TableColumn<String,UnitData> UnitCode;
	@FXML
	private TableColumn<String,UnitData> UnitName;
	@FXML
	private TableColumn<String,UnitData> UnitRemark;
	@FXML
	private TableColumn<String,UnitData> UnitCategoryName;
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
		ArrayList<UnitData> data = UnitDBControl.UnitsearchFunction(txfSearch.getText());
		if(data.isEmpty()) {
			new Alert(AlertType.INFORMATION, "No Such Data Exists", ButtonType.OK).showAndWait();
		}else{
			table.getItems().clear();
			if(txfSearch.getText().isEmpty()) {
				ArrayList<UnitData> search=UnitDBControl.getUnitInitialize();
				 StartPointer = search.get(0).getUnitID();
				 EndPointer = search.get(search.size()-1).getUnitID();
				 for (int i = 0; i < search.size(); i++) {
						table.getItems().add(search.get(i));	
				}
			}else {
				for(int i=0;i<data.size();i++) {
					table.getItems().add(data.get(i));
				}
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
			new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitEdit.fxml");
		}
	}
	// Event Listener on Button[#btnDelete].onAction
	@FXML
	public void btnDeleteOnAction(ActionEvent event) {
		if (table.getSelectionModel().getSelectedItem()== null) {
			new Alert(AlertType.WARNING,"Please select one row!!!",ButtonType.OK).showAndWait();
		}else if(table.getSelectionModel().getSelectedItem()!=null){
				selected= table.getSelectionModel().getSelectedItem();
				UnitDBControl.DeleteUnit(selected.getUnitID());
				table.getItems().clear();
				new Alert(AlertType.INFORMATION,"Successfully deleted!",ButtonType.OK).showAndWait();
				 UnitDBControl.startup1(); 
			        UnitDBControl.startup2();
				ArrayList<UnitData> data = UnitDBControl.getUnitData();
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
		new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "UnitInput.fxml");	
		}
	// Event Listener on Button[#btnFastBackward].onAction
	@FXML
	public void btnFastBackwardOnAction(ActionEvent event) {
		table.getItems().clear();
		ArrayList<UnitData> data = UnitDBControl.getUnitInitialize();
        for (int i = 0; i < data.size(); i++) {
        		table.getItems().add(data.get(i));
		}
        btnFastForward.setDisable(false);
        btnFastBackward.setDisable(true);
        StartPointer = data.get(0).getUnitID();
        EndPointer = data.get(data.size()-1).getUnitID();
        System.out.println("Hello");
	}
	// Event Listener on Button[#btnBackward].onAction
	@FXML
	public void btnBackwardOnAction(ActionEvent event) {
		ArrayList<UnitData> data = null;
		table.getItems().clear();
		try {
			for(int i=0;i<3;i++){
				data = backward();
			}
			
		} finally {
			StartPointer= data.get(data.size()-1).getUnitID(); 
	        EndPointer = data.get(0).getUnitID(); 
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
		ArrayList<UnitData> data = backward();
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
		ArrayList<UnitData> data = forward();
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
		ArrayList<UnitData> data = null;
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
    	ArrayList<UnitData> temp =new ArrayList<>();
    	try {
			temp = forward();
			while (temp.size()>rowInOnePage-1) {
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
	@FXML
    void initialize() {
        
        
        try {
        	UnitCode.setCellValueFactory(new PropertyValueFactory<>("UnitCode"));
	        UnitName.setCellValueFactory(new PropertyValueFactory<>("UnitName"));
	        UnitRemark.setCellValueFactory(new PropertyValueFactory<>("UnitRemark"));
	        UnitCategoryName.setCellValueFactory(new PropertyValueFactory<>("UnitCategoryName"));
	        
	        
	        UnitDBControl.startup1(); 
	        UnitDBControl.startup2(); 
	        
	        ArrayList<UnitData> data = UnitDBControl.getUnitInitialize();
	        for (int i = 0; i < data.size(); i++) {
				table.getItems().add(data.get(i));
			}
	        StartPointer = data.get(0).getUnitID();
		    EndPointer = data.get(data.size()-1).getUnitID();
		} catch (Exception e) {
			e.printStackTrace();
			new Alert(AlertType.INFORMATION,"No data added in Unit",ButtonType.OK).show();
		}
	}
	public static ArrayList<UnitData> forward() {
		StartPointer = EndPointer;//move start point to next page
    	ArrayList<UnitData> data = UnitDBControl.getUnitForward(StartPointer);
        EndPointer = data.get(data.size()-1).getUnitID();//save the id which ended
        StartPointer = data.get(0).getUnitID();
        return data;
	}
	public static ArrayList<UnitData> backward(){
		EndPointer =StartPointer;
    	ArrayList<UnitData> data = UnitDBControl.getUnitBackward(EndPointer);
        StartPointer= data.get(data.size()-1).getUnitID();
        EndPointer = data.get(0).getUnitID();
		return data;
	}
}
