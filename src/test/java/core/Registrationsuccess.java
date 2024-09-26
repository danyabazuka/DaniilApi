package core;

public class Registrationsuccess {
    private int id;
    private String token;

    public Registrationsuccess(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
