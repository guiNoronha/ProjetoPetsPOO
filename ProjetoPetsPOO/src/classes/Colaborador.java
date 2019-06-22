package classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Colaborador extends Pessoa{
	
	private Integer col_id;
	private final SimpleStringProperty col_funcao = new SimpleStringProperty();
	private final SimpleStringProperty col_nascimento = new SimpleStringProperty();
	
	public Integer getColId() {
		return col_id;
	}
	
	public void setColId(Integer col_id) {
		this.col_id = col_id;
	}


	public ObservableValue<String> getColFuncao() {
		return col_funcao;
	}
	
	public void setColFuncao(String col_funcao) {
		this.col_funcao.set(col_funcao);
	}
	
	public ObservableValue<String> getColNascimento() {
		return col_nascimento;
	}
	
	public void setColNascimento(String col_nascimento) {
		this.col_nascimento.set(col_nascimento);
	}
}