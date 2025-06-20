package model;

import java.util.ArrayList;
import java.util.List;

public class Condominio {
    private String nome;
    private String endereco;
    private List<AssociacaoSindicoCondominio> sindicos;

    public Condominio(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.sindicos = new ArrayList<>();
    }

    public void adicionarSindico(Sindico sindico, String dataInicio, String dataFim) {
        sindicos.add(new AssociacaoSindicoCondominio(this, sindico, dataInicio, dataFim));
    }

    // Getters
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public List<AssociacaoSindicoCondominio> getSindicos() { return sindicos; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    @Override
    public String toString() {
        return nome + " - " + endereco;
    }
}