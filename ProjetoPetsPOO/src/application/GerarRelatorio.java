package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import classes.*;

public class GerarRelatorio {
	
	public void gerarRelatorioAnimais(ArrayList<Adocao> ad) throws IOException {
		
	    FileWriter arq = new FileWriter("./src/relatorios/relatorioAnimais.txt");
	    PrintWriter gravarArq = new PrintWriter(arq);
	 
	    gravarArq.printf("+--Relatorio Animais Adotados--+%n");
	    gravarArq.printf("|    Animal    |    Candidato    |%n");
	    
	    for (int i=0; i<ad.size(); i++) {
	    	gravarArq.printf("|    "+ad.get(i).getAni().getAniNome().getValue()+"    |    "+ad.get(i).getCand().getPesNome().getValue()+"    |%n");	
	    }
	 
	    arq.close();
	}
	
	public void gerarRelatorioFluxoCaixa(ArrayList<Financeiro> fi) throws IOException {
		
	    FileWriter arq = new FileWriter("./src/relatorios/relatorioFluxoCaixa.txt");
	    PrintWriter gravarArq = new PrintWriter(arq);
	 
	    gravarArq.printf("+--Relatorio Fluxo Caixa--+%n");
	    gravarArq.printf("|    Data    |    Categoria    |    Descricao    |    Valor    |%n");
	    
	    for (int i=0; i<fi.size(); i++) {
	    	gravarArq.printf("|    "+fi.get(i).getFinData()+"    |    "+fi.get(i).getFinCategoria().getValue()+"    |    "+fi.get(i).getFinDescricao().getValue()+"    |    "+fi.get(i).getFinValor()+"    |%n");	
	    }
	 
	    arq.close();
	}

	public void gerarRelatorioItensDoados(ArrayList<Suprimento> s) throws IOException {
	
	    FileWriter arq = new FileWriter("./src/relatorios/relatorioItensDoados.txt");
	    PrintWriter gravarArq = new PrintWriter(arq);
	 
	    gravarArq.printf("+--Relatorio Fluxo Caixa--+%n");
	    gravarArq.printf("|    Tipo    |    Origem    |    Descricao    |%n");
	    
	    for (int i=0; i<s.size(); i++) {
	    	gravarArq.printf("|    "+s.get(i).getSupTipo().getValue()+"    |    "+s.get(i).getSupOrigem().getValue()+"    |    "+s.get(i).getSupDescricao().getValue()+"    |%n");	
	    }
	 
	    arq.close();
	}
}