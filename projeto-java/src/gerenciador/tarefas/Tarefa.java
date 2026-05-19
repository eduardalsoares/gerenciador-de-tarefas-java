package gerenciador.tarefas;

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
	
	
}
