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

public class ListarSuprimentoController implements Initializable{
	
	@FXML
    private TableView<Suprimento> MainTabelaSuprimentos = new TableView<Suprimento>();
    @FXML
    private TableColumn<Suprimento, String> MainColunaSuprimentosTipo;
	@FXML
    private TableColumn<Suprimento, String> MainColunaSuprimentosOrigem;
	@FXML
    private TableColumn<Suprimento, String> MainColunaSuprimentosDescricao;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
		MainColunaSuprimentosTipo.setCellValueFactory(new Callback<CellDataFeatures<Suprimento, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Suprimento, String> data){  
			return data.getValue().getSupTipo();
		}  
		});
		
		
		MainColunaSuprimentosOrigem.setCellValueFactory(new Callback<CellDataFeatures<Suprimento, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Suprimento, String> data){  
			return data.getValue().getSupOrigem();
		}  
		});
		
		MainColunaSuprimentosDescricao.setCellValueFactory(new Callback<CellDataFeatures<Suprimento, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Suprimento, String> data){  
			return data.getValue().getSupDescricao();
		}  
		});
    	
		try {
			ListarSuprimento();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
	public void ListarSuprimento() throws Exception {
		MainTabelaSuprimentos.setItems(listaDeSuprimentos());
	}
	
	private ObservableList<Suprimento> listaDeSuprimentos() throws Exception {
    	SuprimentoDAO cDAO = new SuprimentoDAO();
        return FXCollections.observableArrayList(cDAO.getLista());
    }
	
	@FXML
	public void manterCadastroSuprimento() throws Exception {
		// Carrego o FXML do Suprimento
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../telas_poo/CadastroSuprimento.fxml"));
		Pane root = loader.load();
		
		// Atribuo o load do FXML a uma nova instancia do controller que faz referncia a ele
		ManterSuprimentoController ss = (ManterSuprimentoController) loader.getController();
//		
		// Verifico se é uma edição de Suprimento ou cadastro
		if(MainTabelaSuprimentos.getSelectionModel().getSelectedItem() != null) {
			
			// Crio uma nova instancia do objeto Suprimento
			Suprimento s = new Suprimento();
			
			// Atribuo o item selecionado ao Suprimento
			s = MainTabelaSuprimentos.getSelectionModel().getSelectedItem();
			
			// Chamada da função que vai atribuir os valores para a visualização do Suprimento já cadastrado
			ss.editarSuprimento(s);
		}
		
		// Crio uma scena da visualização da tela de cadastro do Suprimento e configuro ela
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setTitle("Cadastro de novo Suprimento");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		
		// Comandos depois dessa linha só serão executados quando a tela for fechada
		stage.showAndWait();
		
		// Carrego novamemte a tabela com os Suprimentos
		ListarSuprimento();
	}
	
	@FXML
	public void excluirSuprimento() throws Exception {
		SuprimentoDAO sDAO = new SuprimentoDAO();
		if(MainTabelaSuprimentos.getSelectionModel().getSelectedItem() != null) {
			Suprimento s = MainTabelaSuprimentos.getSelectionModel().getSelectedItem();
			sDAO.remover(s);
			ListarSuprimento();
		}
		
	}
}