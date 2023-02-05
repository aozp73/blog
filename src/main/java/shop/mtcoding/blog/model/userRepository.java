package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blog.dto.user.UserCheckRes;

@Mapper
public interface UserRepository {
        public int insert(@Param("username") String username, @Param("password") String password,
                        @Param("email") String eamil);

        public List<User> findByAll();

        public List<UserCheckRes> findByAllUsername();

        public User findByUsername(String username);

        public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

        public User findById(int id);

        public int updateById(@Param("id") int id, @Param("password") String password,
                        @Param("email") String eamil);

        public int deleteById(int id);
}
