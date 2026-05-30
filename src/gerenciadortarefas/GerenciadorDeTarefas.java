package gerenciadortarefas;

import java.time.LocalDate; // importa a classe LocalDate para lidar com datas de forma mais eficiente e segura, facilitando a manipulação.
import java.time.format.DateTimeFormatter; // converte a data digitada pelo usuário para o formato LocalDate
import java.util.ArrayList; // importa a classe ArrayList para criar uma lista dinâmica de tarefas, permitindo adicionar, remover e acessar tarefas de forma flexível.
import java.util.Scanner; // importa a classe Scanner para ler a entrada do usuário, permitindo que o programa interaja com o usuário por meio do console, facilitando a leitura de dados como títulos, descrições e datas das tarefas.

public class GerenciadorDeTarefas {

    //incluindo arrayList<tarefa>
	ArrayList<Tarefa> listaDeTarefas = new ArrayList<>(); // criando um ArrayList para armazenar as tarefas

    // define o formato esperado de data para todos os métodos que lidam com datas, garantindo consistência na entrada e facilitando a manipulação de datas em todo o programa.
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // método de verificação para realizar a ação novamente ou retornar ao menu
    private boolean verificar(Scanner entrada) {
        String resposta; // variável para armazenar a resposta do usuário sobre realizar a ação novamente ou voltar ao menu

        System.out.println(); // imprime uma linha em branco
        System.out.print("Deseja realizar essa ação novamente? (s/n): ");
        resposta = entrada.nextLine(); 

        if (resposta.equalsIgnoreCase("s")) {
            System.out.println(); // imprime uma linha em branco para separar a ação repetida do próximo menu
            return true; // retorna aqui e nem chega no else if
        } else if (resposta.equalsIgnoreCase("n")) { // se a resposta for "n" ou "N", o programa entra aqui para perguntar se o usuário deseja voltar ao menu principal ou realizar a ação novamente
            System.out.print("Deseja voltar ao menu? (s/n): ");
            resposta = entrada.nextLine();

            if (resposta.equalsIgnoreCase("s")) { // se a resposta for "s" ou "S", o programa entra aqui para voltar ao menu principal
                System.out.println(); // imprime uma linha em branco para separar a ação repetida do próximo menu
                return false; // volta ao menu
            } else if (resposta.equalsIgnoreCase("n")) { // se a resposta for "n" ou "N", o programa entra aqui para realizar a ação novamente
                return verificar(entrada); // volta a perguntar se deseja realizar a ação novamente
            } else {
                System.out.println("Opção inválida!"); 
                return verificar(entrada); // se a resposta for inválida, chama o método verificar novamente para pedir uma resposta válida
            }
        } else {
            System.out.println("Opção inválida!");
            return verificar(entrada); // se a resposta for inválida, chama o método verificar novamente para pedir uma resposta válida
        }
    }
    
    // tratamento de data para evitar que o usuário digite uma data inválida 
    private LocalDate lerData(Scanner entrada) {
        LocalDate data = null; // inicializa a variável data como null para entrar no loop de validação
        while (data == null) { // enquanto a data for null, ou seja, enquanto o usuário não digitar uma data válida, o programa entra aqui para pedir a data novamente
            System.out.print("Data Limite (dd/MM/yyyy): "); 
            String dataStr = entrada.nextLine(); // lê a data digitada pelo usuário como uma string
            try { // tenta converter a string da data para um objeto LocalDate usando o formato definido anteriormente. Se a conversão for bem-sucedida, a data é armazenada na variável data e o loop é encerrado. 
                data = LocalDate.parse(dataStr, formato);
            } catch (Exception e) { // Se a conversão falhar (por exemplo, se o usuário digitar uma data em um formato inválido), o programa entra nesse bloco para informar ao usuário que a data é inválida e pedir para digitar novamente.
                System.out.println("Data inválida! Use o formato dd/MM/yyyy. Tente novamente."); // converte a string da data para um objeto LocalDate
            }
        }
        return data; // retorna a data válida lida do usuário para ser usada nos métodos de cadastro e edição de tarefas
    }

    private Tarefa buscarPorTitulo(String titulo) { // método para buscar uma tarefa pelo título, que é usado para evitar a criação de tarefas com títulos duplicados e para localizar tarefas para edição, exclusão ou alteração de status.
        for (Tarefa t : listaDeTarefas) { // percorre a lista de tarefas usando um loop for-each, onde cada tarefa é representada pela variável t do tipo Tarefa.
            if (t.getTitulo().trim().equalsIgnoreCase(titulo.trim())) { // compara o título da tarefa atual (t.getTitulo()) com o título buscado (titulo) usando o método equalsIgnoreCase para ignorar diferenças de maiúsculas e minúsculas, e o método trim para remover espaços em branco no início e no final. Se os títulos forem iguais, o programa entra aqui para retornar a tarefa encontrada.
                return t;
            }
    }
    return null; // se o loop terminar sem encontrar uma tarefa com o título buscado, o método retorna null para indicar que a tarefa não foi encontrada. Isso é útil para os métodos de cadastro, edição, exclusão e alteração de status para verificar se a tarefa existe antes de realizar a ação desejada.
}
    

    //incluindo os métodos para gerenciar as tarefas
    public void cadastrar (Scanner entrada) { // método para cadastrar uma nova tarefa, que é usado para criar novas tarefas e adicioná-las à lista de tarefas do gerenciador.
        do {
            System.out.print("--------- Cadastro de Tarefas ---------"); // pede para o usuário digitar o título da tarefa
            System.out.print("\nTítulo: ");
            String titulo = entrada.nextLine().trim(); // lê o título da tarefa digitado pelo usuário

            if (buscarPorTitulo(titulo) != null) { // se encontrou  algo é porque já existe
                System.out.println("Já existe uma tarefa com esse título!");
            } else {

            System.out.print("Descrição: "); // pede para o usuário digitar a descrição da tarefa
            String descricao = entrada.nextLine(); // lê a descrição da tarefa digitada pelo usuário

            LocalDate data = lerData(entrada); // chama o método lerData para obter uma data válida digitada pelo usuário
            
            // Status "Pendente"
            Tarefa novaTarefa = new Tarefa(titulo, descricao, data, "Pendente"); // cria um objeto Tarefa com os dados digitados pelo usuário e o status "Pendente"
            listaDeTarefas.add(novaTarefa); // adiciona a nova tarefa criada à lista de tarefas

            System.out.println();

            System.out.println("Tarefa Adicionada!"); // informa ao usuário que a tarefa foi adicionada com sucesso

            }
        } while (verificar(entrada));
    }

    public void exibir(Scanner entrada) { // método para exibir as tarefas cadastradas, que é usado para mostrar ao usuário a lista de tarefas atuais, permitindo que ele veja os detalhes de cada tarefa, como título, descrição, data limite e status.
        do {
            System.out.println("--------- Tarefas Cadastradas ---------"); // imprime um título para a seção de tarefas
            if (listaDeTarefas.isEmpty()) { // pergunta se a lista está vazia
                System.out.println("Nenhuma tarefa cadastrada!"); // se a lista estiver vazia, informa ao usuário que não há tarefas cadastradas
            } else { // se a lista não estiver vazia, o programa entra aqui para exibir as tarefas
                for (int i = 0; i <listaDeTarefas.size(); i ++) { // percorre a lista de tarefas usando um loop for, criamos a variavel i valendo de 0 até o tamanho da lista de tarefas. 
                    System.out.println ((i + 1) + ". " + listaDeTarefas.get(i)); // imprime o número da tarefa (i + 1) e a tarefa em si usando o método get(i) para acessar cada tarefa na lista. O método toString() da classe Tarefa é chamado automaticamente para exibir as informações da tarefa de forma legível.  
                    }
                }
        } while (verificar(entrada));
    }

    /* método status (Rakel)
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
                    System.out.println("Tarefa Concluída!"); // informa ao usuário que a tarefa foi concluída com sucesso
                } else { // se o índice digitado pelo usuário for inválido, o programa entra aqui para informar ao usuário que o número escolhido é inválido
                    System.out.println("Número inválido!\n");                       
                }
            }
         } while (verificar(entrada));     
    } */

    /* método editar (Rakel)
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
                    String titulo = entrada.nextLine().trim(); // lê o novo título digitado pelo usuário e remove os espaços em branco no início e no final usando o método trim()

                    if (buscarPorTitulo(titulo) != null) { // se encontrou  algo é porque já existe
                        System.out.println("Já existe uma tarefa com esse título!");
                    } else {
                        n.setTitulo(entrada.nextLine());// lê o novo título digitado pelo usuário e atualiza o título da tarefa usando o método setTitulo da classe Tarefa
                                            
                        System.out.print("Nova descrição: "); // pede para o usuário digitar a nova descrição da tarefa
                        n.setDescricao(entrada.nextLine()); // lê a nova descrição digitada pelo usuário e atualiza a descrição da tarefa usando o método setDescricao da classe Tarefa
                                            
                        n.setDataLimite(lerData(entrada)); //atualiza a data limite da tarefa usando o método setDataLimite da classe Tarefa

                        System.out.println("Tarefa Editada com sucesso!\n"); // informa ao usuário que a tarefa foi editada com sucesso
                    }
                                        
                } else {
                    System.out.println("Número inválido!");
                }                      
            }
        } while (verificar(entrada));
    } */

    public void status(Scanner entrada) { // método para alterar o status de uma tarefa, que é usado para marcar tarefas como "Pendente" ou "Concluída", permitindo que o usuário acompanhe o progresso de suas tarefas e mantenha o controle sobre o que já foi feito e o que ainda precisa ser feito.
        do { 
            System.out.println("--------- Alteração de Status de Tarefas ---------");
            if (listaDeTarefas.isEmpty()) { // pergunta se a lista de tarefas está vazia
                System.out.println("Não há tarefas para alterar o status"); // se a lista estiver vazia, informa ao usuário que não há tarefas para alterar o status
            } else {
                System.out.print("Digite o título da tarefa que deseja alterar o status: ");

                Tarefa encontrada = buscarPorTitulo(entrada.nextLine());

                if (encontrada == null) {
                    System.out.println("Tarefa não encontrada!");
                } else {
                    System.out.println("Status atual: " + encontrada.getStatus());
                    System.out.print("Digite o novo status: ");
                    String novoStatus = entrada.nextLine().trim();

                    if (novoStatus.equalsIgnoreCase("Pendente")) {
                    encontrada.setStatus("Pendente");
                        System.out.println("Status atualizado com sucesso!");
                    } else if (novoStatus.equalsIgnoreCase ("Concluida")) {
                        encontrada.setStatus("Concluida"); // atualiza o status da tarefa encontrada usando o método setStatus da classe Tarefa
                        System.out.println("Status atualizado com sucesso!");
                    } else {
                        System.out.println("Status inválido! Use Pendente ou Concluída.");
                    } 
                }
            }

        } while (verificar(entrada));
    }

    public void editar(Scanner entrada) { // método para editar uma tarefa, que é usado para modificar os detalhes de uma tarefa existente, como título, descrição ou data limite, permitindo que o usuário mantenha suas tarefas atualizadas e corrigir informações conforme necessário.
        do {
            System.out.println("--------- Edição de Tarefas ---------");
            if (listaDeTarefas.isEmpty()) { // pergunta se a lista de tarefas está vazia
                System.out.println("Não há tarefas para editar"); // se a lista estiver vazia, informa ao usuário que não há tarefas para editar
            } else {
                System.out.print("Digite o título da tarefa que deseja editar: ");
                Tarefa encontrada = buscarPorTitulo(entrada.nextLine());

                if (encontrada == null) {
                    System.out.println("Tarefa não encontrada!");
                } else {
                    System.out.println("""
                            Escolha o campo que deseja editar:
                            1 - Título
                            2 - Descrição
                            3 - Data Limite
                            4 - Voltar ao Menu Principal
                            """);
                        
                    System.out.print("Digite sua opção: ");
                    int opcEditar = entrada.nextInt();
                    entrada.nextLine(); // Limpa o buffer do scanner

                    switch (opcEditar) {
                        case 1 -> {
                            while (true) {
                                System.out.print("Digite o novo título: ");
                                String novoTitulo = entrada.nextLine().trim();

                                if (buscarPorTitulo(novoTitulo) != null) {
                                    System.out.println("Já existe uma tarefa com esse título!");
                                } else {
                                    encontrada.setTitulo(novoTitulo); // atualiza o título da tarefa encontrada usando o método setTitulo da classe Tarefa
                                    System.out.println("Tarefa editada com sucesso!");
                                    break;
                                }
                            }
                        }

                        case 2 -> {
                            System.out.print("Digite a nova descrição: ");
                            String novaDescricao = entrada.nextLine().trim();
                            encontrada.setDescricao(novaDescricao);

                            System.out.println("Tarefa editada com sucesso!");
                        }
                        case 3 -> {
                            System.out.print("Digite a nova data limite (dd/MM/yyyy): ");
                            LocalDate novaDataLimite = lerData(entrada);
                            encontrada.setDataLimite(novaDataLimite);

                            System.out.println("Tarefa editada com sucesso!");
                        }

                        case 4 -> {
                            System.out.println("Voltando ao menu principal...");
                            return;
                        }

                        default -> System.out.println("Opção inválida! Digite 1, 2, 3 ou 4.");
                    }
                }
            }

        } while (verificar(entrada));
    }

    public void excluir(Scanner entrada) { // método para excluir uma tarefa, que é usado para remover tarefas que não são mais necessárias ou relevantes, permitindo que o usuário mantenha sua lista de tarefas organizada e atualizada.
        do { 
            System.out.println("--------- Exclusão de Tarefas ---------");
            if (listaDeTarefas.isEmpty()) { // pergunta se a lista de tarefas está vazia
                System.out.println("Não há tarefas para excluir"); // se a lista estiver vazia, informa ao usuário que não há tarefas para excluir
            } else {
                System.out.print("Digite o título da tarefa: ");

                Tarefa encontrada = buscarPorTitulo(entrada.nextLine()); // busca a tarefa pelo título usando o método buscarPorTitulo e armazena na variável encontrada

                if (encontrada == null) {
                    System.out.println("Tarefa não encontrada!");
                } else {
                    listaDeTarefas.remove(encontrada); // remove a tarefa encontrada da lista de tarefas usando o método remove da classe ArrayList
                    System.out.println("Tarefa Excluída!");
                }
            }
        } while (verificar(entrada));

    }

    public void filtrar (Scanner entrada) { // método para filtrar tarefas, que é usado para mostrar ao usuário apenas as tarefas que atendem a certos critérios, como data limite ou status, facilitando a visualização e organização das tarefas de acordo com as preferências do usuário.
        do { 
            System.out.println("--------- Filtro de Tarefas ---------");
            if (listaDeTarefas.isEmpty()) { // pergunta se a lista de tarefas está vazia
                System.out.println("Não há tarefas para filtrar"); // se a lista estiver vazia, informa ao usuário que não há tarefas para filtrar
            } else {
                    System.out.println("""
                        Escolha o Filtro:
                        1 - Data Limite
                        2 - Status
                        3 - Voltar ao Menu Principal
                        """);
                    System.out.print("Digite sua opção de filtro: ");
                    int opc = entrada.nextInt();
                    entrada.nextLine(); // Limpa o buffer
                    
                    switch (opc) {
                    case 1 -> {
                        System.out.print("Digite para filtrar pela ");
                        LocalDate dataFiltro = lerData(entrada);
                        System.out.println();
                        System.out.println("Tarefas com data limite " + dataFiltro.format(formato) + ":"); // imprime um título para a seção de tarefas filtradas por data limite, formatando a data usando o formato definido anteriormente
                        for (Tarefa t : listaDeTarefas) {
                            if (t.getDataLimite().equals(dataFiltro)) {
                                System.out.println(t);
                            } else {
                                System.out.println("Nenhuma tarefa encontrada com essa data limite!");
                                break;
                            }
                        }
                    }
                    case 2 -> {
                        System.out.print("Digite o status para filtrar (Pendente ou Concluída): ");
                        String statusFiltro = entrada.nextLine().trim();
                        System.out.println();
                        System.out.println("Tarefas com status " + statusFiltro + ":");

                        for (Tarefa t : listaDeTarefas) {
                            if (t.getStatus().equalsIgnoreCase(statusFiltro)) {
                                System.out.println(t);
                            } else {
                                System.out.println("Nenhuma tarefa encontrada com esse status!");
                                break;
                            }
                        }
                    }
                    case 3 -> {
                        System.out.println("Voltando ao menu principal...");
                        return;
                    }
                    default -> System.out.println("Opção inválida! Digite 1, 2 ou 3.");
                }
            }
        } while (verificar(entrada));
    }

    public void listarDataLimite (Scanner entrada) { // método para listar tarefas por data limite, que é usado para mostrar ao usuário as tarefas ordenadas pela data limite, facilitando a visualização das tarefas que precisam ser concluídas em breve e ajudando o usuário a priorizar suas atividades.
        do {
            System.out.println("--------- Lista de Tarefas (Data Limite)---------");
            if (listaDeTarefas.isEmpty()) { // pergunta se a lista de tarefas está vazia
                System.out.println("Não há tarefas para listar"); // se a lista estiver vazia, informa ao usuário que não há tarefas para listar
            } else {
                for (int i = 0; i < listaDeTarefas.size() - 1; i ++) { // percorre a lista de tarefas até o penúltimo elemento para eivtar comparação com um índice que esteja fora do limite da lista
                    int indiceMenor = i; // define uma data limite menor para iniciar as comparações

                    for (int j = i + 1; j < listaDeTarefas.size(); j ++) {  // percorre novamente a lista, mas agora começa pelo elemento seguinte ao do índice i para comparar as datas limite com essa que defini como menor
                        if (listaDeTarefas.get(j).getDataLimite().isBefore(listaDeTarefas.get(indiceMenor).getDataLimite())) {
                            indiceMenor = j; // atualiza o índice ao encontrar uma data limite menor que a anterior
                        }
                    }

                    Tarefa atual = listaDeTarefas.get(i);
                    listaDeTarefas.set(i, listaDeTarefas.get(indiceMenor)); // troca a posição da tarefa atual com a tarefa que tem a data limite menor encontrada
                    listaDeTarefas.set(indiceMenor, atual); // coloca a tarefa atual na posição do índice menor, completando a troca
                }

                // pega parte do método exibir, mas não toda para não haver duplicidade de linhas no console
                for (int i = 0; i <listaDeTarefas.size(); i ++) { // percorre a lista de tarefas usando um loop for, criamos a variavel i valendo de 0 até o tamanho da lista de tarefas. 
                    System.out.println ((i + 1) + ". " + listaDeTarefas.get(i)); // imprime o número da tarefa (i + 1) e a tarefa em si usando o método get(i) para acessar cada tarefa na lista. O método toString() da classe Tarefa é chamado automaticamente para exibir as informações da tarefa de forma legível.  
                } 
            }
        } while (verificar(entrada));
    }
}


