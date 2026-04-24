package lk.ac.pdn.controller;

import lk.ac.pdn.dao.CourseDAO;
import lk.ac.pdn.model.Course;
import lk.ac.pdn.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseDAO courseDAO;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String showCourses(Model model, HttpSession session) {

        if (session.getAttribute("loggedInStudent") == null) {
            return "redirect:/login";
        }

        List<Course> courses = courseDAO.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @RequestMapping(value = "/register/{courseId}", method = RequestMethod.POST)
    public String registerCourse(@PathVariable("courseId") int courseId,
                                  HttpSession session,
                                  Model model) {

        Student student = (Student) session.getAttribute("loggedInStudent");
        if (student == null) {
            return "redirect:/login";
        }

        if (courseDAO.isAlreadyRegistered(student.getStudentId(), courseId)) {
            model.addAttribute("error", "You are already registered for this course!");
            List<Course> courses = courseDAO.getAllCourses();
            model.addAttribute("courses", courses);
            return "courses";
        }

        courseDAO.registerStudent(student.getStudentId(), courseId);

        String courseName = courseDAO.getCourseName(courseId);
        model.addAttribute("courseName", courseName);

        return "success";
    }
}