package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Candidato {
	
	private Integer cand_id;
	private boolean cand_valido;


	public Integer getCandId() {
		return cand_id.get();
	}
	
	public void setCandId(Integer cand_id) {
		this.cand_id.set(cand_id);
	}


	public Integer getCandValido() {
		return cand_valido.get();
	}
	
	public void setCandValido(boolean cand_valido) {
		this.cand_valido.set(cand_valido);
	}


