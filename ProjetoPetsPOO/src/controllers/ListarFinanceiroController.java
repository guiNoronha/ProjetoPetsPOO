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

public class ListarFinanceiroController implements Initializable{
	
	@FXML
    private TableView<Financeiro> MainTabelaFinanceiro = new TableView<Financeiro>();
    @FXML
    private TableColumn<Financeiro, String> MainColunaFinanceiroCategoria;
	@FXML
    private TableColumn<Financeiro, String> MainColunaFinanceiroDescricao;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
		MainColunaFinanceiroCategoria.setCellValueFactory(new Callback<CellDataFeatures<Financeiro, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Financeiro, String> data){  
			return data.getValue().getFinCategoria();
		}  
		});
		
		
		MainColunaFinanceiroDescricao.setCellValueFactory(new Callback<CellDataFeatures<Financeiro, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Financeiro, String> data){  
			return data.getValue().getFinDescricao();
		}  
		});
		
		try {
			ListarFinanceiro();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
	public void ListarFinanceiro() throws Exception {
    	MainTabelaFinanceiro.setItems(listaDeFinanceiros());
	}
	
	private ObservableList<Financeiro> listaDeFinanceiros() throws Exception {
    	FinanceiroDAO cDAO = new FinanceiroDAO();
        return FXCollections.observableArrayList(cDAO.getLista());
    }
	
	@FXML
	public void manterCadastroFinanceiro() throws Exception {
		// Carrego o FXML do Financeiro
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../telas_poo/CadastroFinanceiro.fxml"));
		Pane root = loader.load();
		
		// Atribuo o load do FXML a uma nova instancia do controller que faz referncia a ele
		ManterFinanceiroController cc = (ManterFinanceiroController) loader.getController();
		
		// Verifico se é uma edição de Financeiro ou cadastro
		if(MainTabelaFinanceiro.getSelectionModel().getSelectedItem() != null) {
			
			// Crio uma nova instancia do objeto Financeiro
			Financeiro c = new Financeiro();
			
			// Atribuo o item selecionado ao Financeiro
			c = MainTabelaFinanceiro.getSelectionModel().getSelectedItem();
			
			// Chamada da função que vai atribuir os valores para a visualização do Financeiro já cadastrado
			cc.editarFinanceiro(c);
		}
		
		// Crio uma scena da visualização da tela de cadastro do Financeiro e configuro ela
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setTitle("Cadastro de novo Financeiro");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		
		// Comandos depois dessa linha só serão executados quando a tela for fechada
		stage.showAndWait();
		
		// Carrego novamemte a tabela com os Financeiros
		ListarFinanceiro();
	}
	
	@FXML
	public void excluirFinanceiro() throws Exception {
		FinanceiroDAO cDAO = new FinanceiroDAO();
		if(MainTabelaFinanceiro.getSelectionModel().getSelectedItem() != null) {
			Financeiro c = MainTabelaFinanceiro.getSelectionModel().getSelectedItem();
			cDAO.remover(c);
			ListarFinanceiro();
		}
		
	}
}