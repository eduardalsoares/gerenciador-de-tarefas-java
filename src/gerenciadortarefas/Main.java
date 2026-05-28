package gerenciadortarefas;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) { 
		Scanner entrada = new Scanner(System.in); // criando objeto Scanner para ler a entrada do usuário
		//incluindo arrayList<tarefa>
		ArrayList<Tarefa> listaDeTarefas = new ArrayList<>(); // criando um ArrayList para armazenar as tarefas
		int opcao = 0; // variável p/ armazenar a opção escolhida pelo usuário
		
		while(opcao != 7) { // Enquanto a caixinha opcao for diferente (!=) de 7, repita tudo o que estiver aqui dentro"
			
		// Aqui é o menu do gerenciador de tarefas, onde o usuário escolhe o que deseja fazer
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
			System.out.println("Digite sua opção: "); // pede para o usuário digitar a opção desejada
			opcao = entrada.nextInt(); // lê a opção escolhida pelo usuário
			entrada.nextLine(); // limpa o buffer do scanner ou seja Limpa a memória temporária do teclado.
			
			if (opcao == 1 ) { // se a opção escolhida for 1, o programa entra aqui para cadastrar uma nova tarefa
				System.out.print("Cadastrar Tarefa "); // pede para o usuário digitar o título da tarefa
				String titulo = entrada.nextLine(); // lê o título da tarefa digitado pelo usuário
				System.out.print("Descrição: "); // pede para o usuário digitar a descrição da tarefa
				String descricao = entrada.nextLine(); // lê a descrição da tarefa digitada pelo usuário
				System.out.print("Data Limite - Digite apenas números: "); // pede para o usuário digitar a data
				int data = entrada.nextInt(); // lê a data digitada pelo usuário
				entrada.nextLine(); // limpa o buffer do scanner
				
				// Status "Pendente"
				Tarefa novaTarefa = new Tarefa(titulo, descricao, data, "Pendente"); // cria um objeto Tarefa com os dados digitados pelo usuário e o status "Pendente"
				listaDeTarefas.add(novaTarefa); // adiciona a nova tarefa criada à lista de tarefas
				System.out.println("Tarefa adicionada"); // informa ao usuário que a tarefa foi adicionada com sucesso
						
			} else if (opcao == 2) { // se a opção escolhida for 2, o programa entra aqui para exibir as tarefas cadastradas
				System.out.println("\n--- TAREFAS ---"); // imprime um título para a seção de tarefas
				if (listaDeTarefas.isEmpty()) { // pergunta se a lista está vazia
					System.out.println("Nenhuma tarefa cadastrada"); // se a lista estiver vazia, informa ao usuário que não há tarefas cadastradas
				} else { // se a lista não estiver vazia, o programa entra aqui para exibir as tarefas
					for (int i = 0; i <listaDeTarefas.size(); i ++) { // percorre a lista de tarefas usando um loop for, criamos a variavel i valendo de 0 até o tamanho da lista de tarefas. 
						System.out.println ((i + 1) + ". " + listaDeTarefas.get(i)); // imprime o número da tarefa (i + 1) e a tarefa em si usando o método get(i) para acessar cada tarefa na lista. O método toString() da classe Tarefa é chamado automaticamente para exibir as informações da tarefa de forma legível.
						
					}
				}
				System.out.println(); // imprime uma linha em branco para separar as tarefas exibidas do próximo menu
			}else if (opcao == 3) { // se a opção escolhida for 3, o programa entra aqui para alterar o status de uma tarefa
				if (listaDeTarefas.isEmpty()) { // pergunta se a lista de tarefas está vazia
					System.out.println("Nenhuma tarefa para concluir \n"); // se a lista estiver vazia, informa ao usuário que não há tarefas para concluir
					
				} else { // se a lista não estiver vazia, o programa entra aqui para permitir que o usuário escolha uma tarefa para concluir
					System.out.println("Digite quantas tarefas deseja concluir");// pede para o usuário digitar o número da tarefa que deseja concluir
					int index = entrada.nextInt() - 1; // lê o número da tarefa digitado pelo usuário e subtrai 1 para obter o índice correto na lista (já que a contagem começa em 0)
					entrada.nextLine(); // limpa o buffer do scanner	
					if (index >= 0 && index <listaDeTarefas.size()) { // && significa "E" verifica se o índice digitado pelo usuário é válido (maior ou igual a 0 E menor que o tamanho da lista de tarefas)
						listaDeTarefas.get(index).setStatus("Concluída"); // se o índice for válido, o programa altera o status da tarefa correspondente para "Concluída" usando o método setStatus da classe Tarefa
						System.out.println("Tarefa concluída!\n");// informa ao usuário que a tarefa foi concluída com sucesso				
					} else { // se o índice digitado pelo usuário for inválido, o programa entra aqui para informar ao usuário que o número escolhido é inválido
							System.out.println("Número escolhido invalido!\n");
							
					}
				}
			} else if(opcao == 4) { // editar tarefas
				if (listaDeTarefas.isEmpty()) { // pergunta se a lista de tarefas está vazia
					System.out.println("Não há tarefas para editar"); // se a lista estiver vazia, informa ao usuário que não há tarefas para editar
				} else { // se a lista não estiver vazia, o programa entra aqui para permitir que o usuário escolha uma tarefa para editar
					System.out.println("Digite o número da tarefa que desejar editar: ");
					int index = entrada.nextInt() -1; // lê o número da tarefa digitado pelo usuário e subtrai 1 para obter o índice correto na lista (já que a contagem começa em 0)
					entrada.nextLine();// limpa o buffer do scanner
					
					if (index >= 0 && index < listaDeTarefas.size()) { // verifica se o índice digitado pelo usuário é válido (maior ou igual a 0 E menor que o tamanho da lista de tarefas)
						Tarefa n = listaDeTarefas.get(index); // criamos uma variável n do tipo Tarefa para armazenar a tarefa selecionada pelo usuário usando o método get(index) para acessar a tarefa na lista. 
						System.out.print("Novo Título: "); // pede para o usuário digitar o novo título da tarefa
						n.setTitulo(entrada.nextLine());// lê o novo título digitado pelo usuário e atualiza o título da tarefa usando o método setTitulo da classe Tarefa
						
						System.out.print("Nova descrição: "); // pede para o usuário digitar a nova descrição da tarefa
						n.setDescricao(entrada.nextLine()); // lê a nova descrição digitada pelo usuário e atualiza a descrição da tarefa usando o método setDescricao da classe Tarefa
						
						System.out.print("Nova Data: ");// pede para o usuário digitar a nova data da tarefa
						n.setDataLimite(entrada.nextInt());//atualiza a data limite da tarefa usando o método setDataLimite da classe Tarefa
						
						entrada.nextLine(); // limpa o buffer do scanner
						System.out.println("Tarefa Editada com sucesso!\n"); // informa ao usuário que a tarefa foi editada com sucesso
						
					} else {
						System.out.println("Número escolhido invalido");
					}
					
				}
			}
						
		} 
		entrada.close(); 

	}

}

