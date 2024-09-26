package core;

public class LoginUnSuccessResponse {
    public String error;

    public LoginUnSuccessResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
