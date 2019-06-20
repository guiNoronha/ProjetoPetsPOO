package classes;

public class Colaborador extends Pessoa{
	
	private Integer col_id;
	private boolean col_funcao;
	
	public Integer getColId() {
		return col_id;
	}
	
	public void setColId(Integer col_id) {
		this.col_id = col_id;
	}


	public boolean getColFuncao() {
		return col_funcao;
	}
	
	public void setColFuncao(boolean col_funcao) {
		this.col_funcao = col_funcao;
	}
}