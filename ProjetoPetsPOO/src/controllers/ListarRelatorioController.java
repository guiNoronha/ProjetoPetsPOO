package controllers;

import dao.*;
import classes.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.GerarRelatorio;
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
	
	@FXML
	public void relatorioAnimaisAdotados() throws Exception {
		
		AdocaoDAO adDAO = new AdocaoDAO();
		GerarRelatorio gr = new GerarRelatorio();
		gr.gerarRelatorioAnimais(adDAO.getLista());
		
	}
	
	@FXML
	public void relatorioItensDoados() throws Exception {
		SuprimentoDAO sdDAO = new SuprimentoDAO();
		GerarRelatorio gr = new GerarRelatorio();
		gr.gerarRelatorioItensDoados(sdDAO.getLista());
		
	}
	
	@FXML
	public void relatorioFluxoCaixa() throws Exception {
		FinanceiroDAO fiDAO = new FinanceiroDAO();
		GerarRelatorio gr = new GerarRelatorio();
		gr.gerarRelatorioFluxoCaixa(fiDAO.getLista());
		
	}
}