package madcamp_week2.repet.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";  // login.html을 반환
    }

    @GetMapping("/loginSuccess")
    @ResponseBody
    public String loginSuccess() {
        return "Login Successful!";
    }
}