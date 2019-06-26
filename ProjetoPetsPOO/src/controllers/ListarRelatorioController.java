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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ListarRelatorioController implements Initializable {
	@FXML
    public Button buttonRelAnimaisAdotados;
	@FXML
    public Button buttonItensDoados;
	@FXML
    public Button buttonRelFluxoCaixa;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Context c = Context.getInstance();
    	Pessoa usuario = c.getUsuarioAtual();
    	if(usuario.getPesTipo() != 1) {
    		buttonRelAnimaisAdotados.setDisable(true);
    		buttonItensDoados.setDisable(true);
    		buttonRelFluxoCaixa.setDisable(true);
    	}
	}
}