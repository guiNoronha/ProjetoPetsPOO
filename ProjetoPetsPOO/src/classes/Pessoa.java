package classes;

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
		return pes_id;
	}	
	public void setPesId(Integer verId) {
		this.pes_id = verId;
	}
	
	public String getPesNome() {
		return pes_nome;
	}
	public void setPesNome(String pes_nome) {
		this.pes_nome = pes_nome;
	}
	
	public String getPesEndereco() {
		return pes_endereco;
	}
	public void setPesEndereco(String pes_endereco) {
		this.pes_endereco = pes_endereco;
	}
	
	public String getPesTelefone() {
		return pes_telefone;
	}
	public void setPesTelefone(String pes_telefone) {
		this.pes_telefone = pes_telefone;
	}
	
	public String getPesCpf() {
		return pes_cpf;
	}
	public void setPesCpf(String pes_cpf) {
		this.pes_cpf = pes_cpf;
	}
	
	public String getPesEmail() {
		return pes_email;
	}	
	public void setPesEmail(String pes_email) {
		this.pes_email = pes_email;
	}
	
	@Override
	public String toString() {
		return this.getPesNome();
		
	}
}