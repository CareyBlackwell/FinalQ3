package application;

import java.text.DecimalFormat;
import java.util.Formatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EstimatorViewController {
	@FXML
	TextField TGI;
	@FXML
	TextField TMD;
	@FXML
	TextField MIR;
	@FXML
	TextField DownPayment;
	@FXML
	ComboBox<Integer> Term;
	@FXML
	Button Calculate;
	@FXML
	Label Estimate;
	
	private Stage infoStage;
	ObservableList<Integer> TermList = FXCollections.observableArrayList(10, 15, 30);
	
	
	@FXML
    private void initialize() {
		
		Term.setItems(TermList);
    }
	public void setInfoStage(Stage infoStage){
		this.infoStage=infoStage;
	}
	
	public void handleCalculate(){
		DecimalFormat currencyFormat = new DecimalFormat("0,000.00");
		String estimateStr = currencyFormat.format(this.calculate());
		Estimate.setText("Estimate is: $" + estimateStr);
	}
	private Double calculate(){
		//converting text fields to numbers
		double monthlyIncome = Double.parseDouble(TGI.getText())/12;
		double downPaymentDouble = Double.parseDouble(DownPayment.getText());
		double interestRate = Double.parseDouble(MIR.getText())/1200;
		double numOfPayments = Term.getValue()*12;
		double monthlyAfford;
		
		//determine how much can be spent monthly
		if ((monthlyIncome*0.18)>((monthlyIncome*0.36)-downPaymentDouble)){
			monthlyAfford = (monthlyIncome*0.36 - downPaymentDouble);
			System.out.println(monthlyAfford);
		}else{
			monthlyAfford = monthlyIncome*0.18;
			System.out.println(monthlyAfford);
		}
		
		//Calculate how much can be afforded
		Double totAfford = (monthlyAfford *(Math.pow(1 + interestRate, numOfPayments)-1)
				/(interestRate * Math.pow(1 + interestRate, numOfPayments)))-downPaymentDouble;
		return totAfford;
	}
	
}