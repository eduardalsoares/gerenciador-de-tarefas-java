package gerenciadortarefas;

import java.time.LocalDate; // importa a classe LocalDate para lidar com datas de forma mais eficiente e segura, facilitando a manipulação.
import java.time.format.DateTimeFormatter; // converte a data digitada pelo usuário para o formato LocalDate
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorDeTarefas {

    //incluindo arrayList<tarefa>
	ArrayList<Tarefa> listaDeTarefas = new ArrayList<>(); // criando um ArrayList para armazenar as tarefas

    // define o formato esperado de data para todos os métodos que lidam com datas, garantindo consistência na entrada e facilitando a manipulação de datas em todo o programa.
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // método de verificação para realizar a ação novamente ou retornar ao menu
    private boolean verificar(Scanner entrada) {
        String resposta;

        System.out.println(); // imprime uma linha em branco
        System.out.print("Deseja realizar essa ação novamente? (s/n): ");
        resposta = entrada.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            System.out.println(); // imprime uma linha em branco para separar a ação repetida do próximo menu
            return true; // retorna aqui e nem chega no else if
        } else if (resposta.equalsIgnoreCase("n")) {
            System.out.print("Deseja voltar ao menu? (s/n): ");
            resposta = entrada.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                System.out.println(); // imprime uma linha em branco para separar a ação repetida do próximo menu
                return false; // volta ao menu
            }
        }
        return true; // se não quer voltar ao menu, continua no método
    }
    
    // tratamento de data para evitar que o usuário digite uma data inválida 
    private LocalDate lerData(Scanner entrada) {
        LocalDate data = null; // inicializa a variável data como null para entrar no loop de validação
        while (data == null) {
            System.out.print("Data Limite (dd/MM/yyyy): ");
            String dataStr = entrada.nextLine(); // lê a data digitada pelo usuário como uma string
            try {
                data = LocalDate.parse(dataStr, formato);
            } catch (Exception e) {
                System.out.println("Data inválida! Use o formato dd/MM/yyyy. Tente novamente."); // converte a string da data para um objeto LocalDate
            }
        }
        return data; // retorna a data válida lida do usuário para ser usada nos métodos de cadastro e edição de tarefas
    }

    private Tarefa buscarPorTitulo(String titulo) {
        for (Tarefa t : listaDeTarefas) { // percorre a lista de tarefas usando um loop for-each, onde cada tarefa é representada pela variável t do tipo Tarefa.
            if (t.getTitulo().equalsIgnoreCase(titulo)) {
                return t;
            }
    }
    return null;
}
    

    //incluindo os métodos para gerenciar as tarefas
    public void cadastrar (Scanner entrada) {
        do {
            System.out.print("--------- Cadastro de Tarefas ---------"); // pede para o usuário digitar o título da tarefa
            System.out.print("\nTítulo: ");
            String titulo = entrada.nextLine(); // lê o título da tarefa digitado pelo usuário

            if (buscarPorTitulo(titulo) != null) { // se encontrou  algo é pq já existe
                System.out.println("Já existe uma tarefa com esse título!");
            } else {

            System.out.print("Descrição: "); // pede para o usuário digitar a descrição da tarefa
            String descricao = entrada.nextLine(); // lê a descrição da tarefa digitada pelo usuário

            LocalDate data = lerData(entrada); // chama o método lerData para obter uma data válida digitada pelo usuário
            
            // Status "Pendente"
            Tarefa novaTarefa = new Tarefa(titulo, descricao, data, "Pendente"); // cria um objeto Tarefa com os dados digitados pelo usuário e o status "Pendente"
            listaDeTarefas.add(novaTarefa); // adiciona a nova tarefa criada à lista de tarefas

            System.out.println();

            System.out.println("''''''''''Tarefa Adicionada!''''''''''"); // informa ao usuário que a tarefa foi adicionada com sucesso

            }
        } while (verificar(entrada));
    }

    public void exibir() {
        do {
            System.out.println("--------- Tarefas Cadastradas ---------"); // imprime um título para a seção de tarefas
            if (listaDeTarefas.isEmpty()) { // pergunta se a lista está vazia
                System.out.println("Nenhuma tarefa cadastrada!"); // se a lista estiver vazia, informa ao usuário que não há tarefas cadastradas
            } else { // se a lista não estiver vazia, o programa entra aqui para exibir as tarefas
                for (int i = 0; i <listaDeTarefas.size(); i ++) { // percorre a lista de tarefas usando um loop for, criamos a variavel i valendo de 0 até o tamanho da lista de tarefas. 
                    System.out.println ((i + 1) + ". " + listaDeTarefas.get(i)); // imprime o número da tarefa (i + 1) e a tarefa em si usando o método get(i) para acessar cada tarefa na lista. O método toString() da classe Tarefa é chamado automaticamente para exibir as informações da tarefa de forma legível.  
                    }
                }
        } while (verificar(new Scanner(System.in)));
    }

    public void status(Scanner entrada) {
        do {
            System.out.println("--------- Status das Tarefas ---------");
            // se a opção escolhida for 3, o programa entra aqui para alterar o status de uma tarefa
            if (listaDeTarefas.isEmpty()) { // pergunta se a lista de tarefas está vazia
                System.out.println("Nenhuma tarefa para concluir \n"); // se a lista estiver vazia, informa ao usuário que não há tarefas para concluir                   
            } else { // se a lista não estiver vazia, o programa entra aqui para permitir que o usuário escolha uma tarefa para concluir
                System.out.print("Digite quantas tarefas deseja concluir: ");// pede para o usuário digitar o número da tarefa que deseja concluir
                int index = entrada.nextInt() - 1; // lê o número da tarefa digitado pelo usuário e subtrai 1 para obter o índice correto na lista (já que a contagem começa em 0)

                entrada.nextLine(); // limpa o buffer

                if (index >= 0 && index <listaDeTarefas.size()) { // && significa "E" verifica se o índice digitado pelo usuário é válido (maior ou igual a 0 E menor que o tamanho da lista de tarefas)
                    listaDeTarefas.get(index).setStatus("Concluída"); // se o índice for válido, o programa altera o status da tarefa correspondente para "Concluída" usando o método setStatus da classe Tarefa
                    System.out.println("''''''''''Tarefa Concluída!'''''''''");// informa ao usuário que a tarefa foi concluída com sucesso
                } else { // se o índice digitado pelo usuário for inválido, o programa entra aqui para informar ao usuário que o número escolhido é inválido
                    System.out.println("Número inválido!\n");                       
                }
            }
         } while (verificar(entrada));     
    }

    public void editar(Scanner entrada) {
        do {
            System.out.println("--------- Edição de Tarefas ---------");
            if (listaDeTarefas.isEmpty()) { // pergunta se a lista de tarefas está vazia
                System.out.println("Não há tarefas para editar"); // se a lista estiver vazia, informa ao usuário que não há tarefas para editar
            } else { // se a lista não estiver vazia, o programa entra aqui para permitir que o usuário escolha uma tarefa para editar
                System.out.println("Digite o número da tarefa que desejar editar: ");
                int index = entrada.nextInt() -1; // lê o número da tarefa digitado pelo usuário e subtrai 1 para obter o índice correto na lista (já que a contagem começa em 0)
                
                entrada.nextLine(); // lipma o buffer

                if (index >= 0 && index < listaDeTarefas.size()) { // verifica se o índice digitado pelo usuário é válido (maior ou igual a 0 E menor que o tamanho da lista de tarefas)
                    Tarefa n = listaDeTarefas.get(index); // criamos uma variável n do tipo Tarefa para armazenar a tarefa selecionada pelo usuário usando o método get(index) para acessar a tarefa na lista.
                    System.out.print("Novo Título: "); // pede para o usuário digitar o novo título da tarefa
                    n.setTitulo(entrada.nextLine());// lê o novo título digitado pelo usuário e atualiza o título da tarefa usando o método setTitulo da classe Tarefa
                                        
                    System.out.print("Nova descrição: "); // pede para o usuário digitar a nova descrição da tarefa
                    n.setDescricao(entrada.nextLine()); // lê a nova descrição digitada pelo usuário e atualiza a descrição da tarefa usando o método setDescricao da classe Tarefa
                                        
                    n.setDataLimite(lerData(entrada)); //atualiza a data limite da tarefa usando o método setDataLimite da classe Tarefa

                    System.out.println("Tarefa Editada com sucesso!\n"); // informa ao usuário que a tarefa foi editada com sucesso
                                        
                } else {
                    System.out.println("Número inválido!");
                }                      
            }
        } while (verificar(entrada));
    }

    public void excluir(Scanner entrada) {
        do { 
            System.out.println("--------- Exclusão de Tarefas ---------");
            System.out.print("Digite o título da tarefa: ");

            Tarefa encontrada = buscarPorTitulo(entrada.nextLine()); // busca a tarefa pelo título usando o método buscarPorTitulo

            if (encontrada != null) {
                System.out.println("Tarefa não encontrada!");
            } else {
                listaDeTarefas.remove(encontrada); // remove a tarefa encontrada da lista de tarefas usando o método remove da classe ArrayList
                System.out.println("Tarefa Excluída!");
            }

            
        } while (verificar(entrada));

    }

    public void filtrar (Scanner entrada) {
        do { 
            
        } while (verificar(entrada));

    }
}


