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
	public void manterSuprimento() throws Exception {
		SuprimentoDAO cDAO = new SuprimentoDAO();
		suprimentoAuxiliar.setPesNome(CadPessoaTxtFieldNome.getText());
		suprimentoAuxiliar.setPesCpf(CadPessoaTxtFieldCpf.getText());
		suprimentoAuxiliar.setPesTelefone(CadPessoaTxtFieldTelefone.getText());
		suprimentoAuxiliar.setPesCep(CadPessoaTxtFieldCep.getText());
		suprimentoAuxiliar.setPesNumero(CadPessoaTxtFieldNumero.getText());
		
		if(suprimentoAuxiliar.getPesId() != null) {	
			cDAO.alterar(suprimentoAuxiliar);
		}
		else{
			cDAO.inserir(suprimentoAuxiliar);
		}
		
		fecharTelaCadastroSuprimento();
	}
	
	@FXML
	public void fecharTelaCadastroSuprimento() throws Exception {
		Stage stage = (Stage) CadPessoaTxtFieldNome.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarSuprimento(Suprimento c) {
		// Atribuo o suprimento que foi passado por parametro a um obejto suprimento auxiliar que existe como atributo dessa classe
		suprimentoAuxiliar = c;
		
		// Atribuo o valor do nome do suprimento ao seu respectivo textField
		CadPessoaTxtFieldNome.textProperty().setValue(c.getPesNome().getValue());
		CadPessoaTxtFieldCpf.textProperty().setValue(c.getPesCpf().getValue());
		CadPessoaTxtFieldTelefone.textProperty().setValue(c.getPesTelefone().getValue());
		CadPessoaTxtFieldCep.textProperty().setValue(c.getPesCep());
		CadPessoaTxtFieldNumero.textProperty().setValue(c.getPesNumero());
		
		
	}
}