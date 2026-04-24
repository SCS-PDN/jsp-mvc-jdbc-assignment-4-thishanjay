package com.assignment4.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = getPath(request);
        if ("POST".equalsIgnoreCase(request.getMethod()) && "/login".equals(path)) {
            request.setAttribute("loginAttemptEmail", request.getParameter("email"));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        String method = request.getMethod();
        String path = getPath(request);
        String viewName = modelAndView != null ? modelAndView.getViewName() : null;

        if ("POST".equalsIgnoreCase(method) && "/login".equals(path)) {
            String email = String.valueOf(request.getAttribute("loginAttemptEmail"));
            boolean success = viewName != null && viewName.startsWith("redirect:/courses");
            if (success) {
                logger.info("Login attempt SUCCESS for email={}", email);
            } else {
                logger.warn("Login attempt FAILURE for email={}", email);
            }
            return;
        }

        if ("POST".equalsIgnoreCase(method) && path.startsWith("/register/")) {
            String courseId = path.substring("/register/".length());
            HttpSession session = request.getSession(false);
            String userEmail = session != null ? (String) session.getAttribute("loggedInUser") : "anonymous";
            boolean success = "success".equals(viewName);
            if (success) {
                logger.info("Course registration SUCCESS for user={} courseId={}", userEmail, courseId);
            } else {
                logger.warn("Course registration FAILED for user={} courseId={}", userEmail, courseId);
            }
        }
    }

    private String getPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (contextPath != null && !contextPath.isEmpty() && uri.startsWith(contextPath)) {
            return uri.substring(contextPath.length());
        }
        return uri;
    }
}
