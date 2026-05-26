package gerenciadortarefas;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		//incluindo arrayList<tarefa>
		ArrayList<Tarefa> listaDeTarefas = new ArrayList<>();
		int opcao = 0;
		
		while(opcao != 7) {
			
			System.out.println("""
				------------ GERENCIADOR DE TAREFAS ------------
				1 - Cadastrar Tarefas
				2 - Exibir Tarefas
				3 - Alterar Status da Tarefa
				4 - Editar Tarefas
				5 - Excluir Tarefas
				6 - Filtrar Tarefas
				7 - Sair
				""");
			System.out.println("Digite sua opção: ");
			opcao = entrada.nextInt();
			entrada.nextLine();
			
			if (opcao == 1 ) {
				System.out.print("Cadastrar Tarefa ");
				String titulo = entrada.nextLine();
				System.out.print("Descrição: ");
				String descricao = entrada.nextLine();
				System.out.print("Data Limite - Digite apenas números: ");
				int data = entrada.nextInt();
				entrada.nextLine();
				
				// Status "Pendente"
				Tarefa novaTarefa = new Tarefa(titulo, descricao, data, "Pendente");
				listaDeTarefas.add(novaTarefa);
				System.out.println("Tarefa adicionada");
						
			} else if (opcao == 2) {
				System.out.println("\n--- TAREFAS ---");
				if (listaDeTarefas.isEmpty()) { // pergunta se a lista está vazia
					System.out.println("Nenhuma tarefa cadastrada");
				} else {
					for (int i = 0; i <listaDeTarefas.size(); i ++) {
						System.out.println ((i + 1) + ". " + listaDeTarefas.get(i));
						
					}
				}
				System.out.println();
			}else if (opcao == 3) {
				if (listaDeTarefas.isEmpty()) {
					System.out.println("Nenhuma tarefa para concluir \n");
					
				} else {
					System.out.println("Digite quantas tarefas deseja concluir");
					int index = entrada.nextInt() - 1; 
					if (index >= 0 && index <listaDeTarefas.size()) {
						listaDeTarefas.get(index).setStatus("Concluída"); // usa o setter
						System.out.println("Tarefa concluída!\n");				
					} else {
							System.out.println("Número escolhido invalido!\n");
							
					}
				}
			} else if(opcao == 4) { // editar tarefas
				if (listaDeTarefas.isEmpty()) {
					System.out.println("Não há tarefas para editar");
				} else {
					System.out.println("Digite o número da tarefa que desejar editar: ");
					int index = entrada.nextInt() -1;
					entrada.nextLine();
					
					if (index >= 0 && index < listaDeTarefas.size()) {
						Tarefa n = listaDeTarefas.get(index);
						System.out.print("Novo Título: ");
						n.setTitulo(entrada.nextLine());
						
						System.out.print("Nova descrição: ");
						n.setDescricao(entrada.nextLine());
						
						System.out.print("Nova Data: ");
						n.setDataLimite(entrada.nextInt());
						
						entrada.nextLine();
						System.out.println("Tarefa Editada com sucesso!\n");
						
					} else {
						System.out.println("Número escolhido invalido");
					}
					
				}
			}
						
		} 
		entrada.close(); 

	}

}

