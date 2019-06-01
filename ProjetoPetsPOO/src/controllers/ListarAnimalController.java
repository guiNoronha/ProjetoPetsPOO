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

public class ListarAnimalController implements Initializable{
	
	@FXML
	public TextField CadAnimalTxtFieldNome;
	
	@FXML
    private TableView<Animal> MainTabelaAnimais = new TableView<Animal>();
    @FXML
    private TableColumn<Animal, String> MainColunaAnimaisNome;
	@FXML
    private TableColumn<Animal, String> MainColunaAnimaisEspecie;
	@FXML
    private TableColumn<Animal, String> MainColunaAnimaisRaca;
    @FXML
    private TableColumn<Animal, String> MainColunaAnimaisStatus;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
		MainColunaAnimaisNome.setCellValueFactory(new Callback<CellDataFeatures<Animal, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Animal, String> data){  
			return data.getValue().getAniNome();
		}  
		});
		
		
		MainColunaAnimaisEspecie.setCellValueFactory(new Callback<CellDataFeatures<Animal, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Animal, String> data){  
			return data.getValue().getAniTipo();
		}  
		});
		
		MainColunaAnimaisRaca.setCellValueFactory(new Callback<CellDataFeatures<Animal, String>, 
                ObservableValue<String>>() {
    	 
		public ObservableValue<String> call(CellDataFeatures<Animal, String> data){  
			return data.getValue().getAniRaca();
		}  
		});
    	
		try {
			ListarAnimal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
	public void ListarAnimal() throws Exception {
		MainTabelaAnimais.setItems(listaDeAnimais());
	}
	
	private ObservableList<Animal> listaDeAnimais() throws Exception {
    	AnimalDAO aDAO = new AnimalDAO();
        return FXCollections.observableArrayList(aDAO.getLista());
    }
	
	@FXML
	public void manterCadastroAnimal() throws Exception {
		// Carrego o FXML do Animal
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../telas_poo/CadastroAnimal.fxml"));
		Pane root = loader.load();
		
		// Atribuo o load do FXML a uma nova instancia do controller que faz referncia a ele
		ManterAnimalController ac = (ManterAnimalController) loader.getController();
		
		// Verifico se é uma edição de Animal ou cadastro
		if(MainTabelaAnimais.getSelectionModel().getSelectedItem() != null) {
			
			// Crio uma nova instancia do objeto Animal
			Animal a = new Animal();
			
			// Atribuo o item selecionado ao Animal
			a = MainTabelaAnimais.getSelectionModel().getSelectedItem();
			
			// Chamada da função que vai atribuir os valores para a visualização do Animal já cadastrado
			ac.editarAnimal(a);
		}
		
		// Crio uma scena da visualização da tela de cadastro do Animal e configuro ela
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setTitle("Cadastro de novo Animal");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		
		// Comandos depois dessa linha só serão executados quando a tela for fechada
		stage.showAndWait();
		
		// Carrego novamemte a tabela com os Animals
		ListarAnimal();
	}
	
	@FXML
	public void excluirAnimal() throws Exception {
		AnimalDAO aDAO = new AnimalDAO();
		if(MainTabelaAnimais.getSelectionModel().getSelectedItem() != null) {
			Animal a = MainTabelaAnimais.getSelectionModel().getSelectedItem();
			aDAO.remover(a);
			ListarAnimal();
		}
		
	}
}