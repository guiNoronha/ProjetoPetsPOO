package controllers;

import dao.*;
import classes.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManterSuprimentoController implements Initializable {
	Suprimento suprimentoAuxiliar = new Suprimento();
	
	@FXML
	public TextField CadSuprimentoTxtAreaTipo;
	
	@FXML
	public TextArea CadSuprimentoTxtAreaOrigem;
	
	@FXML
	public TextArea CadSuprimentoTxtAreaDescricao;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void manterSuprimento() throws Exception {
		SuprimentoDAO sDAO = new SuprimentoDAO();
		suprimentoAuxiliar.setSupTipo(CadSuprimentoTxtAreaTipo.getText());
		suprimentoAuxiliar.setSupOrigem(CadSuprimentoTxtAreaOrigem.getText());
		suprimentoAuxiliar.setSupDescricao(CadSuprimentoTxtAreaDescricao.getText());
		
		if(suprimentoAuxiliar.getSupId() != null) {	
			sDAO.alterar(suprimentoAuxiliar);
		}
		else{
			sDAO.inserir(suprimentoAuxiliar);
		}
		
		fecharTelaCadastroSuprimento();
	}
	
	@FXML
	public void fecharTelaCadastroSuprimento() throws Exception {
		Stage stage = (Stage) CadSuprimentoTxtAreaTipo.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarSuprimento(Suprimento s) {
		// Atribuo o suprimento que foi passado por parametro a um obejto suprimento auxiliar que existe como atributo dessa classe
		suprimentoAuxiliar = s;
		
		// Atribuo o valor do nome do suprimento ao seu respectivo textField
		CadSuprimentoTxtAreaTipo.textProperty().setValue(s.getSupTipo().getValue());
		CadSuprimentoTxtAreaOrigem.textProperty().setValue(s.getSupOrigem().getValue());
		CadSuprimentoTxtAreaDescricao.textProperty().setValue(s.getSupDescricao().getValue());
		
	}
}