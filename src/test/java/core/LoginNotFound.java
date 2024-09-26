package core;

public class LoginNotFound {
    public  String error;

    public LoginNotFound(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
