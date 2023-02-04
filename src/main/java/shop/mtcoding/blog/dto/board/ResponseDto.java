package shop.mtcoding.blog.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {
    private int code; // 1, -1
    private String msg; // 상세사유
    private T data;
}
