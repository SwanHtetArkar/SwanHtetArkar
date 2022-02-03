package games;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class numberGuessingGame extends Application{
//
	private static int answer = 0;
	private static String ans;
	private int pointer = 1;
	private int turn = 5;
	private Label lblTurns = new Label("No. of turns left : "+turn);
	private Label title = new Label("Guess The Number");
	private BorderPane root = new BorderPane();
	private TextField input1 = new TextField();
	private TextField input2 = new TextField();
	private TextField input3 = new TextField();
	private TextField input4 = new TextField();
	private Button btnSubmit = new Button("Submit");
	private Button btnclear = new Button("Clear");
	private HBox inputline = new HBox();
	private HBox bottom = new HBox();
	private VBox center = new VBox();
	
	private GridPane dialboard = new GridPane();
	private Button btn1 = new Button("1");
	private Button btn2 = new Button("2");
	private Button btn3 = new Button("3");
	private Button btn4 = new Button("4");
	private Button btn5 = new Button("5");
	private Button btn6 = new Button("6");
	private Button btn7 = new Button("7");
	private Button btn8 = new Button("8");
	private Button btn9 = new Button("9");
	private Button btn0 = new Button("0");
	
	public static void main(String[] args) {
		answer=(int)(Math.random()*9999);
		ans =String.format("%04d",answer);
		launch(args);
	}
	@Override
	public void start(Stage st) throws Exception {
		dialboard.add(btn1, 0, 0);
		dialboard.add(btn2, 1, 0);
		dialboard.add(btn3, 2, 0);
		dialboard.add(btn4, 0, 1);
		dialboard.add(btn5, 1, 1);
		dialboard.add(btn6, 2, 1);
		dialboard.add(btn7, 0, 2);
		dialboard.add(btn8, 1, 2);
		dialboard.add(btn9, 2, 2);
		dialboard.add(btn0, 1, 3);
		
		center.getChildren().addAll(inputline,dialboard);
		inputline.getChildren().addAll(input1,input2,input3,input4);
		bottom.getChildren().addAll(lblTurns,btnclear,btnSubmit);
		root.setTop(title);
		root.setCenter(center);
		root.setBottom(bottom);
		root.setPrefSize(400, 250);
//		
		BorderPane.setAlignment(title, Pos.CENTER);
//		BorderPane.setAlignment(btnSubmit, Pos.BOTTOM_RIGHT);
		HBox.setMargin(btnclear, new Insets(0,0,5,150));
		HBox.setMargin(btnSubmit, new Insets(0,0,5,5));
		title.setFont(new Font("Algerian", 20));		
		lblTurns.setFont(new Font("Baskerville Old Face", 14));
		btnclear.setFont(new Font("Baskerville Old Face", 14));
		btnSubmit.setFont(new Font("Baskerville Old Face", 14));
		inputline.setMargin(input2, new Insets(0,7,0,7));
		inputline.setMargin(input4, new Insets(0,0,0,7));
		BorderPane.setMargin(title, new Insets(0,0,10,0));
		root.setPadding(new Insets(10,10,10,10));
		
		center.setMargin(dialboard, new Insets(10,0,0,154));
		dialboard.setHgap(5);
		dialboard.setVgap(5);
		
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.setTitle("input");
		st.sizeToScene();
		st.show();	

		btn1.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("1");
				pointer++;
				break;
			case 2: 
				input2.setText("1");
				pointer++;
				break;
			case 3: 
				input3.setText("1");
				pointer++;
				break;
			case 4: 
				input4.setText("1");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btn2.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("2");
				pointer++;
				break;
			case 2: 
				input2.setText("2");
				pointer++;
				break;
			case 3: 
				input3.setText("2");
				pointer++;
				break;
			case 4: 
				input4.setText("2");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btn3.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("3");
				pointer++;
				break;
			case 2: 
				input2.setText("3");
				pointer++;
				break;
			case 3: 
				input3.setText("3");
				pointer++;
				break;
			case 4: 
				input4.setText("3");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btn4.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("4");
				pointer++;
				break;
			case 2: 
				input2.setText("4");
				pointer++;
				break;
			case 3: 
				input3.setText("4");
				pointer++;
				break;
			case 4: 
				input4.setText("4");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btn5.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("5");
				pointer++;
				break;
			case 2: 
				input2.setText("5");
				pointer++;
				break;
			case 3: 
				input3.setText("5");
				pointer++;
				break;
			case 4: 
				input4.setText("5");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btn6.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("6");
				pointer++;
				break;
			case 2: 
				input2.setText("6");
				pointer++;
				break;
			case 3: 
				input3.setText("6");
				pointer++;
				break;
			case 4: 
				input4.setText("6");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btn7.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("7");
				pointer++;
				break;
			case 2: 
				input2.setText("7");
				pointer++;
				break;
			case 3: 
				input3.setText("7");
				pointer++;
				break;
			case 4: 
				input4.setText("7");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btn8.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("8");
				pointer++;
				break;
			case 2: 
				input2.setText("8");
				pointer++;
				break;
			case 3: 
				input3.setText("8");
				pointer++;
				break;
			case 4: 
				input4.setText("8");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btn9.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("9");
				pointer++;
				break;
			case 2: 
				input2.setText("9");
				pointer++;
				break;
			case 3: 
				input3.setText("9");
				pointer++;
				break;
			case 4: 
				input4.setText("9");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btn0.setOnAction(e->{
			switch (pointer) {
			case 1: 
				input1.setText("0");
				pointer++;
				break;
			case 2: 
				input2.setText("0");
				pointer++;
				break;
			case 3: 
				input3.setText("0");
				pointer++;
				break;
			case 4: 
				input4.setText("0");
				pointer++;
				break;

			}
			if (pointer>4) {
				pointer = 1;
			}
		});
		btnclear.setOnAction(e->{
			input1.clear();
			input2.clear();
			input3.clear();
			input4.clear();
			pointer= 1;
		});
		
		btnSubmit.setOnAction(e->{
			String result ="";
			if (Integer.parseInt(input1.getText())==Integer.parseInt(ans.charAt(0)+"")) {
				result=result+"X";
			}else if(ans.contains(input1.getText())){
				result=result+"O";
			}else {
				result=result+"-";
			}
			
			if (Integer.parseInt(input2.getText())==Integer.parseInt(ans.charAt(1)+"")) {
				result=result+"X";
			}else if(ans.contains(input2.getText())){
				result=result+"O";
			}else {
				result=result+"-";
			}
			
			if (Integer.parseInt(input3.getText())==Integer.parseInt(ans.charAt(2)+"")) {
				result=result+"X";
			}else if(ans.contains(input3.getText())){
				result=result+"O";
			}else {
				result=result+"-";
			}
			
			if (Integer.parseInt(input4.getText())==Integer.parseInt(ans.charAt(3)+"")) {
				result=result+"X";
			}else if(ans.contains(input4.getText())){
				result=result+"O";
			}else {
				result=result+"-";
			}
			
			
			if (turn==0) {
				turn = 5;
				lblTurns.setText("No. of turns left : "+turn);
				new Alert(AlertType.ERROR,"Game Over",ButtonType.CLOSE).showAndWait();
			}else {

				if (result.equals("XXXX")) {
					new Alert(AlertType.CONFIRMATION,"You Won!!!",ButtonType.CLOSE).showAndWait();
					turn = 5;
					lblTurns.setText("No. of turns left : "+turn);
				}else {
					new Alert(AlertType.ERROR,result+" is your answer!",ButtonType.CLOSE).showAndWait();
					--turn;
					lblTurns.setText("No. of turns left : "+turn);				}
			}
			
		});
	}
}
