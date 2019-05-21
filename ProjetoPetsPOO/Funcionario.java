package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Funcionario {
	
	private Integer func_id;
	private Float func_salario;

	public Integer getFuncId() {
		return func_id.get();
	}
	
	public void setFuncId(Integer func_id) {
		this.func_id.set(func_id);
	}


	public Integer getFuncSalario() {
		return func_salario.get();
	}
	
	public void setFuncSalario(Float func_salario) {
		this.func_salario.set(func_salario);
	}