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

public class ListarAdocaoController implements Initializable{
	@FXML
    public Button MainBtnCadastrarAdocoes;
	@FXML
    public Button MainBtnExcluirAdocoes;
	
	@FXML
    private TableView<Adocao> MainTabelaAdocoes = new TableView<Adocao>();
    @FXML
    private TableColumn<Adocao, String> MainColunaAdocoesAnimal;
	@FXML
    private TableColumn<Adocao, String> MainColunaAdocoesCandidato;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Context c = Context.getInstance();
    	Pessoa usuario = c.getUsuarioAtual();
    	if(usuario.getPesTipo() != 2) {
    		MainBtnCadastrarAdocoes.setDisable(true);
    		MainBtnExcluirAdocoes.setDisable(true);
    	}
    	
    	MainColunaAdocoesAnimal.setCellValueFactory(new Callback<CellDataFeatures<Adocao, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Adocao, String> data){  
			return data.getValue().getAni().getAniNome();
		}  
		});
		
		
    	MainColunaAdocoesCandidato.setCellValueFactory(new Callback<CellDataFeatures<Adocao, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Adocao, String> data){  
			return data.getValue().getCand().getPesNome();
		}  
		});
    	
		try {
			ListarAdocao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
	public void ListarAdocao() throws Exception {
		MainTabelaAdocoes.setItems(listaDeAdocoes());
	}
	
	private ObservableList<Adocao> listaDeAdocoes() throws Exception {
    	AdocaoDAO aDAO = new AdocaoDAO();
        return FXCollections.observableArrayList(aDAO.getLista());
    }
	
	@FXML
	public void manterCadastroAdocao() throws Exception {
		// Carrego o FXML do Adocao
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../telas_poo/CadastroAdocao.fxml"));
		Pane root = loader.load();
		
		// Atribuo o load do FXML a uma nova instancia do controller que faz referncia a ele
		ManterAdocaoController ac = (ManterAdocaoController) loader.getController();
		ac.preencherComboCoxAnimal();
		ac.preencherComboCoxCandidato();
		
		// Verifico se é uma edição de Adocao ou cadastro
		if(MainTabelaAdocoes.getSelectionModel().getSelectedItem() != null) {
			
			// Crio uma nova instancia do objeto Adocao
			Adocao a = new Adocao();
			
			// Atribuo o item selecionado ao Adocao
			a = MainTabelaAdocoes.getSelectionModel().getSelectedItem();
			
			// Chamada da função que vai atribuir os valores para a visualização do Adocao já cadastrado
			ac.editarAdocao(a);
		}
		
		// Crio uma scena da visualização da tela de cadastro do Adocao e configuro ela
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setTitle("Cadastro de novo Adocao");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		
		// Comandos depois dessa linha só serão executados quando a tela for fechada
		stage.showAndWait();
		
		// Carrego novamemte a tabela com os Adocaos
		ListarAdocao();
	}
	
	@FXML
	public void excluirAdocao() throws Exception {
		AdocaoDAO aDAO = new AdocaoDAO();
		if(MainTabelaAdocoes.getSelectionModel().getSelectedItem() != null) {
			Adocao a = MainTabelaAdocoes.getSelectionModel().getSelectedItem();
			aDAO.remover(a);
			ListarAdocao();
		}
		
	}
}