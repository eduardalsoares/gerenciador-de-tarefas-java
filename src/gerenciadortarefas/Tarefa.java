package gerenciadortarefas;

public class Tarefa {
	
	// Atributos: características que uma tarefa tem
	private String titulo;
	private String descricao;
	private int dataLimite;
	private String status;
	
	public Tarefa(String titulo, String descricao, int dataLimite, String status) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataLimite = dataLimite;
		this.status = status;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public int getDataLimite () {
		return dataLimite;
	}
	
	public String getStatus () {
		return status;
	}
	// alteração ao seu sistem, set=definir configurar "uma porta de entrada ao sistema > atualizar ou corrigir uma informação que já existe.
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setDataLimite(int dataLimite) {
		this.dataLimite = dataLimite;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	// sobrescrevendo corretamente um metodo da classe pai
	@Override 
	public String toString() {
		return "titulo: "+ titulo + "| Descrição: "+ descricao + "| Data: " + dataLimite + "| Status: ["+ status +"]";
		
	}
}