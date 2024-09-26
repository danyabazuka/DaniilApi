package core;

public class CreateRs {
    public String name;
    public String job;
    public int id;
    public String createdAt;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public CreateRs(String name, String job, int id, int createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = String.valueOf(createdAt);


    }
}
