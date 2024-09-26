package core;

public class LoginSuccessResponse {
    public  String token;

    public String getToken() {
        return token;
    }

    public LoginSuccessResponse(String token) {
        this.token = token;

    }
}
