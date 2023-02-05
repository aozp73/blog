package shop.mtcoding.blog.vo;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
    private int userId;
    private String title;
    private String content;
    private int loveCnt;
    private Timestamp createdAt;
}
