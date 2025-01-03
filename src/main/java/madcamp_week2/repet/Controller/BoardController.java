package madcamp_week2.repet.Controller;

import madcamp_week2.repet.DTO.BoardDto;
import madcamp_week2.repet.Service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 가져온 데이터를 model을 통해 view에 전달
    @GetMapping("/")
    public String list(Model model) { // model : 뷰(HTML 페이지)와 데이터를 공유하기 위해 사용되는 객체
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList); // 뷰에서 사용할 데이터 boardDto를 model에 추가
        return "board/list.html";
    }


    @GetMapping("/post")
    public String post(){
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }
}
