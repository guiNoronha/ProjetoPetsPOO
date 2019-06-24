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

public class ListarEventoController implements Initializable{
	@FXML
    public Button MainBtnCadastrarEventos;
	@FXML
    public Button MainBtnExcluirEventos;
	
	@FXML
    private TableView<Evento> MainTabelaEventos = new TableView<Evento>();
    @FXML
    private TableColumn<Evento, String> MainColunaEventosNome;
	@FXML
    private TableColumn<Evento, String> MainColunaEventosData;
	@FXML
    private TableColumn<Evento, String> MainColunaEventosLocal;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Context c = Context.getInstance();
    	Pessoa usuario = c.getUsuarioAtual();
    	if(usuario.getPesTipo() != 1) {
//    		MainBtnCadastrarEventos.setDisable(true);
//    		MainBtnExcluirEventos.setDisable(true);
    	}
    	
    	MainColunaEventosNome.setCellValueFactory(new Callback<CellDataFeatures<Evento, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Evento, String> data){  
			return data.getValue().getEventoNome();
		}  
		});
		
		
    	MainColunaEventosData.setCellValueFactory(new Callback<CellDataFeatures<Evento, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Evento, String> data){  
			return data.getValue().getEventoData();
		}  
		});
		
    	MainColunaEventosLocal.setCellValueFactory(new Callback<CellDataFeatures<Evento, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Evento, String> data){  
			return data.getValue().getEventoLocal();
		}  
		});
    	
		try {
			ListarEvento();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
	public void ListarEvento() throws Exception {
		MainTabelaEventos.setItems(listaDeEventos());
	}
	
	private ObservableList<Evento> listaDeEventos() throws Exception {
    	EventoDAO eDAO = new EventoDAO();
        return FXCollections.observableArrayList(eDAO.getLista());
    }
	
	@FXML
	public void manterCadastroEvento() throws Exception {
		// Carrego o FXML do Evento
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../telas_poo/CadastroEvento.fxml"));
		Pane root = loader.load();
		
		// Atribuo o load do FXML a uma nova instancia do controller que faz referncia a ele
		ManterEventoController ec = (ManterEventoController) loader.getController();
		ec.preencherComboBoxResponsavel();
		
		// Verifico se é uma edição de Evento ou cadastro
		if(MainTabelaEventos.getSelectionModel().getSelectedItem() != null) {
			
			// Crio uma nova instancia do objeto Evento
			Evento e = new Evento();
			
			// Atribuo o item selecionado ao Evento
			e = MainTabelaEventos.getSelectionModel().getSelectedItem();
			
			// Chamada da função que vai atribuir os valores para a visualização do Evento já cadastrado
			ec.editarEvento(e);
		}
		
		// Crio uma scena da visualização da tela de cadastro do Evento e configuro ela
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setTitle("Cadastro de novo Evento");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		
		// Comandos depois dessa linha só serão executados quando a tela for fechada
		stage.showAndWait();
		
		// Carrego novamemte a tabela com os Eventos
		ListarEvento();
	}
	
	@FXML
	public void excluirEvento() throws Exception {
		EventoDAO eDAO = new EventoDAO();
		if(MainTabelaEventos.getSelectionModel().getSelectedItem() != null) {
			Evento e = MainTabelaEventos.getSelectionModel().getSelectedItem();
			eDAO.remover(e);
			ListarEvento();
		}
		
	}
}