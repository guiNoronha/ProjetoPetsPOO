package classes;

public class Candidato extends Pessoa{
	
	private Integer cand_id;
	private boolean cand_valido;
	
	public Integer getCandId() {
		return cand_id;
	}
	
	public void setCandId(Integer cand_id) {
		this.cand_id = cand_id;
	}


	public boolean getCandValido() {
		return cand_valido;
	}
	
	public void setCandValido(boolean cand_valido) {
		this.cand_valido = cand_valido;
	}
}