package classes;

public class Context {
 
    private static Context uniqueInstance;
    private Pessoa usuario;
    
    private Context() {
    }
 
    public static synchronized Context getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Context();
 
        return uniqueInstance;
    }
    
    public void setUsuarioAtual(Pessoa pes) {
    	this.usuario = pes;
    }
    
    public Pessoa getUsuarioAtual() {
    	return usuario;
    }
}