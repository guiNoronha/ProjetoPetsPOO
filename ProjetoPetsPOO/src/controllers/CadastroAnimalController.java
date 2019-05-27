package controllers;

import dao.*;
import classes.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroAnimalController implements Initializable {
	
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
		Animal a = new Animal();
		a.setAniNome(CadAnimalTxtFieldNome.getText());
//		a.setAniEntrada(CadAnimalTxtFieldNome.getText());
		a.setAniRaca(CadAnimalTxtFieldRaca.getText());
		a.setAniTipo(CadAnimalTxtFieldEspecie.getText());
		aDAO.inserir(a);
	}
	
	

}