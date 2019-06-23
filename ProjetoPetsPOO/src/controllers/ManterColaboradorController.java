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

public class ManterColaboradorController implements Initializable {
	Colaborador colaboradorAuxiliar = new Colaborador();
	
	@FXML
	public TextField CadColaboradorTxtFieldNome;
	
	@FXML
	public TextField CadColaboradorTxtFieldCpf;
	
	@FXML
	public TextField CadColaboradorTxtFieldTelefone;
	
	@FXML
	public TextField CadColaboradorTxtFieldCep;
	
	@FXML
	public TextField CadColaboradorTxtFieldNumero;
	
	@FXML
	public TextField CadColaboradorTxtFieldOcupacao;
	
	@FXML
	public TextField CadColaboradorTxtFieldNascimento;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void manterColaborador() throws Exception {
		ColaboradorDAO cDAO = new ColaboradorDAO();
		colaboradorAuxiliar.setPesNome(CadColaboradorTxtFieldNome.getText());
		colaboradorAuxiliar.setPesCpf(CadColaboradorTxtFieldCpf.getText());
		colaboradorAuxiliar.setPesTelefone(CadColaboradorTxtFieldTelefone.getText());
		colaboradorAuxiliar.setPesCep(CadColaboradorTxtFieldCep.getText());
		colaboradorAuxiliar.setPesNumero(CadColaboradorTxtFieldNumero.getText());
		colaboradorAuxiliar.setColFuncao(CadColaboradorTxtFieldOcupacao.getText());
		colaboradorAuxiliar.setPesTipo(2);
		
		if(colaboradorAuxiliar.getPesId() != null) {	
			cDAO.alterar(colaboradorAuxiliar);
		}
		else{
			cDAO.inserir(colaboradorAuxiliar);
		}
		
		fecharTelaCadastroColaborador();
	}
	
	@FXML
	public void fecharTelaCadastroColaborador() throws Exception {
		Stage stage = (Stage) CadColaboradorTxtFieldNome.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarColaborador(Colaborador c) {
		// Atribuo o colaborador que foi passado por parametro a um obejto colaborador auxiliar que existe como atributo dessa classe
		colaboradorAuxiliar = c;
		
		// Atribuo o valor do nome do colaborador ao seu respectivo textField
		CadColaboradorTxtFieldNome.textProperty().setValue(c.getPesNome().getValue());
		CadColaboradorTxtFieldCpf.textProperty().setValue(c.getPesCpf().getValue());
		CadColaboradorTxtFieldTelefone.textProperty().setValue(c.getPesTelefone().getValue());
		CadColaboradorTxtFieldCep.textProperty().setValue(c.getPesCep());
		CadColaboradorTxtFieldNumero.textProperty().setValue(c.getPesNumero());
		CadColaboradorTxtFieldOcupacao.textProperty().setValue(c.getColFuncao().getValue());
		
		
	}
}