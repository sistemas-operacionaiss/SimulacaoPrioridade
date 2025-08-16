import java.util.ArrayList;
import java.util.Scanner;

public class TrabalhoSOPrioridade {
    
    public static boolean trocarContexto = false;
    public static Processo pidPrioritario = null;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Processo> processos = new ArrayList<>();
        Processo teste1 = new Processo(111, 1, 15, 15, 5);
        processos.add(teste1);
        Processo teste2 = new Processo(222, 2, 25, 25, 8);
        processos.add(teste2);
        Processo teste3 = new Processo(333, 3, 5, 5, 10);
        processos.add(teste3);
        Processo teste4 = new Processo(444, 4, 10, 10, 5);
        processos.add(teste4);
 
        int opcao = 45;

        Object entrada = "";

        do {
            System.out.println(" ");
            System.out.println("------------------------------------------------------- MENU ---------------------------------------------------------- \n");
            System.out.println("\t1 - Cadastrar novo processo");
            System.out.println("\t2 - Consultar processos cadastrados");
            System.out.println("\t3 - Simular algoritmo de Prioridade");
            System.out.println("\t0 - Encerrar");
            System.out.println(" ");
            System.out.print("\tDigite a opção desejada: ");
            entrada = teclado.next();
            System.out.println(" ");

            try {
                opcao = Integer.parseInt(entrada.toString());
            } catch (NumberFormatException e) {
            }

            switch (opcao) {
                case 1: {
                    int PID = processos.size() + 1;
                    int lugarNaFila = processos.size() + 1;
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                    System.out.println("-------------------------------------------------- INSERIR PROCESSO ---------------------------------------------------");
                    System.out.println("\tPID: " + PID + " | Posição: " + lugarNaFila);
                    System.out.print("\tTempo de execução: ");
                    int tempoDuracao = teclado.nextInt();
                    System.out.print("\tPrioridade: ");
                    int prioridade = teclado.nextInt();
                    int tempoRestante = tempoDuracao;
                    Processo processo1 = new Processo(PID, lugarNaFila, tempoDuracao, tempoRestante, prioridade);
                    processos.add(processo1);
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                }
                break;

                case 2: {
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                    System.out.println("----------------------------------------------- PROCESSOS CADASTRADOS -------------------------------------------------");
                    for (Processo processo : processos) {
                        System.out.println(processo);
                    }
                    System.out.println("------------------------------------------- Quantidade total de processos: " + processos.size() + " ------------------------------------------");
                }
                break;

                case 3: {
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                    System.out.println("------------------------------------------------- SIMULAÇÃO PRIORIDADE ------------------------------------------------");
                    
                    int contadorTotal = 1;//inicio timer
                    
                    while(!verificarProcessosFinilizados(processos)){//enquanto existirem processos que não foram finalizados percorre o loop
                        Processo processoPrioritario = encontrarProcessoComMaiorPrioridade(processos);//puxa o metodo que verifica se o processo não foi finalizado e se ele tem a prioridade maior do que esta atribuido a variavel
                        System.out.println("\n------------------------------------ INICIO DO PROCESSAMENTO DO PROCESSO DE PID " + processoPrioritario.PID + " -----------------------------------");
                        
                        if(pidPrioritario == null || pidPrioritario.finalizado){
                            pidPrioritario = processoPrioritario;
                        } else if ( !pidPrioritario.finalizado && pidPrioritario.PID != processoPrioritario.PID ){
                            contadorTotal++;
                            System.out.println("---------------------------------------------------- O contexto do processo anterior foi salvo - Tempo percorrido: " + (contadorTotal - 1)+"ms");
                            pidPrioritario = processoPrioritario;
                        }
                        
                        for (int i = 0; i < processoPrioritario.tempoDuracao; i++) {
                            processoPrioritario.contadorDeTempoDoProcesso = contadorTotal;
                            processoPrioritario.tempoRestante--;
                            System.out.println(processoPrioritario.toString());
                            if(processoPrioritario.tempoRestante == 0){
                                processoPrioritario.finalizado = true;
                                contadorTotal++;
                                break;
                            }
                            if(i == 4){
                                processoPrioritario.prioridade--;
                                contadorTotal++;
                                break;
                            }
                            contadorTotal++;
                        }
                        System.out.println(" TEMPO TOTAL DE PROCESSAMENTO: " + (contadorTotal -1) + " --------------------------------------------------------------------------------------");
                        
                        System.out.println("");
                        for (Processo processo : processos) {
                            if(processo.finalizado){
                                System.out.println("\t\t\t\t\t\t\t"+processo.PID + " -- FINALIZADO ");
                            }else{
                                System.out.println(processo);
                            }
                        }
                            
                    }
                    
                    
                }
                break;

                case 0: {
                    System.out.println("Encerrando o programa.");
                }
            }
        } while (opcao != 0);
    }

    private static Processo encontrarProcessoComMaiorPrioridade(ArrayList<Processo> processos) {
        if (processos.isEmpty()) {
            return null; // Retorna null se a lista estiver vazia
        }

        Processo processoComMaiorPrioridade = null;

        for (Processo processo : processos) {
            // Considera apenas processos não finalizados
            if (!processo.finalizado && (processoComMaiorPrioridade == null || processo.prioridade > processoComMaiorPrioridade.prioridade)) {
                processoComMaiorPrioridade = processo;
                //TrabalhoSOPrioridade.pidPrioritario = processo.PID;
                
            }
        }

        return processoComMaiorPrioridade;
    }
    
    private static boolean verificarProcessosFinilizados(ArrayList<Processo> processos) {
        if (processos.isEmpty()) {
            return true; 
        }
        
        for (Processo processo : processos) {
            if (processo.finalizado == false) {
                return false;
            }
        }
        return true;
    }
}
