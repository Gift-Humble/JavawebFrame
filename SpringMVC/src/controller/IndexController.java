package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello Spring MVC");
        return mav;
    }

    @RequestMapping("/jump")
    public ModelAndView jump() {
        ModelAndView mav = new ModelAndView("redirect:/index"); //客户端跳转到index
        return mav;
    }

    @RequestMapping("/check")
    public ModelAndView check(HttpSession httpSession) {
        Integer i = (Integer) httpSession.getAttribute("count");
        if (i == null)
            i = 0;
        i++;
        httpSession.setAttribute("count", i);
        ModelAndView mav = new ModelAndView("check");
        return mav;
    }
}
