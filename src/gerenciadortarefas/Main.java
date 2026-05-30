package gerenciadortarefas;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) { 
		GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
		int opcao = 0; // variável p/ armazenar a opção escolhida pelo usuário

		Scanner entrada = new Scanner(System.in); 
			
		while(opcao != 8) { // Enquanto a caixinha opcao for diferente (!=) de 8, repita tudo o que estiver aqui dentro"
			
		// Aqui é o menu do gerenciador de tarefas, onde o usuário escolhe o que deseja fazer
			System.out.println("""
				------------ GERENCIADOR DE TAREFAS ------------
				1 - Cadastrar Tarefas
				2 - Exibir Tarefas
				3 - Alterar Status da Tarefa
				4 - Editar Tarefas
				5 - Excluir Tarefas
				6 - Filtrar Tarefas
				7 - Listar Tarefas por Data Limite
				8 - Sair do Programa
				""");
			System.out.print("Digite sua opção: "); // pede para o usuário digitar a opção desejada

			try {
				opcao = entrada.nextInt(); // lê a opção escolhida pelo usuário
				System.out.println(); // imprime uma linha em branco para separar o menu da próxima ação
				entrada.nextLine(); // limpa o buffer do scanner ou seja Limpa a memória temporária do teclado.
				
				switch (opcao) { // estrutura de controle mais adequada que o if-else para lidar com múltiplas opções
					case 1 -> gerenciador.cadastrar(entrada); 
					case 2 -> gerenciador.exibir(entrada); 
					case 3 -> gerenciador.status(entrada);
					case 4 -> gerenciador.editar(entrada);
					case 5 -> gerenciador.excluir(entrada);
					case 6 -> gerenciador.filtrar(entrada);
					case 7 -> gerenciador.listarDataLimite(entrada);
					case 8 -> System.out.println("Encerrando o programa...");
					default -> System.out.println("Opção Inválida! Digite um número entre 1 e 8");
					}		
			} catch (Exception e) { // captura qualquer exceção que possa ocorrer durante a execução do programa, como entrada inválida do usuário
				System.out.println("Entrada inválida! Por favor, digite um número entre 1 e 8.");
				entrada.nextLine(); // limpa o buffer do scanner para evitar loops infinitos em caso de entrada inválida
			}
		} 
		
		entrada.close();
		
	}

}


