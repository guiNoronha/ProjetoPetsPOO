package classes;

import java.sql.Date;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Financeiro {
	
	private Integer fin_id;
	private Date fin_data;
    private final SimpleStringProperty fin_categoria = new SimpleStringProperty();
  	private final SimpleStringProperty fin_descricao = new SimpleStringProperty();
  	private Float fin_valor;

	public Integer getFinId() {
		return fin_id;
	}
	public void setFinId(Integer fin_id) {
		this.fin_id = fin_id;
	}
	public Date getFinData() {
		return fin_data;
	}
	public void setFinData(Date fin_data) {
		this.fin_data = fin_data;
	}
	public ObservableValue<String> getFinCategoria() {
		return fin_categoria;
	}
	public void setFinCategoria(String fin_categoria) {
		this.fin_categoria.set(fin_categoria);
	}
	public ObservableValue<String> getFinDescricao() {
		return fin_descricao;
	}
	public void setFinDescricao(String fin_descricao) {
		this.fin_descricao.set(fin_descricao);
	}
	public Float getFinValor() {
		return fin_valor;
	}
	public void setFinValor(Float fin_valor) {
		this.fin_valor = fin_valor;
	}
}