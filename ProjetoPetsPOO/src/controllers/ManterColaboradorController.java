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
	public TextField CadPessoaTxtFieldNome;
	
	@FXML
	public TextField CadPessoaTxtFieldCpf;
	
	@FXML
	public TextField CadPessoaTxtFieldTelefone;
	
	@FXML
	public TextField CadPessoaTxtFieldCep;
	
	@FXML
	public TextField CadPessoaTxtFieldNumero;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void manterColaborador() throws Exception {
		ColaboradorDAO cDAO = new ColaboradorDAO();
		colaboradorAuxiliar.setPesNome(CadPessoaTxtFieldNome.getText());
		colaboradorAuxiliar.setPesCpf(CadPessoaTxtFieldCpf.getText());
		colaboradorAuxiliar.setPesTelefone(CadPessoaTxtFieldTelefone.getText());
		colaboradorAuxiliar.setPesCep(CadPessoaTxtFieldCep.getText());
		colaboradorAuxiliar.setPesNumero(CadPessoaTxtFieldNumero.getText());
		
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
		Stage stage = (Stage) CadPessoaTxtFieldNome.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarColaborador(Colaborador c) {
		// Atribuo o colaborador que foi passado por parametro a um obejto colaborador auxiliar que existe como atributo dessa classe
		colaboradorAuxiliar = c;
		
		// Atribuo o valor do nome do colaborador ao seu respectivo textField
		CadPessoaTxtFieldNome.textProperty().setValue(c.getPesNome().getValue());
		CadPessoaTxtFieldCpf.textProperty().setValue(c.getPesCpf().getValue());
		CadPessoaTxtFieldTelefone.textProperty().setValue(c.getPesTelefone().getValue());
		CadPessoaTxtFieldCep.textProperty().setValue(c.getPesCep());
		CadPessoaTxtFieldNumero.textProperty().setValue(c.getPesNumero());
		
		
	}
}