package gerenciadortarefas;
import java.time.LocalDate;

public class Tarefa {
	
	// Atributos: características que uma tarefa tem
	private String titulo; // título da tarefa
	private String descricao; // descrição da tarefa
	private LocalDate dataLimite; 
	private String status;// status da tarefa, que pode ser "Pendente", "Concluída" ou "Em andamento"

	public Tarefa(String titulo, String descricao, LocalDate dataLimite, String status) { // construtor da classe Tarefa, que é um método especial usado para criar objetos da classe. Ele recebe quatro parâmetros: titulo, descricao, dataLimite e status.
		this.titulo = titulo; // o "this" é usado para se referir ao atributo da classe, diferenciando-o do parâmetro do construtor. Ele atribui o valor do parâmetro titulo ao atributo titulo da classe.
		this.descricao = descricao; // atribui o valor do parâmetro descricao ao atributo descricao da classe.
		this.dataLimite = dataLimite; // atribui o valor do parâmetro dataLimite ao atributo dataLimite da classe.
		this.status = status; // atribui o valor do parâmetro status ao atributo status da classe.
	}
	
	public String getTitulo() {// método get para o título da tarefa, que é usado para acessar o valor do atributo titulo de um objeto da classe Tarefa.
		return titulo; // retorna o valor do atributo titulo da tarefa.
	}
	
	public String getDescricao() {// método get para a descrição da tarefa, que é usado para acessar o valor do atributo descricao de um objeto da classe Tarefa.
		return descricao;// retorna o valor do atributo descricao da tarefa.
	}
	
	public LocalDate getDataLimite () {// método get para a data limite da tarefa, que é usado para acessar o valor do atributo dataLimite de um objeto da classe Tarefa.
		return dataLimite;// retorna o valor do atributo dataLimite da tarefa.
	}
	
	public String getStatus () {// método get para o status da tarefa, que é usado para acessar o valor do atributo status de um objeto da classe Tarefa.
		return status;// retorna o valor do atributo status da tarefa.
	}
	// alteração ao seu sistem, set=definir configurar "uma porta de entrada ao sistema > atualizar ou corrigir uma informação que já existe.
	public void setTitulo(String titulo) { // método set para o título da tarefa, que é usado para atualizar o valor do atributo titulo de um objeto da classe Tarefa.
		this.titulo = titulo;// atribui o valor do parâmetro titulo ao atributo titulo da classe, permitindo que o título da tarefa seja atualizado.
	}
	public void setDescricao(String descricao) {// método set para a descrição da tarefa, que é usado para atualizar o valor do atributo descricao de um objeto da classe Tarefa.
		this.descricao = descricao;// atribui o valor do parâmetro descricao ao atributo descricao da classe, permitindo que a descrição da tarefa seja atualizada.
	}
	public void setDataLimite(LocalDate dataLimite) {// método set para a data limite da tarefa, que é usado para atualizar o valor do atributo dataLimite de um objeto da classe Tarefa.
		this.dataLimite = dataLimite;// atribui o valor do parâmetro dataLimite ao atributo dataLimite da classe, permitindo que a data limite da tarefa seja atualizada.
	}
	public void setStatus(String status) {// método set para o status da tarefa, que é usado para atualizar o valor do atributo status de um objeto da classe Tarefa.
		this.status = status;// atribui o valor do parâmetro status ao atributo status da classe, permitindo que o status da tarefa seja atualizado.
	}

	// sobrescrevendo corretamente um metodo da classe pai
	@Override 
	public String toString() {// método toString é sobrescrito para fornecer uma representação em formato de string da tarefa, facilitando a exibição das informações da tarefa de forma legível.
		return "Título: "+ titulo + 
		"| Descrição: "+ descricao + 
		"| Data Limite: " + dataLimite + 
		"| Status: ["+ status +"]"; // retorna uma string formatada com as informações da tarefa, incluindo o título, descrição, data limite e status.
		
	}
}