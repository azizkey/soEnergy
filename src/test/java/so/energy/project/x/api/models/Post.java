package so.energy.project.x.api.models;

import lombok.Data;

@Data
public class Post {
    private int id;
    private int userId;
    private String title;
    private String body;
}
