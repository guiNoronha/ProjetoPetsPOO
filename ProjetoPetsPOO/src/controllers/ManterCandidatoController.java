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
	Animal animalAuxiliar = new Animal();
	
	@FXML
	public TextField CadAnimalTxtFieldNome;
	
	@FXML
	public TextField CadAnimalTxtFieldCodigo;
	
	@FXML
	public TextField CadAnimalTxtFieldEspecie;
	
	@FXML
	public TextField CadAnimalTxtFieldStatus;
	
	@FXML
	public TextField CadAnimalTxtFieldRaca;
	
	@FXML
	public TextArea CadAnimalTxtAreaDescricao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void manterAnimal() throws Exception {
		AnimalDAO aDAO = new AnimalDAO();
		animalAuxiliar.setAniNome(CadAnimalTxtFieldNome.getText());
//		animalAuxiliar.setAniEntrada(CadAnimalTxtFieldNome.getText());
		animalAuxiliar.setAniRaca(CadAnimalTxtFieldRaca.getText());
		animalAuxiliar.setAniTipo(CadAnimalTxtFieldEspecie.getText());
		
		if(animalAuxiliar.getAniId() != null) {	
			aDAO.alterar(animalAuxiliar);
		}
		else{
			aDAO.inserir(animalAuxiliar);
		}
		
		fecharTelaCadastroAnimal();
	}
	
	@FXML
	public void fecharTelaCadastroAnimal() throws Exception {
		Stage stage = (Stage) CadAnimalTxtFieldNome.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarAnimal(Animal a) {
		// Atribuo o animal que foi passado por parametro a um obejto animal auxiliar que existe como atributo dessa classe
		animalAuxiliar = a;
		
		// Atribuo o valor do nome do animal ao seu respectivo textField
		CadAnimalTxtFieldNome.textProperty().setValue(a.getAniNome().getValue());
		CadAnimalTxtFieldRaca.textProperty().setValue(a.getAniRaca().getValue());
		CadAnimalTxtFieldEspecie.textProperty().setValue(a.getAniTipo().getValue());
		CadAnimalTxtFieldCodigo.textProperty().setValue(a.getAniId().toString());
		
	}
}