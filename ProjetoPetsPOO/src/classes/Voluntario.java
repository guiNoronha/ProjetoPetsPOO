package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Voluntario {
	
	private Integer voluntario_id;
	private String voluntario_atuacao;
	private String voluntario_area;


	public Integer getVoluntarioId() {
		return voluntario_id.get();
	}
	
	public void setVoluntarioId(Integer voluntario_id) {
		this.voluntario_id.set(voluntario_id);
	}


	public Integer getAtuacao() {
		return voluntario_atuacao.get();
	}
	
	public void setAtuacao(String voluntario_atuacao) {
		this.voluntario_atuacao.set(voluntario_atuacao);
	}

	public Integer getArea() {
		return voluntario_area.get();
	}
	
	public void setArea(String voluntario_area) {
		this.voluntario_area.set(voluntario_area);
	}
