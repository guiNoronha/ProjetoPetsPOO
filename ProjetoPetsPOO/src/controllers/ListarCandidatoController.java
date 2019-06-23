package controllers;

import dao.*;
import classes.*;
import controllers.*;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListarCandidatoController implements Initializable{
	@FXML
    public Button MainBtnCadastrarCandidatos;
	@FXML
    public Button MainBtnExcluirCandidatos;
	
	@FXML
    private TableView<Candidato> MainTabelaCandidatos = new TableView<Candidato>();
    @FXML
    private TableColumn<Candidato, String> MainColunaCandidatosNome;
	@FXML
    private TableColumn<Candidato, String> MainColunaCandidatosCpf;
	@FXML
    private TableColumn<Candidato, String> MainColunaCandidatosTelefone;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Context c = Context.getInstance();
    	Pessoa usuario = c.getUsuarioAtual();
    	if(usuario.getPesTipo() != 1) {
    		MainBtnCadastrarCandidatos.setDisable(true);
    		MainBtnExcluirCandidatos.setDisable(true);
    	}
    	
		MainColunaCandidatosNome.setCellValueFactory(new Callback<CellDataFeatures<Candidato, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Candidato, String> data){  
			return data.getValue().getPesNome();
		}  
		});
		
		
		MainColunaCandidatosCpf.setCellValueFactory(new Callback<CellDataFeatures<Candidato, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Candidato, String> data){  
			return data.getValue().getPesCpf();
		}  
		});
		
		MainColunaCandidatosTelefone.setCellValueFactory(new Callback<CellDataFeatures<Candidato, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Candidato, String> data){  
			return data.getValue().getPesTelefone();
		}  
		});
    	
		try {
			ListarCandidato();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
	public void ListarCandidato() throws Exception {
		MainTabelaCandidatos.setItems(listaDeCandidatos());
	}
	
	private ObservableList<Candidato> listaDeCandidatos() throws Exception {
    	CandidatoDAO cDAO = new CandidatoDAO();
        return FXCollections.observableArrayList(cDAO.getLista());
    }
	
	@FXML
	public void manterCadastroCandidato() throws Exception {
		// Carrego o FXML do Candidato
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../telas_poo/CadastroCandidato.fxml"));
		Pane root = loader.load();
		
		// Atribuo o load do FXML a uma nova instancia do controller que faz referncia a ele
		ManterCandidatoController cc = (ManterCandidatoController) loader.getController();
		
		// Verifico se é uma edição de Candidato ou cadastro
		if(MainTabelaCandidatos.getSelectionModel().getSelectedItem() != null) {
			
			// Crio uma nova instancia do objeto Candidato
			Candidato c = new Candidato();
			
			// Atribuo o item selecionado ao Candidato
			c = MainTabelaCandidatos.getSelectionModel().getSelectedItem();
			
			// Chamada da função que vai atribuir os valores para a visualização do Candidato já cadastrado
			cc.editarCandidato(c);
		}
		
		// Crio uma scena da visualização da tela de cadastro do Candidato e configuro ela
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setTitle("Cadastro de novo Candidato");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		
		// Comandos depois dessa linha só serão executados quando a tela for fechada
		stage.showAndWait();
		
		// Carrego novamemte a tabela com os Candidatos
		ListarCandidato();
	}
	
	@FXML
	public void excluirCandidato() throws Exception {
		CandidatoDAO cDAO = new CandidatoDAO();
		if(MainTabelaCandidatos.getSelectionModel().getSelectedItem() != null) {
			Candidato c = MainTabelaCandidatos.getSelectionModel().getSelectedItem();
			cDAO.remover(c);
			ListarCandidato();
		}
		
	}
}