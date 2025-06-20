package memento;

import model.*;

public class Memento {
    private final Object estado;

    public Memento(Object estadoParaSalvar) {
        this.estado = estadoParaSalvar;
    }

    public Object getEstadoSalvo() {
        return estado;
    }
}