package classes;//test

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Funcionario extends Pessoa {
	
	private Integer func_id;
	private final SimpleStringProperty func_ocupacao = new SimpleStringProperty();
	private final SimpleStringProperty func_salario = new SimpleStringProperty();

	public Integer getFuncId() {
		return func_id;
	}	
	public void setFuncId(Integer func_id) {
		this.func_id= func_id;
	}

	public ObservableValue<String> getFuncSalario() {
		return func_salario;
	}	
	public void setFuncSalario(String func_salario) {
		this.func_salario.set(func_salario);
	}
	
	public ObservableValue<String> getFuncOcupacao() {
		return func_ocupacao;
	}	
	public void setFuncOcupacao(String func_ocupacao) {
		this.func_ocupacao.set(func_ocupacao);
	}
}