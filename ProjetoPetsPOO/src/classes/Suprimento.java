package classes;

import java.sql.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Suprimento {
	
	private Integer sup_id;
	private String sup_nome;
	private Date sup_validade;
	private Integer sup_quantidade;

	public Integer getSuprimentoId() {
		return sup_id;
//		sup_id
	}
	public void setSuprimentoId(Integer sup_id) {
		this.sup_id = sup_id;
	}


	public String getSuprimentoNome() {
		return sup_nome;
	}
	public void setSuprimentoNome(String sup_nome) {
		this.sup_nome = sup_nome;
	}

	public Date getSuprimentoValidade() {
		return sup_validade;
	}
	public void setSuprimentoValidade(Date sup_validade) {
		this.sup_validade = sup_validade;
	}


	public Integer getSuprimentoQuantidade() {
		return sup_quantidade;
	}
	public void setSuprimentoQuantidade(Integer sup_quantidade) {
		this.sup_quantidade = sup_quantidade;
	}

}