package memento;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class Originator {
    private List<Pessoa> estadoPessoas;
    private List<Sindico> estadoSindicos;
    private List<Condominio> estadoCondominios;

    public void setEstado(List<Pessoa> pessoas, List<Sindico> sindicos, List<Condominio> condominios) {
        this.estadoPessoas = new ArrayList<>(pessoas);
        this.estadoSindicos = new ArrayList<>(sindicos);
        this.estadoCondominios = new ArrayList<>(condominios);
    }

    public Memento salvarParaMemento() {
        List<List<?>> estadoCompleto = new ArrayList<>();
        estadoCompleto.add(new ArrayList<>(estadoPessoas));
        estadoCompleto.add(new ArrayList<>(estadoSindicos));
        estadoCompleto.add(new ArrayList<>(estadoCondominios));
        return new Memento(estadoCompleto);
    }

    @SuppressWarnings("unchecked")
    public void restaurarDeMemento(Memento memento) {
        List<List<?>> estadoCompleto = (List<List<?>>) memento.getEstadoSalvo();
        this.estadoPessoas = (List<Pessoa>) estadoCompleto.get(0);
        this.estadoSindicos = (List<Sindico>) estadoCompleto.get(1);
        this.estadoCondominios = (List<Condominio>) estadoCompleto.get(2);
    }

    public List<Pessoa> getEstadoPessoas() {
        return estadoPessoas;
    }

    public List<Sindico> getEstadoSindicos() {
        return estadoSindicos;
    }

    public List<Condominio> getEstadoCondominios() {
        return estadoCondominios;
    }
}