package classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Voluntario extends Pessoa {
	
	public Integer voluntario_id;
	private String voluntario_atuacao;
	private String voluntario_area;
	private Pessoa pessoa;

	public Integer getVoluntarioId() {
		return voluntario_id;
	}
	
	public void setVoluntarioId(Integer voluntario_id) {
		this.voluntario_id = voluntario_id;
	}


	public String getVoluntarioAtuacao() {
		return voluntario_atuacao;
	}
	
	public void setVoluntarioAtuacao(String voluntario_atuacao) {
		this.voluntario_atuacao = voluntario_atuacao;
	}

	public String getVoluntarioArea() {
		return voluntario_area;
	}
	
	public void setVoluntarioArea(String voluntario_area) {
		this.voluntario_area = voluntario_area;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}
	
	public void setPessoa(Pessoa pes) {
		this.pessoa = pes;
	}
}
	
	