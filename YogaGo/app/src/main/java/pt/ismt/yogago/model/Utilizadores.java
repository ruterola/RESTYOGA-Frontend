package pt.ismt.yogago.model;

import java.util.List;

public class Utilizadores {
    private int id;
    private String name;
    private String email;
    private String password;

    private List <Atividades> listaAtividade;

    public Utilizadores(int id, String name, String email, String password, List<Atividades> listaAtividade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.listaAtividade = listaAtividade;
    }

    public Utilizadores() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Atividades> getListaAtividade() {
        return listaAtividade;
    }

    public void setListaAtividade(List<Atividades> listaAtividade) {
        this.listaAtividade = listaAtividade;
    }
}
