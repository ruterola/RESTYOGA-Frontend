package pt.ismt.yogago.model;

import java.util.List;

public class ArtigoResponse {
    private boolean success;
    private List<Artigo> artigoList;

    public ArtigoResponse(boolean success, List<Artigo> artigoList) {
        this.success = success;
        this.artigoList = artigoList;
    }

    public ArtigoResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Artigo> getArtigoList() {
        return artigoList;
    }

    public void setArtigoList(List<Artigo> artigoList) {
        this.artigoList = artigoList;
    }
}
