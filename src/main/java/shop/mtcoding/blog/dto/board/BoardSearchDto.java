package shop.mtcoding.blog.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchDto {
    private int begin;
    private int end;
    private String keyword;
}
