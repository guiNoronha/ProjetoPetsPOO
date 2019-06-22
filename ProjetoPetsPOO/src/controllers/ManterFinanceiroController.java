package controllers;

import dao.*;
import classes.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManterFinanceiroController implements Initializable {
	Financeiro financeiroAuxiliar = new Financeiro();
	
	@FXML
	public DatePicker CadFinanceiroDatePickerData;
	
	@FXML
	public TextField CadFinanceiroCategoria;
	
	@FXML
	public TextField CadFinanceiroTxtFieldValor;
	
	@FXML
	public TextArea CadFinanceiroTxtAreaDescricao;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void manterFinanceiro() throws Exception {
		FinanceiroDAO cDAO = new FinanceiroDAO();
//		financeiroAuxiliar.setFinData(LocalDate.parse(text)CadFinanceiroDatePickerData.getText());
		financeiroAuxiliar.setFinCategoria(CadFinanceiroCategoria.getText());
		financeiroAuxiliar.setFinValor(Float.parseFloat(CadFinanceiroTxtFieldValor.getText()));
		financeiroAuxiliar.setFinDescricao(CadFinanceiroTxtAreaDescricao.getText());
		
		if(financeiroAuxiliar.getFinId() != null) {	
			cDAO.alterar(financeiroAuxiliar);
		}
		else{
			cDAO.inserir(financeiroAuxiliar);
		}
		
		fecharTelaCadastroFinanceiro();
	}
	
	@FXML
	public void fecharTelaCadastroFinanceiro() throws Exception {
		Stage stage = (Stage) CadFinanceiroCategoria.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarFinanceiro(Financeiro f) {
		// Atribuo o financeiro que foi passado por parametro a um obejto financeiro auxiliar que existe como atributo dessa classe
		financeiroAuxiliar = f;
		
		// Atribuo o valor do nome do financeiro ao seu respectivo textField
//		CadFinanceiroDatePickerData.setValue(f.getFinData());
		CadFinanceiroCategoria.textProperty().setValue(f.getFinCategoria().getValue());
		CadFinanceiroTxtFieldValor.textProperty().setValue(f.getFinValor().toString());
		CadFinanceiroTxtAreaDescricao.textProperty().setValue(f.getFinDescricao().getValue());
		
		
	}
}