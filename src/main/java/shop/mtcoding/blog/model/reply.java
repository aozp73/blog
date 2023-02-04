package shop.mtcoding.blog.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reply {
    private int boardId;
    private int userId;
    private String username;
    private String content;
    private Timestamp createdAt;
}
