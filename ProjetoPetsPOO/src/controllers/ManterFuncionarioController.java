package controllers;

import dao.*;
import classes.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManterFuncionarioController implements Initializable {
	Funcionario funcionarioAuxiliar = new Funcionario();
	
	@FXML
	public TextField CadFuncionarioTxtFieldNome;
	
	@FXML
	public TextField CadFuncionarioTxtFieldCpf;
	
	@FXML
	public TextField CadFuncionarioTxtFieldTelefone;
	
	@FXML
	public TextField CadFuncionarioTxtFieldCep;
	
	@FXML
	public TextField CadFuncionarioTxtFieldNumero;
	
	@FXML
	public TextField CadFuncionarioTxtFieldOcupacao;
	
	@FXML
	public TextField CadFuncionarioTxtFieldSalario;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void manterFuncionario() throws Exception {
		FuncionarioDAO fDAO = new FuncionarioDAO();
		funcionarioAuxiliar.setPesNome(CadFuncionarioTxtFieldNome.getText());
		funcionarioAuxiliar.setPesCpf(CadFuncionarioTxtFieldCpf.getText());
		funcionarioAuxiliar.setPesTelefone(CadFuncionarioTxtFieldTelefone.getText());
		funcionarioAuxiliar.setPesCep(CadFuncionarioTxtFieldCep.getText());
		funcionarioAuxiliar.setPesNumero(CadFuncionarioTxtFieldNumero.getText());
		funcionarioAuxiliar.setFuncOcupacao(CadFuncionarioTxtFieldOcupacao.getText());
		funcionarioAuxiliar.setFuncSalario(Float.valueOf(CadFuncionarioTxtFieldSalario.getText()));
		
		if(funcionarioAuxiliar.getPesId() != null) {	
			fDAO.alterar(funcionarioAuxiliar);
		}
		else{
			fDAO.inserir(funcionarioAuxiliar);
		}
		
		fecharTelaCadastroFuncionario();
	}
	
	@FXML
	public void fecharTelaCadastroFuncionario() throws Exception {
		Stage stage = (Stage) CadFuncionarioTxtFieldNome.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void editarFuncionario(Funcionario f) {
		// Atribuo o colaborador que foi passado por parametro a um obejto colaborador auxiliar que existe como atributo dessa classe
		funcionarioAuxiliar = f;
		
		// Atribuo o valor do nome do colaborador ao seu respectivo textField
		CadFuncionarioTxtFieldNome.textProperty().setValue(f.getPesNome().getValue());
		CadFuncionarioTxtFieldCpf.textProperty().setValue(f.getPesCpf().getValue());
		CadFuncionarioTxtFieldTelefone.textProperty().setValue(f.getPesTelefone().getValue());
		CadFuncionarioTxtFieldCep.textProperty().setValue(f.getPesCep());
		CadFuncionarioTxtFieldNumero.textProperty().setValue(f.getPesNumero());
		CadFuncionarioTxtFieldOcupacao.textProperty().setValue(f.getFuncOcupacao());
		CadFuncionarioTxtFieldSalario.textProperty().setValue(Float.toString(f.getFuncSalario()));		
		
	}
}