package pt.ismt.yogago.model;

import java.util.List;

public class AtividadesResponse {
    private Utilizadores utilizadores;
    private List<Atividades> atividades;

    public AtividadesResponse() {
    }

    public Utilizadores getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(Utilizadores utilizadores) {
        this.utilizadores = utilizadores;
    }

    public List<Atividades> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividades> atividades) {
        this.atividades = atividades;
    }
}
