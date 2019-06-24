package classes;

import javafx.beans.property.SimpleStringProperty;

public class Evento {
	private Integer ev_id;
	private final SimpleStringProperty ev_nome = new SimpleStringProperty();
	private final SimpleStringProperty ev_data = new SimpleStringProperty();
	private String ev_descricao;
	private final SimpleStringProperty ev_local = new SimpleStringProperty();
	
	private Colaborador pes_id_resp;
	
	public Integer getEventoId() {
		return ev_id;
	}
	public void setEventoId(Integer ev_id) {
		this.ev_id = ev_id;
	}
	public SimpleStringProperty getEventoNome() {
		return ev_nome;
	}
	public void setEventoNome(String ev_nome) {
		this.ev_nome.set(ev_nome);
	}
	public SimpleStringProperty getEventoData() {
		return ev_data;
	}
	public void setEventoData(String ev_data) {
		this.ev_data.set(ev_data);
	}
	public String getEventoDescricao() {
		return ev_descricao;
	}
	public void setEventoDescricao(String ev_descricao) {
		this.ev_descricao = ev_descricao;
	}
	public SimpleStringProperty getEventoLocal() {
		return ev_local;
	}
	public void setEventoLocal(String ev_local) {
		this.ev_local.set(ev_local);
	}
	public Colaborador getPesIdResp() {
		return pes_id_resp;
	}
	public void setPesIdResp(Colaborador pes_id_resp) {
		this.pes_id_resp = pes_id_resp;
	}
	
}
