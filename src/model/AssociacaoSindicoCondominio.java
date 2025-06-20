package model;

public class AssociacaoSindicoCondominio {
    private final Condominio condominio;
    private final Sindico sindico;
    private String dataInicio;
    private String dataFim;

    public AssociacaoSindicoCondominio(Condominio condominio, Sindico sindico, String dataInicio, String dataFim) {
        this.condominio = condominio;
        this.sindico = sindico;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters
    public Condominio getCondominio() { return condominio; }
    public Sindico getSindico() { return sindico; }
    public String getDataInicio() { return dataInicio; }
    public String getDataFim() { return dataFim; }

    @Override
    public String toString() {
        return sindico + " -> " + condominio + " (" + dataInicio + " a " + dataFim + ")";
    }
}