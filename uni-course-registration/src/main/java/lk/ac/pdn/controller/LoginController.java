package lk.ac.pdn.controller;

import lk.ac.pdn.dao.StudentDAO;
import lk.ac.pdn.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
    private StudentDAO studentDAO;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               HttpSession session,
                               org.springframework.ui.Model model) {

        Student student = studentDAO.findByEmailAndPassword(email, password);

        if (student != null) {
            session.setAttribute("loggedInStudent", student);
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
