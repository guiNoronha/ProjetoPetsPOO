package controllers;

import dao.*;
import classes.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	Adocao adocaoAuxiliar = new Adocao();
	
	@FXML
	public TextField LoginTxtFieldUsuario;
	
	@FXML
	public TextField LoginTxtFieldSenha;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void realizarLogin() throws Exception {
		Pessoa pes = new Pessoa();
		PessoaDAO pesDAO = new PessoaDAO();
		
		pes.setPesCpf(LoginTxtFieldUsuario.getText());
		pes.setPesSenha(LoginTxtFieldSenha.getText());
		pes = pesDAO.buscarDadosLogin(pes);
		
//		if(pes.getPesId() != null) {
			Context c = Context.getInstance();
			c.setUsuarioAtual(pes);
			
			Stage stage = (Stage) LoginTxtFieldSenha.getScene().getWindow();
			stage.close();
			
			AnchorPane root = FXMLLoader.load(getClass().getResource("../telas_poo/Main.fxml"));
			Scene scene = new Scene(root);
			Stage stageMain = new Stage();
			stageMain.setScene(scene);
			stageMain.show();	
//		}
	}
}