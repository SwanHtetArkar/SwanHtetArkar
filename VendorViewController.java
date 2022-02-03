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

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import com.mysql.jdbc.Blob;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class VendorViewController {
	public static int StartPointer= 1;
	public static int EndPointer = 11;
	public final int rowInOnePage = 10;
	private static VendorData selected=null; 
	
    public static VendorData getSelected() {
		return selected;
	}

	public static void setSelected(VendorData selected) {
		VendorViewController.selected = selected;
	}
	@FXML
	private TextField txfSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnCancle;
	@FXML
	private TableView<VendorData> table;
	@FXML
	private TableColumn<String, VendorData> VendorName;
	@FXML
	private TableColumn<String,VendorData> VendorAddress;
	@FXML
	private TableColumn<String, VendorData> VendorPhone;
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
		txfSearch.requestFocus();
	}
	// Event Listener on Button[#btnSearch].onAction
	@FXML
	public void btnSearchOnAction(ActionEvent event) {
		ArrayList<VendorData> data = VendorDBControl.VendorsearchFunction(txfSearch.getText());
		if(data.isEmpty()) {
			new Alert(AlertType.INFORMATION, "No Such Data Exists", ButtonType.OK).showAndWait();
		}else{
			table.getItems().clear();
			if(txfSearch.getText().isEmpty()) {
				ArrayList<VendorData> search=VendorDBControl.getVendorInitialize();
				 StartPointer = search.get(0).getVendorID();
				 EndPointer = search.get(search.size()-1).getVendorID();
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
		ArrayList<VendorData> search=VendorDBControl.getVendorInitialize();
		 StartPointer = search.get(0).getVendorID();
		 EndPointer = search.get(search.size()-1).getVendorID();
		 for (int i = 0; i < search.size(); i++) {
				table.getItems().add(search.get(i));	
		}
		
	}
	// Event Listener on Button[#btnUpdate].onAction
	@FXML
	public void btnUpdateOnAction(ActionEvent event) {
		if (table.getSelectionModel().getSelectedItem()== null) {
			new Alert(AlertType.WARNING,"Please select one row!!!",ButtonType.OK).showAndWait();
		} else {
			selected= table.getSelectionModel().getSelectedItem();
			new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "VendorEdit.fxml");
		}
	}
	// Event Listener on Button[#btnDelete].onAction
	@FXML
	public void btnDeleteOnAction(ActionEvent event) {
		if (table.getSelectionModel().getSelectedItem()== null) {
			new Alert(AlertType.WARNING,"Please select one row!!!",ButtonType.OK).showAndWait();
		}else if(table.getSelectionModel().getSelectedItem()!=null){
				selected= table.getSelectionModel().getSelectedItem();
				VendorDBControl.DeleteVendor(selected.getVendorID());
				table.getItems().clear();
				new Alert(AlertType.INFORMATION,"Successfully deleted!",ButtonType.OK).showAndWait();
				 VendorDBControl.startup1(); 
			     VendorDBControl.startup2();
				ArrayList<VendorData> data = VendorDBControl.getVendorData();
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
		new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "VendorInput.fxml");	
	}
	// Event Listener on Button[#btnFastBackward].onAction
		@FXML
		public void btnFastBackwardOnAction(ActionEvent event) {
			table.getItems().clear();
			ArrayList<VendorData> data = VendorDBControl.getVendorInitialize();
	        for (int i = 0; i < data.size(); i++) {
	        		table.getItems().add(data.get(i));
			}
	        btnFastForward.setDisable(false);
	        btnFastBackward.setDisable(true);
	        StartPointer = data.get(0).getVendorID();
	        EndPointer = data.get(data.size()-1).getVendorID();
	    
		}
		// Event Listener on Button[#btnBackward].onAction
		@FXML
		public void btnBackwardOnAction(ActionEvent event) {
			ArrayList<VendorData> data = null;
			table.getItems().clear();
			try {
				for(int i=0;i<3;i++){
					data = backward();
				}
				
			} finally {
				StartPointer= data.get(data.size()-1).getVendorID(); 
		        EndPointer = data.get(0).getVendorID(); 
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
			ArrayList<VendorData> data = backward();
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
			ArrayList<VendorData> data = forward();
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
			ArrayList<VendorData> data = null;
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
	    	ArrayList<VendorData> temp =new ArrayList<>();
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
		public static ArrayList<VendorData> forward() {
			StartPointer = EndPointer;//move start point to next page
	    	ArrayList<VendorData> data = VendorDBControl.getVendorForward(StartPointer);
	        EndPointer = data.get(data.size()-1).getVendorID();//save the id which ended
	        StartPointer = data.get(0).getVendorID();
	        return data;
		}
		public static ArrayList<VendorData> backward(){
			EndPointer =StartPointer;
	    	ArrayList<VendorData> data = VendorDBControl.getVendorBackward(EndPointer);
	        StartPointer= data.get(data.size()-1).getVendorID();
	        EndPointer = data.get(0).getVendorID();
			return data;
		}
		@FXML
	    void initialize() {
	        
	        
	        try {
	        	VendorName.setCellValueFactory(new PropertyValueFactory<>("VendorName"));
		        VendorAddress.setCellValueFactory(new PropertyValueFactory<>("VendorAddress"));
		        VendorPhone.setCellValueFactory(new PropertyValueFactory<>("VendorPhone"));
		        
		        VendorDBControl.startup1(); 
		        VendorDBControl.startup2(); 
		        
		        ArrayList<VendorData> data = VendorDBControl.getVendorInitialize();
		        for (int i = 0; i < data.size(); i++) {
					table.getItems().add(data.get(i));
				}
		        StartPointer = data.get(0).getVendorID();
			    EndPointer = data.get(data.size()-1).getVendorID();
			} catch (Exception e) {
				e.printStackTrace();
				new Alert(AlertType.INFORMATION,"No data added in Vendor",ButtonType.OK).show();
			}
		}
}
