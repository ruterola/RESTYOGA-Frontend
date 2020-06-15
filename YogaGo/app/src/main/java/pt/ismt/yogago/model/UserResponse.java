package pt.ismt.yogago.model;

import java.util.List;

public class UserResponse {
    private boolean success;
    private List<Utilizadores> utilizadoresList;

    public UserResponse(boolean success, List<Utilizadores> utilizadoresList) {
        this.success = success;
        this.utilizadoresList = utilizadoresList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Utilizadores> getUtilizadoresList() {
        return utilizadoresList;
    }

    public void setUtilizadoresList(List<Utilizadores> utilizadoresList) {
        this.utilizadoresList = utilizadoresList;
    }
}
