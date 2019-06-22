package classes;

import java.sql.Date;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Animal {
	
	private Integer ani_id;
    private final SimpleStringProperty ani_nome = new SimpleStringProperty();
	private Date ani_entrada;
	private Date ani_saida;
	private final SimpleStringProperty ani_raca = new SimpleStringProperty();
  	private final SimpleStringProperty ani_tipo = new SimpleStringProperty();

	public Integer getAniId() {
		return ani_id;
	}
	public void setAniId(Integer ani_id) {
		this.ani_id = ani_id;
	}
	public ObservableValue<String> getAniNome() {
		return ani_nome;
	}
	public void setAniNome(String ani_nome) {
		this.ani_nome.set(ani_nome);
	}
	public Date getAniEntrada() {
		return ani_entrada;
	}
	public void setAniEntrada(Date ani_entrada) {
		this.ani_entrada = ani_entrada;
	}
	public Date getAniSaida() {
		return ani_saida;
	}
	public void setAniSaida(Date ani_saida) {
		this.ani_saida = ani_saida;
	}
	public ObservableValue<String> getAniRaca() {
		return ani_raca;
	}
	public void setAniRaca(String ani_raca) {
		this.ani_raca.set(ani_raca);
	}
	public ObservableValue<String> getAniTipo() {
		return ani_tipo;
	}
	
	public void setAniTipo(String ani_tipo) {
		this.ani_tipo.set(ani_tipo);
	}
	
	@Override
	public String toString() {
		return this.getAniNome().getValue();
		
	}
}