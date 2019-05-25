package classes;

import java.sql.Date;

public class Animal {
	
	private Integer ani_id;
	private String ani_nome;
	private Date ani_entrada;
	private Date ani_saida;
	private String ani_raca;
  	private String ani_tipo;


	public Integer getAniId() {
		return ani_id;
	}
	
	public void setAniId(Integer verId) {
		this.ani_id = verId;
	}
	
	public String getAniNome() {
		return ani_nome;
	}
	public void setAniNome(String ani_nome) {
		this.ani_nome = ani_nome;
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
	public String getAniRaca() {
		return ani_raca;
	}
	public void setAniRaca(String ani_raca) {
		this.ani_raca = ani_raca;
	}
	public String getAniTipo() {
		return ani_tipo;
	}
	
	public void setAniTipo(String ani_tipo) {
		this.ani_tipo = ani_tipo;
	}
}