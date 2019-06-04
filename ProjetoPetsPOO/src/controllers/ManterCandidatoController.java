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

public class ManterCandidatoController implements Initializable {
	Candidato candidatoAuxiliar = new Candidato();
	
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
	public void manterCandidato() throws Exception {
		CandidatoDAO cDAO = new CandidatoDAO();
		candidatoAuxiliar.setPesNome(CadPessoaTxtFieldNome.getText());
		candidatoAuxiliar.setPesCpf(CadPessoaTxtFieldCpf.getText());
		candidatoAuxiliar.setPesTelefone(CadPessoaTxtFieldTelefone.getText());
		candidatoAuxiliar.setPesCep(CadPessoaTxtFieldCep.getText());
		candidatoAuxiliar.setPesNumero(CadPessoaTxtFieldNumero.getText());
		
		if(candidatoAuxiliar.getPesId() != null) {	
			cDAO.alterar(candidatoAuxiliar);
		}
		else{
			cDAO.inserir(candidatoAuxiliar);
		}
		
		fecharTelaCadastroCandidato();
	}
	
	@FXML
	public void fecharTelaCadastroCandidato() throws Exception {
		Stage stage = (Stage) CadPessoaTxtFieldNome.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarCandidato(Candidato c) {
		// Atribuo o candidato que foi passado por parametro a um obejto candidato auxiliar que existe como atributo dessa classe
		candidatoAuxiliar = c;
		
		// Atribuo o valor do nome do candidato ao seu respectivo textField
		CadPessoaTxtFieldNome.textProperty().setValue(c.getPesNome().getValue());
		CadPessoaTxtFieldCpf.textProperty().setValue(c.getPesCpf().getValue());
		CadPessoaTxtFieldTelefone.textProperty().setValue(c.getPesTelefone().getValue());
		CadPessoaTxtFieldCep.textProperty().setValue(c.getPesCep());
		CadPessoaTxtFieldNumero.textProperty().setValue(c.getPesNumero());
		
		
	}
}