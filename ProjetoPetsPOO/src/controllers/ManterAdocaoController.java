package controllers;

import dao.*;
import classes.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManterAdocaoController implements Initializable {
	Adocao adocaoAuxiliar = new Adocao();
	
	@FXML
	public ComboBox comboBoxAnimal;
	
	@FXML
	public ComboBox comboBoxCandidato;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void manterAdocao() throws Exception {
		AdocaoDAO aDAO = new AdocaoDAO();
		adocaoAuxiliar.setAni((Animal)comboBoxAnimal.getValue());
		adocaoAuxiliar.setCand((Candidato)comboBoxCandidato.getValue());
		
		if(adocaoAuxiliar.getAdocaoId() != null) {	
			aDAO.alterar(adocaoAuxiliar);
		}
		else{
			aDAO.inserir(adocaoAuxiliar);
		}
		
		fecharTelaCadastroAdocao();
	}
	
	@FXML
	public void fecharTelaCadastroAdocao() throws Exception {
		Stage stage = (Stage) comboBoxAnimal.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarAdocao(Adocao a) {
		// Atribuo o adocao que foi passado por parametro a um obejto adocao auxiliar que existe como atributo dessa classe
		adocaoAuxiliar = a;
		
		// Atribuo o valor do nome do adocao ao seu respectivo textField
		comboBoxCandidato.setValue(a.getCand());
		comboBoxAnimal.setValue(a.getAni());
		
	}
	
	public void preencherComboCoxAnimal() throws Exception {

		comboBoxAnimal.getItems().clear();
		
		AnimalDAO aniDAO = new AnimalDAO();
		ArrayList<Animal> animais;
		animais = aniDAO.getLista();
		
		comboBoxAnimal.getItems().addAll(animais);
	}
	
	public void preencherComboCoxCandidato() throws Exception {

		comboBoxCandidato.getItems().clear();
		
		CandidatoDAO candDAO = new CandidatoDAO();
		ArrayList<Candidato> candidatos;
		candidatos = candDAO.getLista();
		
		comboBoxCandidato.getItems().addAll(candidatos);
	}
}