package shop.mtcoding.blog.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDetailDto {
    private int id;
    private int userId;
    private String username;
    private String title;
    private String content;
    private int loveCnt;
}
