package shop.mtcoding.blog.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Love {
    private int id;
    private int boardId;
    private int boardUserId;
    private int actedUserId;
    private Boolean isCheck;
    private Timestamp createdAt;
}
