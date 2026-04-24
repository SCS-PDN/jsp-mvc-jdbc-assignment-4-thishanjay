package lk.ac.pdn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, 
                             HttpServletResponse response, 
                             Object handler) throws Exception {

        String url = request.getRequestURI();
        String method = request.getMethod();

        if (url.contains("/login") && method.equals("POST")) {
            String email = request.getParameter("email");
            System.out.println("[LOG] Login attempt by: " + email);
        }

        if (url.contains("/register") && method.equals("POST")) {
            System.out.println("[LOG] Registration attempt - URL: " + url);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        String url = request.getRequestURI();
        String method = request.getMethod();

        if (url.contains("/login") && method.equals("POST")) {
            if (modelAndView != null && modelAndView.getViewName() != null) {
                String view = modelAndView.getViewName();
                if (view.contains("redirect:/courses")) {
                    System.out.println("[LOG] Login SUCCESS");
                } else {
                    System.out.println("[LOG] Login FAILED - invalid credentials");
                }
            }
        }

        if (url.contains("/register") && method.equals("POST")) {
            if (modelAndView != null) {
                System.out.println("[LOG] Course registration SUCCESS - URL: " + url);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        if (ex != null) {
            System.out.println("[LOG] ERROR occurred: " + ex.getMessage());
        }
    }
}