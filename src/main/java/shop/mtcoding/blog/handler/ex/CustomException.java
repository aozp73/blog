package shop.mtcoding.blog.handler.ex;

// 스프링(JVM)이 실행되고 나서 터지는 Exception들은 RuntimeException의 자식
public class CustomException extends RuntimeException {
    public CustomException(String msg) {
        super(msg);
    }
}
