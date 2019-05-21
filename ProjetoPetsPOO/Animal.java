package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Animal {
	
	private Integer ani_id;
	private String ani_nome;
	private Date ani_entrada;
	private Date ani_saida;
	private String ani_raca;
  	private String ani_tipo;


	public Integer getPesId() {
		return ani_id.get();
	}
	
	public void setAniId(Integer verId) {
		this.ani_id.set(verId);
	}
	
	public ObservableValue<String> getAniNome() {
		return ani_nome;
	}
	public void setAniNome(String ani_nome) {
		this.ani_nome.set(ani_nome);
	}
	public ObservableValue<Date> getAniEntrada() {
		return ani_entrada;
	}
	public void setAniEntrada(Date ani_entrada) {
		this.ani_entrada.set(pesNomeCurto);
	}
	public String getAniSaida() {
		return ani_saida.get();
	}
	public void setAniSaida(Date ani_saida) {
		this.ani_saida.set(ani_saida);
	}
	public String getAniRaca() {
		return ani_raca.get();
	}
	public void setAniRaca(String ani_raca) {
		this.ani_raca.set(ani_raca);
	}
	public Integer getAniTipo() {
		return ani_tipo.get();
	}
	
	public void setAniTipo(String ani_tipo) {
		this.ani_tipo.set(ani_tipo);
	}
	
	@Override
	public String toString() {
		return this.getAniNome().getValue();
		
	}
}