package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.*;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.Node;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import java.time.*;
import java.util.ArrayList; 

public class SaleOrderReportController {
	@FXML
	private Button btnCustomerDebtList;
	@FXML
	private Button btnSaleOrderReport;
	@FXML
	private Button btnExportExcel;
	@FXML
	private TableView<SalesOrderReportData> tabel;
	@FXML
	private TableColumn<SalesOrderReportData, String> ItemName;
	@FXML
	private TableColumn<SalesOrderReportData, Integer> Quantity;
	@FXML
	private TableColumn<SalesOrderReportData, Integer> Price;
	@FXML
	private TextField txfSearch;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnSearch;
	@FXML
	private ComboBox<String> cmbYear;
	@FXML
	private ComboBox<String> cmbMonth;
	@FXML
	private ComboBox<String> cmbDay;
	@FXML
	private StackedBarChart<String,Number> Chart;
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
	
	
	String Months[] = new String[] {"January","February","March",
			"April","May","June","July","August",
			"September","October","November","December"};
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
	@FXML
	public void btnExportExcelOnAction(ActionEvent event) {
	
	}
	// Event Listener on TextField[#txfSearch].onAction
	@FXML
	public void txfSearchOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void btnCancleOnAction(ActionEvent event) {
		txfSearch.clear();
		if (!cmbDay.getSelectionModel().isSelected(0)) {
			try {
				if (!(cmbYear.getSelectionModel().isSelected(0) && cmbMonth.getSelectionModel().isSelected(0))) {
					ArrayList<SalesOrderReportData> data = SalesOrderReportDBControl.salesItemByDate(Integer.parseInt(cmbDay.getSelectionModel().getSelectedItem()),
							cmbMonth.getSelectionModel().getSelectedIndex(),Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()));
					tabel.getItems().clear();
					for (int i = 0; i < data.size(); i++) {
						tabel.getItems().add(data.get(i));
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}else
		addToTable();
	}
	// Event Listener on Button[#btnSearch].onAction
	@FXML
	public void btnSearchOnAction(ActionEvent event) {
		if (!cmbDay.getSelectionModel().isSelected(0)) {
			try {
				if (!(cmbYear.getSelectionModel().isSelected(0) && cmbMonth.getSelectionModel().isSelected(0))) {
					ArrayList<SalesOrderReportData> data = SalesOrderReportDBControl.salesItemByDateSearchByItemName(txfSearch.getText(),Integer.parseInt(cmbDay.getSelectionModel().getSelectedItem()),
							cmbMonth.getSelectionModel().getSelectedIndex(),Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()));
					tabel.getItems().clear();
					for (int i = 0; i < data.size(); i++) {
						tabel.getItems().add(data.get(i));
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}else
		addToTable(txfSearch.getText());
	}
	// Event Listener on ComboBox[#cmbYear].onAction
	@FXML
	public void cmbYearOnAction(ActionEvent event) {
		
	}
	// Event Listener on ComboBox[#cmbMonth].onAction
	@FXML
	public void cmbMonthOnAction(ActionEvent event) {
	}
	// Event Listener on ComboBox[#cmbDay].onAction
	@FXML
	public void cmbDayOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	@FXML
	public void initialize() {

		ItemName.setCellValueFactory(new PropertyValueFactory<>("Item_Name"));
		Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
		cmbYear.getItems().add("Years");
		cmbMonth.getItems().add("Months");
		cmbDay.getItems().add("Days");
		
		for (int i = 2000; i <= LocalDate.now().getYear(); i++) {
			cmbYear.getItems().add(i+"");
		}
		
		for (int i = 0; i < 12; i++) {
			cmbMonth.getItems().add(Months[i]);
		}

		cmbYear.getSelectionModel().select(LocalDate.now().getYear()+"");
		cmbMonth.getSelectionModel().select(LocalDate.now().getMonthValue());
		
		switch(getDate()) {
		  case 1:
		    for(int i = 1;i<31;i++) {
		    	cmbDay.getItems().add(i+"");
		    }
		    break;
		  case 2:
			  for(int i = 1;i<32;i++) {
			    	cmbDay.getItems().add(i+"");
			    }
		    break;
		  case 3:
			  for(int i = 1;i<30;i++) {
			    	cmbDay.getItems().add(i+"");
			    }
		    break;
		  case 4:
			  for(int i = 1;i<29;i++) {
			    	cmbDay.getItems().add(i+"");
			    }
		    break;
		  default:
		    // code block
		}
		cmbDay.getSelectionModel().select(LocalDate.now().getDayOfMonth());
		
		ArrayList<SalesOrderReportData> StartUpData = SalesOrderReportDBControl.salesItemByDate(Integer.parseInt(cmbDay.getSelectionModel().getSelectedItem()),
				cmbMonth.getSelectionModel().getSelectedIndex(),Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()));
		tabel.getItems().clear();
		for (int i = 0; i < StartUpData.size(); i++) {
			tabel.getItems().add(StartUpData.get(i));
		}
		
		cmbMonth.setOnAction(e->{
			cmbDay.getItems().clear();
			cmbDay.getItems().add("Days");
			cmbDay.getSelectionModel().selectFirst();
			switch(getDate()) {
			  case 1:
			    for(int i = 1;i<31;i++) {
			    	cmbDay.getItems().add(i+"");
			    }
			    break;
			  case 2:
				  for(int i = 1;i<32;i++) {
				    	cmbDay.getItems().add(i+"");
				    }
			    break;
			  case 3:
				  for(int i = 1;i<30;i++) {
				    	cmbDay.getItems().add(i+"");
				    }
			    break;
			  case 4:
				  for(int i = 1;i<29;i++) {
				    	cmbDay.getItems().add(i+"");
				    }
			    break;
			  default:
			    // code block
			}
			if (txfSearch.getText().isEmpty()) {
				addToTable();
			}else {
				addToTable(txfSearch.getText());
			}
		});
		cmbYear.setOnAction(e->{
			if (!cmbMonth.getSelectionModel().isEmpty()) {
				cmbDay.getItems().clear();
				cmbDay.getItems().add("Days");
				cmbDay.getSelectionModel().selectFirst();
				switch(getDate()) {
				  case 1:
				    for(int i = 1;i<31;i++) {
				    	cmbDay.getItems().add(i+"");
				    }
				    break;
				  case 2:
					  for(int i = 1;i<32;i++) {
					    	cmbDay.getItems().add(i+"");
					    }
				    break;
				  case 3:
					  for(int i = 1;i<30;i++) {
					    	cmbDay.getItems().add(i+"");
					    }
				    break;
				  case 4:
					  for(int i = 1;i<29;i++) {
					    	cmbDay.getItems().add(i+"");
					    }
				    break;
				  default:
				    // code block
				}
				
			}

			if (txfSearch.getText().isEmpty()) {
				addToTable();
			}else {
				addToTable(txfSearch.getText());
			}
		});
		
		cmbDay.setOnAction(e->{
			if (cmbDay.getSelectionModel().isSelected(0)) {
				if (txfSearch.getText().isEmpty()) {
					addToTable();
				}else {
					addToTable(txfSearch.getText());
				}
			}else {
				try {
						
					if (txfSearch.getText().isEmpty()) {
						if (!(cmbYear.getSelectionModel().isSelected(0) && cmbMonth.getSelectionModel().isSelected(0))) {
							ArrayList<SalesOrderReportData> data = SalesOrderReportDBControl.salesItemByDate(Integer.parseInt(cmbDay.getSelectionModel().getSelectedItem()),
									cmbMonth.getSelectionModel().getSelectedIndex(),Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()));
							tabel.getItems().clear();
							for (int i = 0; i < data.size(); i++) {
								tabel.getItems().add(data.get(i));
							}
						}
					}else {
						if (!(cmbYear.getSelectionModel().isSelected(0) && cmbMonth.getSelectionModel().isSelected(0))) {
							ArrayList<SalesOrderReportData> data = SalesOrderReportDBControl.salesItemByDateSearchByItemName(txfSearch.getText(),Integer.parseInt(cmbDay.getSelectionModel().getSelectedItem()),
									cmbMonth.getSelectionModel().getSelectedIndex(),Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()));
							tabel.getItems().clear();
							for (int i = 0; i < data.size(); i++) {
								tabel.getItems().add(data.get(i));
							}
						}
					}
						
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();  
	      series1.setName("Capital"); 
	      for (int i = 0; i < 12; i++) {
		      series1.getData().add(new XYChart.Data<>(Months[i], SalesOrderReportDBControl.MonthlySales(i+1, LocalDate.now().getYear()))); 
		}
	    Chart.getData().add(series1);
	}
	
	public int getDate() {
		if (cmbMonth.getSelectionModel().getSelectedIndex()==3||
				cmbMonth.getSelectionModel().getSelectedIndex()==5||
				cmbMonth.getSelectionModel().getSelectedIndex()==8||
				cmbMonth.getSelectionModel().getSelectedIndex()==10) {
				return 1;
			} else if (cmbMonth.getSelectionModel().getSelectedIndex()==0||
				cmbMonth.getSelectionModel().getSelectedIndex()==2||
				cmbMonth.getSelectionModel().getSelectedIndex()==4||
				cmbMonth.getSelectionModel().getSelectedIndex()==6||
				cmbMonth.getSelectionModel().getSelectedIndex()==7||
				cmbMonth.getSelectionModel().getSelectedIndex()==9||
				cmbMonth.getSelectionModel().getSelectedIndex()==11) {
				return 2;
				
			} else{
				LocalDate date = LocalDate.of(Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()), 1, 1);
				if (date.isLeapYear()){
			    	 return 3;
				}else {
					return 4;
				}
			}
	}
	public void addToTable() {

		if (cmbMonth.getSelectionModel().isSelected(0)) {
			ArrayList<SalesOrderReportData> data = SalesOrderReportDBControl.salesItemByYear(Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()));
			tabel.getItems().clear();
//			cmbDay.getSelectionModel().select(0);
			for (int i = 0; i < data.size(); i++) {
				tabel.getItems().add(data.get(i));
			}
		}else if (!cmbMonth.getSelectionModel().isSelected(0)) {
			ArrayList<SalesOrderReportData> data = SalesOrderReportDBControl.salesItemByMonth(cmbMonth.getSelectionModel().getSelectedIndex(),Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()));
			tabel.getItems().clear();
			for (int i = 0; i < data.size(); i++) {
				tabel.getItems().add(data.get(i));
			}
			cmbDay.getSelectionModel().select(0);
		} else {
			tabel.getItems().clear();
			System.out.println(cmbMonth.getSelectionModel().getSelectedItem());
		}
		
	}
	public void addToTable(String name) {

		if (cmbMonth.getSelectionModel().isSelected(0)) {
			ArrayList<SalesOrderReportData> data = SalesOrderReportDBControl.salesItemByYearSearchByItemName(name,Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()));
			tabel.getItems().clear();
//			cmbDay.getSelectionModel().select(0);
			for (int i = 0; i < data.size(); i++) {
				tabel.getItems().add(data.get(i));
			}
		}else if (!cmbMonth.getSelectionModel().isSelected(0)) {
			ArrayList<SalesOrderReportData> data = SalesOrderReportDBControl.salesItemByMonthSearchByItemName(name,cmbMonth.getSelectionModel().getSelectedIndex(),Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem()));
			tabel.getItems().clear();
			for (int i = 0; i < data.size(); i++) {
				tabel.getItems().add(data.get(i));
			}
			cmbDay.getSelectionModel().select(0);
		} else {
			tabel.getItems().clear();
			System.out.println(cmbMonth.getSelectionModel().getSelectedItem());
		}
		
	}
}
