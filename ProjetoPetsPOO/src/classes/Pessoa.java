package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Pessoa {
	
	private Integer pes_id;
	private String pes_nome;
	private String pes_endereco;
	private String pes_telefone;
	private String pes_cpf;
	private String pes_email;

	public Integer getPesId() {
		return pes_id.get();
	}
	
	public void setPesId(Integer verId) {
		this.pes_id.set(verId);
	}
	
	public ObservableValue<String> getPesNome() {
		return pes_nome;
	}
	public void setPesNome(String pes_nome) {
		this.pes_nome.set(pes_nome);
	}
	public ObservableValue<String> getPesEndereco() {
		return pes_endereco;
	}
	public void setPesEndereco(String pes_endereco) {
		this.pes_endereco.set(pesNomeCurto);
	}
	public String getTelefone() {
		return pes_telefone.get();
	}
	public void setTelefone(String pes_telefone) {
		this.pes_telefone.set(pes_telefone);
	}
	public String getCpf() {
		return pes_cpf.get();
	}
	public void setCpf(String pes_cpf) {
		this.pes_cpf.set(pes_cpf);
	}
	public Integer getEmail() {
		return pes_email.get();
	}
	
	public void setEmail(String pes_email) {
		this.pes_email.set(pes_email);
	}
	
	@Override
	public String toString() {
		return this.getPesNome().getValue();
		
	}
}