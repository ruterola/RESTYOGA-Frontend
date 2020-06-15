package pt.ismt.yogago.model;

public class LoginResponse {
    private String token;
    private Utilizadores utilizador;

    private String message;

    public LoginResponse(String token, Utilizadores utilizador, String message) {
        this.token = token;
        this.utilizador = utilizador;
        this.message = message;
    }

    public LoginResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Utilizadores getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizadores utilizador) {
        this.utilizador = utilizador;
    }
}
