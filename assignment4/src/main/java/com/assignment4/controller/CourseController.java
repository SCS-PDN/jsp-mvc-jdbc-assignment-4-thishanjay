package com.assignment4.controller;

import com.assignment4.model.Course;
import com.assignment4.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String showCourses(Model model, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("userEmail", loggedInUser);
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable("courseId") int courseId,
                                 Model model,
                                 HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        boolean registered = courseService.registerLoggedInStudent(loggedInUser, courseId);
        if (!registered) {
            return "redirect:/courses";
        }

        Course selectedCourse = courseService.findById(courseId);
        model.addAttribute("userEmail", loggedInUser);
        model.addAttribute("course", selectedCourse);
        return "success";
    }
}
