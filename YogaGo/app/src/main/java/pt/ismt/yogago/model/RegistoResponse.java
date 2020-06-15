package pt.ismt.yogago.model;

public class RegistoResponse {
    private Utilizadores utilizador;

    private String message;

    public RegistoResponse(String token, Utilizadores utilizador, String message) {
        this.utilizador = utilizador;
        this.message = message;
    }

    public RegistoResponse() {
    }
}