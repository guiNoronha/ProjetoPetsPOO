package classes;//test

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Funcionario extends Pessoa {
	
	private Integer func_id;
	private Float func_salario;

	public Integer getFuncId() {
		return func_id;
	}	
	public void setFuncId(Integer func_id) {
		this.func_id= func_id;
	}

	public Float getFuncSalario() {
		return func_salario;
	}	
	public void setFuncSalario(Float func_salario) {
		this.func_salario = func_salario;
	}
}