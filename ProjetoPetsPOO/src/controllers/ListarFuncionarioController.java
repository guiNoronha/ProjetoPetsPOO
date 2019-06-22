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

public class ListarFuncionarioController implements Initializable{
	
	@FXML
	public TextField CadFuncionarioTxtFieldNome;
	
	@FXML
    private TableView<Funcionario> MainTabelaFuncionarios = new TableView<Funcionario>();
    @FXML
    private TableColumn<Funcionario, String> MainColunaFuncionariosNome;
	@FXML
    private TableColumn<Funcionario, String> MainColunaFuncionariosCpf;
	@FXML
    private TableColumn<Funcionario, String> MainColunaFuncionariosSalario;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	MainColunaFuncionariosNome.setCellValueFactory(new Callback<CellDataFeatures<Funcionario, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Funcionario, String> data){  
			return data.getValue().getPesNome();
		}  
		});
		
		
    	MainColunaFuncionariosCpf.setCellValueFactory(new Callback<CellDataFeatures<Funcionario, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Funcionario, String> data){  
			return data.getValue().getPesCpf();
		}  
		});
    	
    	MainColunaFuncionariosSalario.setCellValueFactory(new Callback<CellDataFeatures<Funcionario, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Funcionario, String> data){  
			return data.getValue().getFuncSalario();
		}  
		});
    	
		try {
			ListarFuncionario();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
	public void ListarFuncionario() throws Exception {
		MainTabelaFuncionarios.setItems(listaDeFuncionarios());
	}
	
	private ObservableList<Funcionario> listaDeFuncionarios() throws Exception {
    	FuncionarioDAO cDAO = new FuncionarioDAO();
        return FXCollections.observableArrayList(cDAO.getLista());
    }
	
	@FXML
	public void manterCadastroFuncionario() throws Exception {
		// Carrego o FXML do Funcionario
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../telas_poo/CadastroFuncionario.fxml"));
		Pane root = loader.load();
		
		// Atribuo o load do FXML a uma nova instancia do controller que faz referncia a ele
		ManterFuncionarioController cc = (ManterFuncionarioController) loader.getController();
		
		// Verifico se é uma edição de Funcionario ou cadastro
		if(MainTabelaFuncionarios.getSelectionModel().getSelectedItem() != null) {
			
			// Crio uma nova instancia do objeto Funcionario
			Funcionario c = new Funcionario();
			
			// Atribuo o item selecionado ao Funcionario
			c = MainTabelaFuncionarios.getSelectionModel().getSelectedItem();
			
			// Chamada da função que vai atribuir os valores para a visualização do Funcionario já cadastrado
			cc.editarFuncionario(c);
		}
		
		// Crio uma scena da visualização da tela de cadastro do Funcionario e configuro ela
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setTitle("Cadastro de novo Funcionario");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		
		// Comandos depois dessa linha só serão executados quando a tela for fechada
		stage.showAndWait();
		
		// Carrego novamemte a tabela com os Funcionarios
		ListarFuncionario();
	}
	
	@FXML
	public void excluirFuncionario() throws Exception {
		FuncionarioDAO cDAO = new FuncionarioDAO();
		if(MainTabelaFuncionarios.getSelectionModel().getSelectedItem() != null) {
			Funcionario c = MainTabelaFuncionarios.getSelectionModel().getSelectedItem();
			cDAO.remover(c);
			ListarFuncionario();
		}
		
	}
}