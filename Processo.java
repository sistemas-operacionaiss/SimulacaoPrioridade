public class Processo {
    int PID;
    int lugarNaFila;
    int tempoDuracao;
    int tempoRestante;
    int prioridade;
    int contadorDeTempoDoProcesso;
    boolean finalizado = false;

    public Processo(int PID, int lugarNaFila, int tempoDuracao, int tempoRestante, int prioridade) {
        this.PID = PID;
        this.lugarNaFila = lugarNaFila;
        this.tempoDuracao = tempoDuracao;
        this.tempoRestante = tempoRestante;
        this.prioridade = prioridade;
    }

    public String toString() {
        return " PID: " + PID
                + " | Posição na fila: " + lugarNaFila
                + " | Processamento: "
                + tempoDuracao + "ms | Proridade: "
                + prioridade + "ms | Tempo percorrido: " + contadorDeTempoDoProcesso + "ms | Esgotamento: "
                + tempoRestante;
    }
}
