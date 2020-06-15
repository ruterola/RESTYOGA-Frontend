package pt.ismt.yogago.model;

public class SimpleResponse {
    private boolean success;
    private String message;

    public SimpleResponse(boolean success) {
        this.success = success;
    }

    public SimpleResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SimpleResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
