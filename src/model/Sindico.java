package model;

public class Sindico extends Pessoa {
    private String registro;
    private String dataEleicao;

    public Sindico(String nome, String cpf, String telefone, String registro, String dataEleicao) {
        super(nome, cpf, telefone);
        this.registro = registro;
        this.dataEleicao = dataEleicao;
    }

    // Getters e Setters
    public String getRegistro() { return registro; }
    public void setRegistro(String registro) { this.registro = registro; }
    public String getDataEleicao() { return dataEleicao; }
    public void setDataEleicao(String dataEleicao) { this.dataEleicao = dataEleicao; }

    @Override
    public String toString() {
        return super.toString() + " - Síndico (Reg: " + registro + ")";
    }
}