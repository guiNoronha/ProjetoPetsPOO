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

public class ListarColaboradorController implements Initializable{
	
	@FXML
    private TableView<Colaborador> MainTabelaColaboradores = new TableView<Colaborador>();
    @FXML
    private TableColumn<Colaborador, String> MainColunaColaboradoresNome;
	@FXML
    private TableColumn<Colaborador, String> MainColunaColaboradoresCpf;
	@FXML
    private TableColumn<Colaborador, String> MainColunaColaboradoresFuncao;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
		MainColunaColaboradoresNome.setCellValueFactory(new Callback<CellDataFeatures<Colaborador, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Colaborador, String> data){  
			return data.getValue().getPesNome();
		}  
		});
		
		
		MainColunaColaboradoresCpf.setCellValueFactory(new Callback<CellDataFeatures<Colaborador, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Colaborador, String> data){  
			return data.getValue().getPesCpf();
		}  
		});
		
		MainColunaColaboradoresFuncao.setCellValueFactory(new Callback<CellDataFeatures<Colaborador, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Colaborador, String> data){  
			return data.getValue().getColFuncao();
		}  
		});
    	
		try {
			ListarColaborador();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
	public void ListarColaborador() throws Exception {
		MainTabelaColaboradores.setItems(listaDeColaboradores());
	}
	
	private ObservableList<Colaborador> listaDeColaboradores() throws Exception {
    	ColaboradorDAO cDAO = new ColaboradorDAO();
        return FXCollections.observableArrayList(cDAO.getLista());
    }
	
	@FXML
	public void manterCadastroColaborador() throws Exception {
		// Carrego o FXML do Colaborador
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../telas_poo/CadastroColaborador.fxml"));
		Pane root = loader.load();
		
		// Atribuo o load do FXML a uma nova instancia do controller que faz referncia a ele
		ManterColaboradorController cc = (ManterColaboradorController) loader.getController();
		
		// Verifico se é uma edição de Colaborador ou cadastro
		if(MainTabelaColaboradores.getSelectionModel().getSelectedItem() != null) {
			
			// Crio uma nova instancia do objeto Colaborador
			Colaborador c = new Colaborador();
			
			// Atribuo o item selecionado ao Colaborador
			c = MainTabelaColaboradores.getSelectionModel().getSelectedItem();
			
			// Chamada da função que vai atribuir os valores para a visualização do Colaborador já cadastrado
			cc.editarColaborador(c);
		}
		
		// Crio uma scena da visualização da tela de cadastro do Colaborador e configuro ela
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setTitle("Cadastro de novo Colaborador");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		
		// Comandos depois dessa linha só serão executados quando a tela for fechada
		stage.showAndWait();
		
		// Carrego novamemte a tabela com os Colaboradores
		ListarColaborador();
	}
	
	@FXML
	public void excluirColaborador() throws Exception {
		ColaboradorDAO cDAO = new ColaboradorDAO();
		if(MainTabelaColaboradores.getSelectionModel().getSelectedItem() != null) {
			Colaborador c = MainTabelaColaboradores.getSelectionModel().getSelectedItem();
			cDAO.remover(c);
			ListarColaborador();
		}
		
	}
}