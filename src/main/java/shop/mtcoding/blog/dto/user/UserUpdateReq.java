package shop.mtcoding.blog.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateReq {
    private int id;
    private String password;
    private String email;
}
