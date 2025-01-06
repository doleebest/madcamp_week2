package madcamp_week2.repet.Controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";  // login.html을 반환
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(RedirectAttributes redirectAttributes) {
        return "redirect:http://localhost:3000/dashboard";
    }
}