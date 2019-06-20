package classes;

import java.sql.Date;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Suprimento {
	
	private Integer sup_id;
    private final SimpleStringProperty sup_tipo = new SimpleStringProperty();
	private final SimpleStringProperty sup_origem = new SimpleStringProperty();
  	private final SimpleStringProperty sup_descricao = new SimpleStringProperty();

	public Integer getSupId() {
		return sup_id;
	}
	public void setSupId(Integer sup_id) {
		this.sup_id = sup_id;
	}
	public ObservableValue<String> getSupTipo() {
		return sup_tipo;
	}
	public void setSupTipo(String sup_tipo) {
		this.sup_tipo.set(sup_tipo);
	}
	public ObservableValue<String> getSupOrigem() {
		return sup_origem;
	}
	public void setSupOrigem(String sup_origem) {
		this.sup_origem.set(sup_origem);
	}
	public ObservableValue<String> getSupDescricao() {
		return sup_descricao;
	}
	public void setSupDescricao(String sup_descricao) {
		this.sup_descricao.set(sup_descricao);
	}
}