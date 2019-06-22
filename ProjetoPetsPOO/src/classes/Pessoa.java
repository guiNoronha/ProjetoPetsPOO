package classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Pessoa {
	
	private Integer pes_id;
	private final SimpleStringProperty pes_nome = new SimpleStringProperty();
	private String pes_cep;
	private final SimpleStringProperty pes_telefone = new SimpleStringProperty();
	private final SimpleStringProperty pes_cpf = new SimpleStringProperty();
	private String pes_email;
	private String pes_numero;
	private String pes_senha;

	public Integer getPesId() {
		return pes_id;
	}	
	public void setPesId(Integer verId) {
		this.pes_id = verId;
	}
	
	public ObservableValue<String> getPesNome() {
		return pes_nome;
	}
	public void setPesNome(String pes_nome) {
		this.pes_nome.set(pes_nome);
	}
	
	public String getPesCep() {
		return pes_cep;
	}
	public void setPesCep(String pes_cep) {
		this.pes_cep = pes_cep;
	}
	
	public ObservableValue<String> getPesTelefone() {
		return pes_telefone;
	}
	public void setPesTelefone(String pes_telefone) {
		this.pes_telefone.set(pes_telefone);
	}
	
	public ObservableValue<String> getPesCpf() {
		return pes_cpf;
	}
	public void setPesCpf(String pes_cpf) {
		this.pes_cpf.set(pes_cpf);
	}
	
	public String getPesEmail() {
		return pes_email;
	}	
	public void setPesEmail(String pes_email) {
		this.pes_email = pes_email;
	}
	
	public String getPesNumero() {
		return pes_numero;
	}	
	public void setPesNumero(String pes_numero) {
		this.pes_numero = pes_numero;
	}
	public String getPesSenha() {
		return pes_senha;
	}
	public void setPesSenha(String pes_senha) {
		this.pes_senha = pes_senha;
	}
}