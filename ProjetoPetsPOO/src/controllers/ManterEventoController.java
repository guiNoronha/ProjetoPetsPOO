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

public class ManterEventoController implements Initializable {
	Evento eventoAuxiliar = new Evento();
	
	@FXML
	public TextField CadEventoTxtFieldNome;
	
	@FXML
	public TextField CadEventoTxtFieldData;
	
	@FXML
	public TextField CadEventoTxtFieldLocal;
	
	@FXML
	public TextArea CadEventoTxtFieldDescricao;
	
	@FXML
	public ComboBox CadEventoTxtFieldResponsavel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void manterEvento() throws Exception {
		EventoDAO cDAO = new EventoDAO();
		eventoAuxiliar.setEventoNome(CadEventoTxtFieldNome.getText());
		eventoAuxiliar.setEventoData(CadEventoTxtFieldData.getText());
		eventoAuxiliar.setEventoLocal(CadEventoTxtFieldLocal.getText());
		eventoAuxiliar.setEventoDescricao(CadEventoTxtFieldDescricao.getText());
		eventoAuxiliar.setPesIdResp((Colaborador)CadEventoTxtFieldResponsavel.getValue());

		if(eventoAuxiliar.getEventoId() != null) {	
			cDAO.alterar(eventoAuxiliar);
		}
		else{
			cDAO.inserir(eventoAuxiliar);
		}
		
		fecharTelaCadastroEvento();
	}
	
	@FXML
	public void fecharTelaCadastroEvento() throws Exception {
		Stage stage = (Stage) CadEventoTxtFieldNome.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarEvento(Evento e) {
		// Atribuo o evento que foi passado por parametro a um obejto evento auxiliar que existe como atributo dessa classe
		eventoAuxiliar = e;
		
		// Atribuo o valor do nome do evento ao seu respectivo textField
		CadEventoTxtFieldNome.textProperty().setValue(e.getEventoNome().getValue());
		CadEventoTxtFieldData.textProperty().setValue(e.getEventoData().getValue());
		CadEventoTxtFieldLocal.textProperty().setValue(e.getEventoLocal().getValue());
		CadEventoTxtFieldDescricao.textProperty().setValue(e.getEventoDescricao());
		CadEventoTxtFieldResponsavel.setValue(e.getPesIdResp());
		
	}
	
	public void preencherComboBoxResponsavel() throws Exception {

		CadEventoTxtFieldResponsavel.getItems().clear();
		
		ColaboradorDAO colDAO = new ColaboradorDAO();
		ArrayList<Colaborador> colaboradores;
		colaboradores = colDAO.getLista();
		
		CadEventoTxtFieldResponsavel.getItems().addAll(colaboradores);
	}
}