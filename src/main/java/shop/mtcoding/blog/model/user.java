package shop.mtcoding.blog.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class user {
    private int username;
    private String password;
    private String email;
    private Timestamp createdAt;
}
