package gerenciador.tarefas;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Inicializando o  ");
		int opcao = entrada.nextInt();
		
			System.out.println("------------ GERENCIADOR DE TAREFAS ------------\n"
					+ "	1 - Cadastrar Tarefas"
					+ " 2 - Exibir Tarefas"
					+ " 3 - Alterar Status da Tarefa"
					+ " 4 - Editar Tarefas"
					+ " 5 - Excluir Tarefas"
					+ " 6 - Filtrar Tarefas"
					+ " 7 - Sair");
			System.out.println("Digite sua opção: ");
			
			
		} 
		
		entrada.close();
		

	}

}
