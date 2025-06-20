package memento;

import java.util.Stack;

public class Caretaker {
    private final Originator originator;
    private final Stack<Memento> historico = new Stack<>();
    private final Stack<Memento> refazer = new Stack<>();

    public Caretaker(Originator originator) {
        this.originator = originator;
    }

    public void salvarEstado() {
        Memento memento = originator.salvarParaMemento();
        historico.push(memento);
        refazer.clear(); // Limpa o stack de refazer após novo estado
    }

    public void desfazer() {
        if (!historico.isEmpty()) {
            Memento estadoAtual = originator.salvarParaMemento();
            refazer.push(estadoAtual);

            Memento mementoAnterior = historico.pop();
            originator.restaurarDeMemento(mementoAnterior);
        }
    }

    public void refazer() {
        if (!refazer.isEmpty()) {
            Memento estadoAtual = originator.salvarParaMemento();
            historico.push(estadoAtual);

            Memento mementoRefazer = refazer.pop();
            originator.restaurarDeMemento(mementoRefazer);
        }
    }
}