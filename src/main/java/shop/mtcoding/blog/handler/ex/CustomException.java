package shop.mtcoding.blog.handler.ex;

import org.springframework.http.HttpStatus;

import lombok.Getter;

// 스프링(JVM)이 실행되고 나서 터지는 Exception들은 RuntimeException의 자식
@Getter
public class CustomException extends RuntimeException {

    private HttpStatus status;

    public CustomException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public CustomException(String msg) {
        this(msg, HttpStatus.BAD_REQUEST);
    }
}
