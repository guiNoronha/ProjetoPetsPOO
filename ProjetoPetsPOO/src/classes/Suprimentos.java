package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Suprimentos {
	
	private Integer sup_id;
	private String sup_nome;
	private Date sup_validade;
	private Integer sup_quantidade;

	public String getSupId() {
		return sup_id.get();
	}
	public void setSupId(Integer sup_id) {
		this.sup_id.set(sup_id);
	}


	public String getSupNome() {
		return sup_nome.get();
	}
	public void setSupNOme(String sup_nome) {
		this.sup_nome.set(sup_nome);
	}

	public String getValidade() {
		return sup_validade.get();
	}
	public void setValidade(Date sup_validade) {
		this.sup_validade.set(sup_validade);
	}


	public String getSupQuantidade() {
		return sup_quantidade.get();
	}
	public void setSupQuantidade(Integer sup_quantidade) {
		this.sup_quantidade.set(sup_quantidade);
	}

}