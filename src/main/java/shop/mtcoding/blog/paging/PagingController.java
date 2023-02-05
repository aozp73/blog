// package shop.mtcoding.blog.paging;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

// import lombok.RequiredArgsConstructor;
// import shop.mtcoding.blog.model.Board;
// import shop.mtcoding.blog.model.BoardRepository;
// import shop.mtcoding.blog.model.UserRepository;

// @RequiredArgsConstructor
// @Controller
// public class PagingController {

// private final BoardRepository boardRepository;
// private final UserRepository userRepository;

// @GetMapping("/tes")
// @ResponseBody
// public String tes() {
// List<Board> list = boardRepository.findByAllOrederByLove();
// int maxPage = list.size();
// int firstPage = maxPage / 10;
// int lastPage = (int) Math.ceil(((float) maxPage / 10));

// List listP = new ArrayList();
// List list1 = new ArrayList();
// List list2 = new ArrayList();
// return "굿";
// }
// }
