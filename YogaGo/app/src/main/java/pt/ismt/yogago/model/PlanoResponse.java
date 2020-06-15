package pt.ismt.yogago.model;

import java.util.List;

public class PlanoResponse {
    private boolean success;
    private List<Planos> planosList;

    public PlanoResponse(boolean success, List<Planos> planosList) {
        this.success = success;
        this.planosList = planosList;
    }

    public PlanoResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Planos> getPlanosList() {
        return planosList;
    }

    public void setPlanosList(List<Planos> planosList) {
        this.planosList = planosList;
    }
}
